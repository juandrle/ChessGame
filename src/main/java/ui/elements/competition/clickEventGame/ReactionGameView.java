package ui.elements.competition.clickEventGame;


import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;

public class ReactionGameView extends BorderPane{
	final Pane pane;
	final Label time;
	final Label score;

	public ReactionGameView() {
		pane = new Pane();
		time = new Label("Time");
		time.setStyle("-fx-font-size: 25");
		score = new Label("Score: ");
		score.setStyle("-fx-font-size: 25");

		VBox bottomVBox = new VBox();
		HBox widthFromRightToScore = new HBox();
		HBox bottomHBox = new HBox();
		VBox widthBetween = new VBox();
		VBox timeVBox = new VBox();

		widthBetween.setPrefWidth(230);
		widthFromRightToScore.setPrefWidth(45);
		timeVBox.getChildren().add(time);
		bottomHBox.getChildren().addAll(widthFromRightToScore, score, widthBetween, timeVBox);
		bottomHBox.setPrefHeight(40);
		bottomVBox.getChildren().add(bottomHBox);
		this.setBottom(bottomVBox);

	}


}