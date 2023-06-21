package UI.Elements.FullGame;

import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CombinedView extends BorderPane {

    public Button nextTurn;
    public Label playerTurn;
    public MenuItem saveGame;
    public MenuItem loadGame;
    public MenuItem exitGame;


    CombinedView() {
        VBox topMenu = new VBox();
        MenuBar options = new MenuBar();
        playerTurn = new Label();
        nextTurn = new Button("End Turn");
        Menu gameMenu = new Menu("Game");
        saveGame = new MenuItem("Save Game");
        loadGame = new MenuItem("Load Game");
        exitGame = new MenuItem("Main Menu");
        gameMenu.getItems().addAll(saveGame, loadGame, exitGame);
        options.getMenus().add(gameMenu);
        HBox turns = new HBox(nextTurn,playerTurn);
        topMenu.getChildren().addAll(options, turns);
        setTop(topMenu);
        turns.setStyle("-fx-alignment: center");
        turns.setTranslateY(30);
        playerTurn.setTranslateY(10);
        playerTurn.setStyle("-fx-font-size: 20px");
        turns.setSpacing(120);
        setStyle("-fx-background-color: #c9c9c9");
    }
}