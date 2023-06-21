package UI.Elements.Competition.ClickEventGame;

import java.util.List;

import Business.Competition.Circle.ShrinkingCircle;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;

public class ReactionGameView extends BorderPane{
	Pane pane;
	Label time;
	Label score;

	public ReactionGameView() {
		pane = new Pane();
		time = new Label("Time");
		score = new Label("Score");

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

		//createCircle();
	}


}