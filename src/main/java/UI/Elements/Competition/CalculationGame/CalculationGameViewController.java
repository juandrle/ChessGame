package UI.Elements.Competition.CalculationGame;

import Business.Competition.CalculatorGame;
import Business.GameLogic.Game;
import UI.Presentation.MonsterApplication;
import UI.ViewController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
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
					getClass().getResource("/views/CalculationGameView.fxml"));
			loader.setController(this);
			rootView = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	@Override
	public void initialize() {

		clearButton.addEventHandler(ActionEvent.ACTION, event -> textField.clear());

		textField.setOnKeyPressed(event -> {
			KeyCode keyCode = event.getCode();
			System.out.println(calculationGame.keyPermitted(keyCode));
			if (calculationGame.keyPermitted(keyCode)) {
				processInput();
			}
		});

		processButton.addEventHandler(ActionEvent.ACTION, event -> {
			processInput();
		});

		calculationGame.getActTime().addListener((observable, oldValue, newValue) -> Platform.runLater(() -> {
			time.setText(newValue.toString());
			if (newValue.intValue() == 0) {
				time.setText("Zeit ist abgelaufen!");
				endGame = true;
			}
		}));

		calculationGame.getCountCorrectAnswer().addListener((observable, oldValue, newValue) -> Platform.runLater(() -> {
			score.setText("Score: " + newValue.toString());
		}));

		// Eigenscahften die am Anfang bestimmt werden:
		// Mathe aufgaben
		mathProblem = showNextTask();
		// startet zeit
		calculationGame.startTimer();
		// punktestand: 0
		score.setText("Punktestand: " + calculationGame.getHelpCountCorrectAnswer());
		message.setVisible(false);
	}

	public void processInput(){
		String inputText = textField.getText();
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
			message.setText("Keine weiteren Eingaben moeglich!");
		}
	}

	private String showNextTask() {
		Map<String, String> task = calculationGame.getTask();
		List<String> taskKeys = new ArrayList<>(task.keySet());
		Collections.shuffle(taskKeys);

		String nextTask = null;
		for (String key : taskKeys) {
			if (!key.isEmpty()) {
				nextTask = key;
				break;
			}
		}

		if (nextTask != null) {
			mathProblems.setText(nextTask);
		} else {
			message.setText("Keine Aufgaben mehr!");
		}
		return nextTask;
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
