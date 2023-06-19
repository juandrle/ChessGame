package UI.Elements.Game;

import Business.Gamepiece.Gamepiece;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;


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
        player1Gamepieces = new ListView<>();
        useItemPlayer1 = new Button("Use Item");
        player1Box.getChildren().addAll(player1Gamepieces, useItemPlayer1);


        player2Box = new HBox();
        player2Gamepieces = new ListView<>();
        useItemPlayer2 = new Button("Use Item");
        player2Box.getChildren().addAll(player2Gamepieces, useItemPlayer2);
        playerInfoBox.getChildren().addAll(player1Box,player2Box);
        playerInfoBox.setStyle("--fxbackground-color: blue");
        setBottom(playerInfoBox);
    }
}
