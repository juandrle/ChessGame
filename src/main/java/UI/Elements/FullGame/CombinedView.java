package UI.Elements.FullGame;

import UI.Elements.Extra.AlertPane;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CombinedView extends BorderPane {

    public Button nextTurn;
    public Label playerTurn;
    public MenuItem saveGame;
    public MenuItem exitGame;
    public AlertPane alertPane;
    VBox topMenu;


    CombinedView() {
        alertPane = new AlertPane();
        topMenu = new VBox();
        MenuBar options = new MenuBar();
        playerTurn = new Label();
        nextTurn = new Button("End Turn");
        Menu gameMenu = new Menu("Game");
        saveGame = new MenuItem("Save Game");
        exitGame = new MenuItem("Main Menu");
        gameMenu.getItems().addAll(saveGame, exitGame);
        options.getMenus().add(gameMenu);
        HBox turns = new HBox(nextTurn,playerTurn);
        topMenu.getChildren().addAll(alertPane, options, turns);
        turns.setPadding(new Insets(50,0,0,0));
        nextTurn.setTranslateY(10);
        playerTurn.setTranslateY(20);
        topMenu.setTranslateY(-alertPane.getPrefHeight() - 1);
        setTop(topMenu);
        turns.setStyle("-fx-alignment: center");
        playerTurn.setStyle("-fx-font-size: 20px");
        turns.setSpacing(120);
        setStyle("-fx-background-color: #ececec");
    }
}