package UI.Elements.Game;

import Business.Gamepiece.Gamepiece;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class GameView extends HBox {
    public HBox playerInfoBox;
    public HBox player1Box;
    public ListView<Gamepiece> player1Gamepieces;
    public Button useItemPlayer1;
    public ImageView selPiece1;
    public ImageView selPiece1Item;
    public Label player1ItemDesc;

    public HBox player2Box;
    public ListView<Gamepiece> player2Gamepieces;
    public Button useItemPlayer2;
    public ImageView selPiece2;
    public ImageView selPiece2Item;
    public Label player2ItemDesc;

    public GameView() {
        playerInfoBox = new HBox();

        player1Box = new HBox();
        HBox player1ItemHBox = new HBox();
        VBox player1VBox = new VBox();

        selPiece1 = new ImageView();
        selPiece1.setFitHeight(40);
        selPiece1.setFitWidth(40);

        selPiece1Item = new ImageView();
        selPiece1Item.setFitHeight(30);
        selPiece1Item.setFitWidth(30);

        player1ItemDesc = new Label();
        useItemPlayer1 = new Button("Use Item");

        player1Gamepieces = new ListView<>();
        player1Gamepieces.setOrientation(Orientation.HORIZONTAL);
        player1Gamepieces.setPrefHeight(60);
        player1Gamepieces.setPrefWidth(228);

        player1ItemHBox.getChildren().addAll(selPiece1, selPiece1Item, player1ItemDesc, useItemPlayer1);
        player1VBox.getChildren().addAll(player1ItemHBox, player1Gamepieces);
        player1Box.getChildren().add(player1VBox);


        player2Box = new HBox();
        HBox player2ItemHBox = new HBox();
        VBox player2VBox = new VBox();

        selPiece2 = new ImageView();
        selPiece2.setFitHeight(40);
        selPiece2.setFitWidth(40);

        selPiece2Item = new ImageView();
        selPiece2Item.setFitHeight(30);
        selPiece2Item.setFitWidth(30);

        player2ItemDesc = new Label();
        useItemPlayer2 = new Button("Use Item");

        player2Gamepieces = new ListView<>();
        player2Gamepieces.setOrientation(Orientation.HORIZONTAL);
        player2Gamepieces.setPrefHeight(60);
        player2Gamepieces.setPrefWidth(228);

        player2ItemHBox.getChildren().addAll(selPiece2, selPiece2Item, player2ItemDesc, useItemPlayer2);
        player2VBox.getChildren().addAll(player2ItemHBox, player2Gamepieces);
        player2Box.getChildren().add(player2VBox);
        this.getChildren().addAll(player1Box, player2Box);

    }
}
