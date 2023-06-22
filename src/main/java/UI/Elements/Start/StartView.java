package UI.Elements.Start;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class StartView extends StackPane {

    public Button loadGame;
    public Button startNewGame;

    public StartView() {

        Label header = new Label("Welcome to our Monsterappdemo!");
        header.setId("header");
        Label authors = new Label("Authors: Sadia, David and Julian");
        authors.setStyle("-fx-font-size: 15px");
        loadGame = new Button("Load Game");

        startNewGame = new Button("Start new Game");
        VBox menu = new VBox(header, authors, startNewGame, loadGame);
        this.getChildren().add(menu);
        menu.setId("menu");

    }
}
