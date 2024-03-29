package ui.elements.competition.clickEventGame;

import java.util.ArrayList;
import java.util.List;

import business.competition.ReactionGame;
import business.competition.circle.ShrinkingCircle;
import business.gameLogic.Game;
import ui.ViewController;
import ui.presentation.MonsterApplication;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class ReactionGameViewController extends ViewController<MonsterApplication> {
    private final ReactionGame reactionGame;
    private final ReactionGameView mainView;
    private final List<ShrinkingCircle> circles;
    private final Label time;
    private final Label score;
    private Boolean endGame = false;
    private ShrinkingCircle currentCircle;

    public ReactionGameViewController(ReactionGame reactionGame, MonsterApplication application, Game game) {
        super(application);
        rootView = new ReactionGameView();
        this.mainView = (ReactionGameView) rootView;
        this.reactionGame = reactionGame;
		circles = new ArrayList<>();
        this.time = mainView.time;
        this.score = mainView.score;

        // steht hier weil sich initialize() immer neu aufruft
        reactionGame.startTimer();
        score.setText("Score: " + reactionGame.getHelpCountCorrectAnswer());

        initialize();
    }
	@Override
    public void initialize() {

        reactionGame.getActTime().addListener((observable, oldValue, newValue) -> Platform.runLater(() -> {
			time.setText(newValue.toString());
			if (newValue.intValue() % 2 == 0) {
				createCircle();
				clickCircle();
			}
			if (newValue.intValue() == 0) {
				endGame = true;
				time.setText("Zeit ist abgelaufen!");
			}
		}));

        reactionGame.getCountCorrectAnswer().addListener((observable, oldValue, newValue) ->
				Platform.runLater(() -> score.setText("Score: " + newValue.toString())
		));

    }


    // Für jeden Kreis das Klick-Ereignis registrieren
    public void clickCircle() {
        if (!endGame) {
            for (ShrinkingCircle circle : circles) {
                currentCircle = circle;
                circle.setOnMouseClicked(event -> {
					reactionGame.setHelpCountCorrectAnswer(1);
					removeCircle(currentCircle);

				});
            }
        }
    }
	public void createCircle() {


		ShrinkingCircle circle = new ShrinkingCircle(60, Color.color(0.50f,1f,0.16f));
		circles.add(circle);

		// Zufällige Position für den Kreis innerhalb der Pane
		double circleX = Math.random() * (mainView.getWidth() - circle.getRadius() * 2) + circle.getRadius();
		double circleY = Math.random() * (mainView.getHeight() - circle.getRadius() * 2) + circle.getRadius();

		// Überprüfen, ob der Kreis zu nah am oberen Rand liegt
		if (circleY < circle.getRadius()) {
			circleY = circle.getRadius() * 2;
		}

		// Überprüfen, ob der Kreis zu nah am linken Rand liegt
		if (circleX < circle.getRadius()) {
			circleX = circle.getRadius() * 2;
		}

		circle.setCenterX(circleX);
		circle.setCenterY(circleY);

		mainView.getChildren().add(circle);
		circles.add(circle);

		// Animation starten
		circle.render();
	}
	public void removeCircle(ShrinkingCircle circle) {
		circles.remove(circle);
		mainView.getChildren().remove(circle);
	}
}
