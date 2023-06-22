package UI.Elements.GameField;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;


public class GameFieldView extends GridPane {
    public static final int BOARD_SIZE = 8;
    public static final int CELL_SIZE = 60;


    public GameFieldView() {

        setPrefSize(CELL_SIZE * BOARD_SIZE, CELL_SIZE * BOARD_SIZE);

        setMaxSize(CELL_SIZE*BOARD_SIZE, CELL_SIZE * BOARD_SIZE);
        setStyle("-fx-background-color: #c9c9c9;");

        // Create the chessboard cells
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                boolean isWhite = (row + col) % 2 == 0;
                String filename = isWhite ? "files/pictures/white_placeholder.png" : "files/pictures/black_placeholder.jpg";
                String id = isWhite ? "white" : "black";

                ImageView imageView = new ImageView(new Image(filename));
                imageView.setId(id);
                imageView.setFitHeight(CELL_SIZE);
                imageView.setFitWidth(CELL_SIZE);

                // Add the cell to the grid
                add(imageView, col, row);
            }
        }
        setGridLinesVisible(true);


    }
}
