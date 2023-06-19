package UI.Elements.GameField;

import Business.GameLogic.Field;
import Business.GameLogic.Game;
import Business.Gamepiece.Gamepiece;
import UI.Presentation.MonsterApplication;
import UI.ViewController;

import javafx.collections.ObservableList;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;


public class GameFieldViewController extends ViewController<MonsterApplication> {
    private final GameFieldView view;
    private final Game game;
    private static final String GAMEPIECE = ";Gamepiece";
    private static final String ITEM = ";Item";
    private ObservableList<Field> possibleMoves;

    public GameFieldViewController(MonsterApplication application, Game game) {
        super(application);
        rootView = new GameFieldView();
        view = (GameFieldView) rootView;
        this.game = game;
        initialize();

    }

    @Override
    public void initialize() {
        gamefieldInitializer();

    }

    private void setDragAndDrop(ImageView imageView) {
        imageView.setOnDragDetected(event -> {
            int sourceRow = ((int) imageView.getLayoutY() / 80);
            int sourceCol = ((int) imageView.getLayoutX() / 80);
            Gamepiece selGamepiece = game.getGamefield().getField(sourceRow, sourceCol).getGamepiece();
            if (selGamepiece == null) return;
            Dragboard dragboard = imageView.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putImage(imageView.getImage());
            dragboard.setContent(content);
            event.consume();

        });

        imageView.setOnDragOver(event -> {
            if (event.getGestureSource() != imageView && event.getDragboard().hasImage()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
        });

        imageView.setOnDragEntered(event -> {
            if (event.getGestureSource() != imageView && event.getDragboard().hasImage()) {
                imageView.setOpacity(0.7); // fix this
            }
            event.consume();
        });

        imageView.setOnDragExited(event -> {
            if (event.getGestureSource() != imageView && event.getDragboard().hasImage()) {
                imageView.setOpacity(1.0);
            }
            event.consume();
        });

        imageView.setOnDragDropped(event -> {
            Dragboard dragboard = event.getDragboard();
            boolean success = false;
            ImageView sourceImageView = (ImageView) event.getGestureSource();
            int sourceRow = ((int) sourceImageView.getLayoutY() / 80);
            int sourceCol = ((int) sourceImageView.getLayoutX() / 80);
            Gamepiece sourceGamepiece = game.getGamefield().getField(sourceRow, sourceCol).getGamepiece(); // auslagern in business
            ImageView targetImageView = (ImageView) event.getTarget();
            int targetRow = ((int) targetImageView.getLayoutY() / 80);
            int targetCol = ((int) targetImageView.getLayoutX() / 80);
            if (dragboard.hasImage()
                    && sourceGamepiece.isValidMove(game.getGamefield().getField(targetRow,targetCol), game)) { // hier fehlt noch check wer grade dran ist damit nur der ziehen kann, der dran ist

                game.getGamefield().getField(targetRow, targetCol).setGamepiece(sourceGamepiece); // auslagern in business
                game.getGamefield().getField(sourceRow, sourceCol).setGamepiece(null);
                if (game.getGamefield().getPlayer1().chooseGamepiece(sourceGamepiece) != null)
                    game.getGamefield().getPlayer1().moveGamepiece(sourceGamepiece, game.getGamefield().getField(targetRow, targetCol));
                else
                    game.getGamefield().getPlayer2().moveGamepiece(sourceGamepiece, game.getGamefield().getField(targetRow, targetCol));
                if (targetImageView.getId().contains(ITEM)) {
                    sourceGamepiece.setInventory(game.getGamefield().getField(targetRow, targetCol).getItem());
                    game.getGamefield().getField(targetRow, targetCol).setItem(null);

                }
                // Copy the image from the source ImageView
                Image draggedImage = sourceImageView.getImage();

                // Remove the image from the source ImageView
                if (sourceImageView.getId().contains("white")) {
                    sourceImageView.setImage(new Image("files/pictures/white_placeholder.png"));
                    sourceImageView.setId("white");
                } else {
                    sourceImageView.setImage(new Image("files/pictures/black_placeholder.jpg"));
                    sourceImageView.setId("black");
                }
                // Set the dropped image to the current ImageView

                targetImageView.setId(targetImageView.getId() + GAMEPIECE);
                targetImageView.setImage(draggedImage);
                success = true;
            }
            event.setDropCompleted(success);
            event.consume();
        });

        imageView.setOnDragDone(DragEvent::consume);
        imageView.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                int selRow = ((int) view.getChildren().get(view.getChildren().indexOf(imageView)).getLayoutY() / 80);
                int selCol = ((int) view.getChildren().get(view.getChildren().indexOf(imageView)).getLayoutX() / 80);
                Field selField = game.getGamefield().getField(selRow, selCol);
                // Perform the logic for handling the selected field here
                System.out.println("row: " + selRow + " column: " + selCol);

                if (selField.getGamepiece() == null) {
                    for (int row = 0; row < GameFieldView.BOARD_SIZE; row++)
                        for (int col = 0; col < GameFieldView.BOARD_SIZE; col++)
                            view.getChildren().get(row * view.getRowCount() + col).setOpacity(1.0);
                    return;
                }

                for (int row = 0; row < GameFieldView.BOARD_SIZE; row++)
                    for (int col = 0; col < GameFieldView.BOARD_SIZE; col++) {
                        Node currNode = view.getChildren().get(row * view.getRowCount() + col);
                        if (selField.getGamepiece().possibleMoves(game).contains(game.getGamefield().getField(row, col))) {
                            if (game.getGamefield().getField(row, col).getGamepiece() == null
                                    && game.getGamefield().getField(row, col).getItem() == null)
                                currNode.setOpacity(0.0);
                        } else currNode.setOpacity(1.0);
                    }


            }
        });
    }



    private void gamefieldInitializer() {
        for (int row = 0; row < view.getRowCount(); row++) {
            for (int col = 0; col < view.getColumnCount(); col++) {
                Node currNode = view.getChildren().get(row * view.getRowCount() + col);
                if (game.getGamefield().getField(row, col).getGamepiece() != null) {
                    currNode.setId(currNode.getId() + GAMEPIECE);
                    if (game.getGamefield().getPlayer1().getOwnGamepieces().contains(game.getGamefield().getField(row, col).getGamepiece()))
                        switch (game.getGamefield().getField(row, col).getGamepiece().getRank()) {
                            case 0 ->
                                    ((ImageView) currNode).setImage(new Image("files/pictures/gamepieces/PawnPlayer1.png"));

                            case 1 ->
                                    ((ImageView) currNode).setImage(new Image("files/pictures/gamepieces/TowerPlayer1.png"));

                            case 2 ->
                                    ((ImageView) currNode).setImage(new Image("files/pictures/gamepieces/QueenPlayer1.png"));

                        }
                    else
                        switch (game.getGamefield().getField(row, col).getGamepiece().getRank()) {
                            case 0 ->
                                    ((ImageView) currNode).setImage(new Image("files/pictures/gamepieces/PawnPlayer2.png"));

                            case 1 ->
                                    ((ImageView) currNode).setImage(new Image("files/pictures/gamepieces/TowerPlayer2.png"));


                            case 2 ->
                                    ((ImageView) currNode).setImage(new Image("files/pictures/gamepieces/QueenPlayer2.png"));


                        }
                }
                if (game.getGamefield().getField(row, col).getItem() != null) {
                    ((ImageView) currNode).setImage(new Image("files/pictures/Item.png"));
                    currNode.setId(currNode.getId() + ITEM);
                }

                setDragAndDrop((ImageView) currNode);
            }
        }

    }


}
