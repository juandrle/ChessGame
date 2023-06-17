package UI.Elements.GameField;

import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameFieldView extends GridPane {
    public static final int BOARD_SIZE = 8;
    public static final int CELL_SIZE = 80;

    private ImageView dragImageView;

    public GameFieldView() {
        setPrefSize(CELL_SIZE * BOARD_SIZE, CELL_SIZE * BOARD_SIZE);
        setStyle("-fx-background-color: #ffffff;");

        // Create the chessboard cells
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                boolean isWhite = (row + col) % 2 == 0;
                String color = isWhite ? "#FFFFFF" : "#2e2d2d";


                Rectangle cell = new Rectangle(CELL_SIZE, CELL_SIZE);
                cell.setFill(Color.web(color));

                // Add the cell to the grid
                add(cell, col, row);
            }
        }
    }
}
