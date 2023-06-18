package UI.Elements.GameField;

import Business.GameLogic.Game;
import Business.Gamepiece.Gamepiece;
import UI.Presentation.MonsterApplication;
import UI.ViewController;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;


public class GameFieldViewController extends ViewController<MonsterApplication> {
    private final GameFieldView view;
    private final Game game;
    private static final String GAMEPIECE = ";Gamepiece";
    private static final String ITEM = ";Item";

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
            if (!imageView.getId().contains(GAMEPIECE)) return;
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

            if (dragboard.hasImage()) {
                ImageView sourceImageView = (ImageView) event.getGestureSource();
                ImageView targetImageView = (ImageView) event.getTarget();
                int sourceRow = ((int) sourceImageView.getLayoutY() / 80);
                int sourceCol = ((int) sourceImageView.getLayoutX() / 80);
                Gamepiece sourceGamepiece = game.getGamefield().getField(sourceRow, sourceCol).getGamepiece(); // auslagern in business
                game.getGamefield().getField(sourceRow, sourceCol).setGamepiece(null);
                int targetRow = ((int) targetImageView.getLayoutY() / 80);
                int targetCol = ((int) targetImageView.getLayoutX() / 80);
                game.getGamefield().getField(targetRow, targetCol).setGamepiece(sourceGamepiece); // auslagern in business
                if (game.getGamefield().getPlayer1().chooseGamepiece(sourceGamepiece) != null)
                    game.getGamefield().getPlayer1().moveGamepiece(sourceGamepiece, game.getGamefield().getField(targetRow, targetCol));
                else
                    game.getGamefield().getPlayer2().moveGamepiece(sourceGamepiece, game.getGamefield().getField(targetRow, targetCol));
                if (targetImageView.getId().contains(ITEM)) {
                    sourceGamepiece.setInventory(game.getGamefield().getField(targetRow,targetCol).getItem());
                    game.getGamefield().getField(targetRow,targetCol).setItem(null);

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
                int row = ((int) view.getChildren().get(view.getChildren().indexOf(imageView)).getLayoutY() / 80);
                int col = ((int) view.getChildren().get(view.getChildren().indexOf(imageView)).getLayoutX() / 80);
                // Perform the logic for handling the selected field here
                System.out.println("row: " + row + " column: " + col);
                if (game.getGamefield().getField(row, col).getGamepiece() != null)
                    System.out.println(game.getGamefield().getField(row, col).getGamepiece().getInventory());


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
