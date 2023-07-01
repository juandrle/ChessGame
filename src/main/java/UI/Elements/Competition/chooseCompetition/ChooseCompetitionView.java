package UI.Elements.Competition.chooseCompetition;

import UI.Elements.GameField.GameFieldView;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class ChooseCompetitionView extends BorderPane {
    public Button calcGame;
    public Button clickGame;

    public ChooseCompetitionView(){
        Label header = new Label("Please choose your competition");
        header.setId("header");
        calcGame = new Button("Play the calculation game");
        clickGame = new Button("Play the click Event");
        VBox menu = new VBox(header,calcGame,clickGame);
        setCenter(menu);
        menu.setAlignment(Pos.CENTER);
        menu.setSpacing(15);
        this.setPrefSize(GameFieldView.CELL_SIZE* GameFieldView.BOARD_SIZE,
                        GameFieldView.CELL_SIZE* GameFieldView.BOARD_SIZE);
        this.setMaxSize(GameFieldView.CELL_SIZE* GameFieldView.BOARD_SIZE,
                        GameFieldView.CELL_SIZE* GameFieldView.BOARD_SIZE);

    }
}
