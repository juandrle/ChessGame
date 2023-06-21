package UI.Elements.Competition.ClickEventGame;

import java.util.List;

import Business.Competition.ReactionGame;
import Business.Competition.Circle.ShrinkingCircle;
import Business.GameLogic.Game;
import UI.ViewController;
import UI.Presentation.MonsterApplication;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class ReactionGameViewController extends ViewController<MonsterApplication> {
	private ReactionGame reactionGame;
	private ReactionGameView mainView;
	private List<ShrinkingCircle> circles;
	private Label time;
	private Label score;
	private Boolean endGame = false;
	private ShrinkingCircle currentCircle;

	private int countTimeForNextCircle = 0;

	public ReactionGameViewController(ReactionGame reactionGame, MonsterApplication application, Game game) {
		super(application);
		this.reactionGame = reactionGame;
		this.mainView = new ReactionGameView();
		this.circles = mainView.getCircles();
		this.time = mainView.time;
		this.score = mainView.score;

		// steht hier weil sich initialize() immer neu aufruft
		reactionGame.startTimer();
		score.setText("Punktestand: " + reactionGame.getHelpCountCorrectAnswer());

		initialize();
	}

	public void initialize() {

		if (countTimeForNextCircle == 3){
			clickCircle();
			countTimeForNextCircle = 0;
		}

		reactionGame.getActTime().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				Platform.runLater(() -> {
					System.out.println(countTimeForNextCircle);
					countTimeForNextCircle +=1;
					time.setText(newValue.toString());

					if (newValue.intValue() == 0) {
						endGame = true;
						time.setText("Zeit ist abgelaufen!");
					}
				});
			}
		});

		reactionGame.getCountCorrectAnswer().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				Platform.runLater(() -> {
					score.setText("Punktestand: " + newValue.toString());
				});

			}
		});

	}


// Für jeden Kreis das Klick-Ereignis registrieren
	public void clickCircle(){
		if (endGame == false) {
			for (ShrinkingCircle circle : circles) {
				currentCircle = circle;
				circle.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						reactionGame.setHelpCountCorrectAnswer(1);
						mainView.removeCircle(currentCircle);
						mainView.createCircle();

						initialize();

					}
				});
			}
		}
	}

	public BorderPane getRootView() {
		return mainView.getBorderPane();
	}
}
