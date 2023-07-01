package UI.Elements.GameField;

import Business.GameLogic.Field;
import Business.GameLogic.Game;
import Business.Gamepiece.Gamepiece;
import Business.Item.Item;
import Business.Item.Trap.Trap;
import UI.Presentation.MonsterApplication;
import UI.ViewController;

import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;


public class GameFieldViewController extends ViewController<MonsterApplication> {
    private final GameFieldView view;
    private final Game game;

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
        game.getNextPlayer().isEngaged().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                gamefieldInitializer();
            }
        });
        game.getCurrentPlayer().isEngaged().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                gamefieldInitializer();
            }
        });
        game.getGamefield().getPlayer1().getOwnGamepieces().addListener((ListChangeListener<Gamepiece>) change ->
                {
                    gamefieldInitializer();
                }
        );
        game.getGamefield().getPlayer2().getOwnGamepieces().addListener((ListChangeListener<Gamepiece>) change ->
                {
                    gamefieldInitializer();
                }
        );
    }

    private void setDragAndDrop(ImageView imageView) {
        imageView.setOnDragDetected(event -> {
            int sourceRow = ((int) imageView.getLayoutY() / GameFieldView.CELL_SIZE);
            int sourceCol = ((int) imageView.getLayoutX() / GameFieldView.CELL_SIZE);
            Gamepiece selGamepiece = game.getGamefield().getField(sourceRow, sourceCol).getGamepiece();
            if (selGamepiece == null || !game.getCurrentPlayer().getOwnGamepieces().contains(selGamepiece)) return;
            showPossibleMoves(imageView, true);
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
            int sourceRow = ((int) sourceImageView.getLayoutY() / GameFieldView.CELL_SIZE);
            int sourceCol = ((int) sourceImageView.getLayoutX() / GameFieldView.CELL_SIZE);
            Gamepiece sourceGamepiece = game.getGamefield().getField(sourceRow, sourceCol).getGamepiece(); // auslagern in business
            ImageView targetImageView = (ImageView) event.getTarget();
            int targetRow = ((int) targetImageView.getLayoutY() / GameFieldView.CELL_SIZE);
            int targetCol = ((int) targetImageView.getLayoutX() / GameFieldView.CELL_SIZE);
            if (dragboard.hasImage() && game.getCurrentPlayer().
                    moveGamepiece(sourceGamepiece, game.getGamefield().getField(targetRow, targetCol), game)) {
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

                targetImageView.setImage(draggedImage);
                success = true;
                clearMoves();
            }
            event.setDropCompleted(success);
            event.consume();
        });

        imageView.setOnDragDone(DragEvent::consume);

        imageView.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                if (game.getCurrentPlayer().isItemUsed()) {
                    int targetRow = ((int) imageView.getLayoutY() / GameFieldView.CELL_SIZE);
                    int targetCol = ((int) imageView.getLayoutX() / GameFieldView.CELL_SIZE);
                    Field targetField = game.getGamefield().getField(targetRow, targetCol);
                    game.getCurrentPlayer().setPosItemUsed(targetField, game.getCurrentPlayer().getCurrGamepiece(), game);
                }
                int sourceRow = ((int) imageView.getLayoutY() / GameFieldView.CELL_SIZE);
                int sourceCol = ((int) imageView.getLayoutX() / GameFieldView.CELL_SIZE);
                game.getCurrentPlayer().chooseGamepiece(game.getGamefield().getField(sourceRow, sourceCol).getGamepiece());
                if (!game.getEffectedGamepieces().containsKey(game.getCurrentPlayer().getCurrGamepiece())) {
                    showPossibleMoves(imageView, true);

                }

            }
        });
    }


    public void gamefieldInitializer() {
        for (int row = 0; row < view.getRowCount(); row++) {
            for (int col = 0; col < view.getColumnCount(); col++) {
                Node currNode = view.getChildren().get(row * view.getRowCount() + col);
                if (game.getGamefield().getField(row, col).getGamepiece() != null) {
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
                Item tempItem = game.getGamefield().getField(row, col).getItem();
                if (tempItem != null) {
                    if (tempItem instanceof Trap) {
                        if (!((Trap) tempItem).isActive())
                            ((ImageView) currNode).setImage(new Image("files/pictures/Item.png"));
                    } else {
                        ((ImageView) currNode).setImage(new Image("files/pictures/Item.png"));
                    }

                }

                setDragAndDrop((ImageView) currNode);
            }
        }
        game.getCurrentPlayer().itemUsedProperty().addListener((observable, oldValue, newValue) -> {
            Node currNode = view.getChildren().get(game.getCurrentPlayer().getCurrGamepiece().getPosition().getRow()
                    * view.getRowCount() + game.getCurrentPlayer().getCurrGamepiece().getPosition().getColumn());
            if (newValue) {
                showPossibleMoves((ImageView) currNode, false);
            }
        });

        //zweiter player reagiert nur hier drauf
        game.getNextPlayer().itemUsedProperty().addListener((observable, oldValue, newValue) -> {
            Node currNode = view.getChildren().get(game.getCurrentPlayer().getCurrGamepiece().getPosition().getRow()
                    * view.getRowCount() + game.getCurrentPlayer().getCurrGamepiece().getPosition().getColumn());
            if (newValue) {
                showPossibleMoves((ImageView) currNode, false);
            }
        });
    }

    private void showPossibleMoves(ImageView imageView, boolean gamepiece) {
        if (!game.getCurrentPlayer().getTurn() && gamepiece) return;
        int selRow = ((int) view.getChildren().get(view.getChildren().indexOf(imageView)).getLayoutY() / GameFieldView.CELL_SIZE);
        int selCol = ((int) view.getChildren().get(view.getChildren().indexOf(imageView)).getLayoutX() / GameFieldView.CELL_SIZE);
        Field selField = game.getGamefield().getField(selRow, selCol);
        // Perform the logic for handling the selected field here
        if (selField.getGamepiece() == null || !game.getCurrentPlayer().getOwnGamepieces().contains(selField.getGamepiece())) {
            for (int row = 0; row < GameFieldView.BOARD_SIZE; row++)
                for (int col = 0; col < GameFieldView.BOARD_SIZE; col++)
                    view.getChildren().get(row * view.getRowCount() + col).setOpacity(1.0);
            return;
        }
        if (gamepiece) {
            for (int row = 0; row < GameFieldView.BOARD_SIZE; row++)
                for (int col = 0; col < GameFieldView.BOARD_SIZE; col++) {
                    Node currNode = view.getChildren().get(row * view.getRowCount() + col);
                    if (selField.getGamepiece().possibleMoves(game).contains(game.getGamefield().getField(row, col))) {
                        if (game.getGamefield().getField(row, col).getGamepiece() == null
                                && game.getGamefield().getField(row, col).getItem() == null)
                            currNode.setOpacity(0.2);
                    } else currNode.setOpacity(1.0);
                }
        } else {
            // Anzeige der möglichen Platzierungen für eine Falle
            Field sel2Field = game.getGamefield().getField(selRow, selCol);
            if (sel2Field.getGamepiece() != null && sel2Field.getGamepiece().getInventory() instanceof Trap) {
                Trap trap = (Trap) sel2Field.getGamepiece().getInventory();
                for (int row = 0; row < GameFieldView.BOARD_SIZE; row++) {
                    for (int col = 0; col < GameFieldView.BOARD_SIZE; col++) {
                        Node currNode = view.getChildren().get(row * view.getRowCount() + col);
                        Field field = game.getGamefield().getField(row, col);
                        if (trap.possiblePlacements(game).contains(field)) {
                            if (field.getGamepiece() == null && field.getItem() == null) {
                                currNode.setOpacity(0.2);
                            }
                        } else {
                            currNode.setOpacity(1.0);
                        }
                    }
                }
            }
        }
    }

    public void clearMoves() {
        for (int row = 0; row < GameFieldView.BOARD_SIZE; row++)
            for (int col = 0; col < GameFieldView.BOARD_SIZE; col++) {
                Node currNode = view.getChildren().get(row * view.getRowCount() + col);
                currNode.setOpacity(1.0);
            }
    }

    public void clean() {
        for (int row = 0; row < GameFieldView.BOARD_SIZE; row++) {
            for (int col = 0; col < GameFieldView.BOARD_SIZE; col++) {
                Node currNode = view.getChildren().get(row * GameFieldView.BOARD_SIZE + col);

                Gamepiece gamepiece = game.getGamefield().getField(row, col).getGamepiece();
                Item item = game.getGamefield().getField(row, col).getItem();
                if(gamepiece != null || item != null){
                    continue;
                }
                if (currNode.getId().contains("white")) {
                    ((ImageView) currNode).setImage(new Image("files/pictures/white_placeholder.png"));
                    currNode.setId("white");
                } else {
                    ((ImageView) currNode).setImage(new Image("files/pictures/black_placeholder.jpg"));
                    currNode.setId("black");
                }
            }
        }
    }

}
