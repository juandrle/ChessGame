package UI.Elements.Game;

import Business.Gamepiece.Gamepiece;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class GameView extends BorderPane {
    public HBox playerInfoBox;
    public HBox player1Box;
    public ListView<Gamepiece> player1Gamepieces;
    public Button useItemPlayer1;

    public HBox player2Box;
    public ListView<Gamepiece> player2Gamepieces;
    public Button useItemPlayer2;

    public GameView(){
        playerInfoBox = new HBox();
        player1Box = new HBox();
        VBox player1VBox = new VBox();
        player1Gamepieces = new ListView<>();
        player1Gamepieces.setOrientation(Orientation.HORIZONTAL);
        player1Gamepieces.setPrefHeight(60);
        useItemPlayer1 = new Button("Use Item");
        player1VBox.getChildren().addAll(useItemPlayer1, player1Gamepieces);
        player1Box.getChildren().add(player1VBox);


        player2Box = new HBox();
        VBox player2VBox = new VBox();
        player2Gamepieces = new ListView<>();
        player2Gamepieces.setOrientation(Orientation.HORIZONTAL);
        player2Gamepieces.setPrefHeight(60);
        useItemPlayer2 = new Button("Use Item");
        player2VBox.getChildren().addAll(useItemPlayer2, player2Gamepieces);
        player2Box.getChildren().add(player2VBox);
        playerInfoBox.getChildren().addAll(player1Box,player2Box);
        playerInfoBox.setStyle("--fxbackground-color: blue");
        setBottom(playerInfoBox);
    }
}
