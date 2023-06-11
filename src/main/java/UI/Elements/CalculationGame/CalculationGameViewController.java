package UI.Elements.CalculationGame;

import Business.Competition.CalculatorGame;
import Business.GameLogic.Game;
import UI.Presentation.MonsterApplication;
import UI.ViewController;

import java.io.IOException;
import java.util.Map;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.event.EventHandler;
import javafx.application.Platform;

public class CalculationGameViewController extends ViewController<MonsterApplication> {

	private CalculatorGame calculationGame;

	private BorderPane rootView;

	@FXML
	private Button processButton;

	@FXML
	private Button clearButton;

	@FXML
	private Label mathProblems;

	@FXML
	private Label time;

	@FXML
	private TextField textField;

	@FXML
	private Label message;

	@FXML
	private Label score;

	private String mathProblem;
	private Boolean endGame = false;

	public CalculationGameViewController(CalculatorGame calculationGame, MonsterApplication application, Game game) {
		super(application);
		this.calculationGame = calculationGame;
		try {
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("/MonsterSchach/src/main/java/UI/Elements/CalculationGame/CalculationGameView.fxml"));
			loader.setController(this);
			rootView = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void initialize() {

		clearButton.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				textField.clear();
			}
		});

		processButton.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String inputText = textField.getText();
				System.out.println("Input text: " + inputText);
				String result = getResultOfMathProblem(mathProblem);
				if (endGame == false) {
					if (inputText.equals(result)) {
						message.setText("Richtig!");
						calculationGame.setHelpCountCorrectAnswer(1);
						removeMathProblem(mathProblem);
						mathProblem = showNextTask();
						textField.clear();
					} else {
						message.setText("Falsch!");
					}
					message.setVisible(true);
				} else {
					message.setText("Keine weiteren Eingaben möglich!");
				}
			}
		});

		calculationGame.getActTime().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				Platform.runLater(() -> {
					time.setText(newValue.toString());
					if (newValue.intValue() == 0) {
						message.setText("Zeit ist abgelaufen!");
						endGame = true;
					}
				});
			}
		});

		calculationGame.getCountCorrectAnswer().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				Platform.runLater(() -> {
					score.setText("Punktestand: " + newValue.toString());
				});

			}
		});

		// Eigenscahften die am Anfang bestimmt werden:
		// Mathe aufgaben
		mathProblem = showNextTask();
		// startet zeit
		calculationGame.startTimer();
		// punktestand: 0
		score.setText("Punktestand: " + calculationGame.getHelpCountCorrectAnswer());
		message.setVisible(false);
	}

	private String showNextTask() {
		Map<String, String> task = calculationGame.getTask();
		String nextTask = null;
		if (!task.isEmpty()) {
			nextTask = task.keySet().iterator().next();
			mathProblems.setText(nextTask);
			return nextTask;
		} else {
			message.setText("Keine Aufgaben mehr!");
			return null;
		}
	}

	private String getResultOfMathProblem(String key) {
		Map<String, String> task = calculationGame.getTask();
		return task.get(key);
	}

	private void removeMathProblem(String key) {
		Map<String, String> task = calculationGame.getTask();
		task.remove(key);

	}
	//um den Root-Knoten des CalculationGameViewController zu erhalten und in der Main Klasse zu verwenden
	public BorderPane getRootView() {
		return rootView;
	}

}
