package UI.Elements.Game;

import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class GameView extends AnchorPane {
    public Button demoGameField;
    public Button demoCalcGame;
    public Button demoReacGame;

    public GameView(){
        demoGameField = new Button("Demo GamefieldView");
        demoCalcGame = new Button("Demo CalculationGameView");
        demoReacGame = new Button("Demo ReactionGameView");
        demoCalcGame.setTranslateY(40);
        demoReacGame.setTranslateY(80);
        this.getChildren().addAll(demoGameField, demoCalcGame, demoReacGame);
    }
}
