package UI.FullGame;

import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
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
        topMenu.getChildren().addAll(options, nextTurn, playerTurn);
        setTop(topMenu);
    }
}