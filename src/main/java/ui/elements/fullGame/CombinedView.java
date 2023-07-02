package ui.elements.fullGame;

import ui.elements.extra.AlertPane;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class CombinedView extends BorderPane {

    public final Button nextTurn;
    public final Label playerTurn;
    public final MenuItem saveGame;
    public final MenuItem exitGame;
    public final AlertPane alertPane;
    final VBox topMenu;
    final VBox turns;


    CombinedView() {
        alertPane = new AlertPane();
        topMenu = new VBox();
        MenuBar options = new MenuBar();
        playerTurn = new Label();
        playerTurn.setRotate(-90);
        nextTurn = new Button("End Turn");
        nextTurn.setRotate(-90);
        Menu gameMenu = new Menu("Game");
        saveGame = new MenuItem("Save Game");
        exitGame = new MenuItem("Main Menu");
        gameMenu.getItems().addAll(saveGame, exitGame);
        options.getMenus().add(gameMenu);
        turns = new VBox(nextTurn,playerTurn);
        setLeft(turns);
        topMenu.getChildren().addAll(alertPane, options);
        topMenu.setTranslateY(-alertPane.getPrefHeight() - 1);
        setTop(topMenu);
        turns.setStyle("-fx-alignment: center");
        playerTurn.setStyle("-fx-font-size: 20px");
        turns.setSpacing(120);
        setStyle("-fx-background-color: #ececec");
    }
}