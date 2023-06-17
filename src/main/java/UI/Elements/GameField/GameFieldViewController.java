package UI.Elements.GameField;

import Business.GameLogic.Game;
import UI.Presentation.MonsterApplication;
import UI.ViewController;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;


public class GameFieldViewController extends ViewController<MonsterApplication> {
    private final GridPane view;
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
        for (int row = 0; row < view.getRowCount(); row++) {
            for (int col = 0; col < view.getColumnCount(); col++) {
                ImageView imageView = new ImageView(); // Create ImageView instance
                imageView.setFitWidth(GameFieldView.CELL_SIZE);
                imageView.setFitHeight(GameFieldView.CELL_SIZE);
                view.add(imageView, col, row);
                if (game.getGamefield().getField(row, col).getGamepiece() != null)
                    if (col < 3)
                        switch (game.getGamefield().getField(row, col).getGamepiece().getRank()) {
                            case 0 -> imageView.setImage(new Image("files/pictures/gamepieces/PawnPlayer1.png"));
                            case 1 -> imageView.setImage(new Image("files/pictures/gamepieces/TowerPlayer1.png"));
                            case 2 -> imageView.setImage(new Image("files/pictures/gamepieces/QueenPlayer1.png"));
                        }
                    else
                        switch (game.getGamefield().getField(row, col).getGamepiece().getRank()) {
                            case 0 -> imageView.setImage(new Image("files/pictures/gamepieces/PawnPlayer2.png"));
                            case 1 -> imageView.setImage(new Image("files/pictures/gamepieces/TowerPlayer2.png"));
                            case 2 -> imageView.setImage(new Image("files/pictures/gamepieces/QueenPlayer2.png"));
                        }
                    if (game.getGamefield().getField(row, col).getItem() != null)
                        imageView.setImage(new Image("files/pictures/Item.png"));

                // Add drag and drop event handlers to the ImageView
                addDragEventHandlers(imageView);
            }
        }

    }

    private void addDragEventHandlers(ImageView imageView) {
        imageView.setOnDragDetected(event -> {
            /*
             * This method is called when the drag gesture is detected on the ImageView.
             * Here, you can start the drag operation and set the appropriate content to be dragged.
             * For example, you can set a custom data format and store the image information.
             */
            Dragboard dragboard = imageView.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putString("Some custom data"); // Set your custom data here
            dragboard.setContent(content);

            event.consume();
        });

        imageView.setOnDragOver(event -> {
            /*
             * This method is called when the dragged object is over the ImageView.
             * You can check the dragged data and decide whether to accept the drop or not.
             */
            if (event.getGestureSource() != imageView && event.getDragboard().hasString()) {
                // Allow the drop
                event.acceptTransferModes(TransferMode.MOVE);
            }

            event.consume();
        });

        imageView.setOnDragDropped(event -> {
            /*
             * This method is called when the dragged object is dropped on the ImageView.
             * Here, you can handle the drop action and retrieve the dropped data.
             */
            Dragboard dragboard = event.getDragboard();
            boolean success = false;

            if (dragboard.hasString()) {
                // Retrieve and process the dropped data
                String droppedData = dragboard.getString();
                // Perform necessary actions based on the dropped data

                success = true;
            }

            event.setDropCompleted(success);
            event.consume();
        });

        imageView.setOnDragDone(event -> {
            /*
             * This method is called when the drag and drop operation is complete.
             * Here, you can perform any cleanup or additional actions after the drop.
             */
            if (event.getTransferMode() == TransferMode.MOVE) {
                // Perform any necessary cleanup or actions after the drop
            }

            event.consume();
        });
    }

}

