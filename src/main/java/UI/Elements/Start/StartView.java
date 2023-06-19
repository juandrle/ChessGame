package UI.Elements.Start;

import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class StartView extends AnchorPane {
    public Button demoGameField;
    public Button demoCalcGame;
    public Button demoReacGame;
    public Button demoGame;

    public StartView(){
        demoGameField = new Button("Demo GamefieldView");
        demoCalcGame = new Button("Demo CalculationGameView");
        demoReacGame = new Button("Demo ReactionGameView");
        demoGame = new Button(" Demo GameView");
        demoCalcGame.setTranslateY(40);
        demoReacGame.setTranslateY(80);
        demoGame.setTranslateY(120);
        this.getChildren().addAll(demoGameField, demoCalcGame, demoReacGame, demoGame);
    }
}
