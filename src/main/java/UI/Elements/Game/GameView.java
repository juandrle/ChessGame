package UI.Elements.Game;

import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class GameView extends AnchorPane {
    public Button demoGameField;
    public Button demoCalcGame;

    public GameView(){
        demoGameField = new Button("Demo GamefieldView");
        demoCalcGame = new Button("Demo CalculationGameView");
        demoCalcGame.setTranslateY(40);
        this.getChildren().addAll(demoGameField, demoCalcGame);
    }
}
