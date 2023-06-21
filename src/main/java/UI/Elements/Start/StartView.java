package UI.Elements.Start;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class StartView extends StackPane {

    public Button demoCalcGame;
    public Button demoReacGame;
    public Button demoCombined;

    public StartView() {

        Label header = new Label("Welcome to our Monsterappdemo!");
        header.setId("header");
        Label authors = new Label("Authors: Sadia, David and Julian");
        authors.setStyle("-fx-font-size: 15px");
        demoCalcGame = new Button("Demo CalculationGameView");
        demoReacGame = new Button("Demo ReactionGameView");

        demoCombined = new Button("Demo CombinedView");
        VBox menu = new VBox(header, authors, demoCalcGame, demoReacGame,demoCombined);
        this.getChildren().add(menu);
        menu.setAlignment(Pos.CENTER);
        menu.setStyle("-fx-background-color: #c9c9c9");
        menu.setSpacing(20);
    }
}
