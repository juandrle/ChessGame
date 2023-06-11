package Business.Competition;

import java.util.HashMap;
import java.util.Map;

import javafx.beans.property.SimpleIntegerProperty;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CalculatorGame extends CompetitionImpl {

	private Map<String, String> task;
	private int helpCountCorrectAnswer;
	private SimpleIntegerProperty countCorrectAnswer;
	private SimpleIntegerProperty actTime;
	private int helpActTime;

	public CalculatorGame(int time) {
		super(time);
		this.helpCountCorrectAnswer = 0;
		this.helpActTime = time;
		this.actTime = new SimpleIntegerProperty();
		this.countCorrectAnswer = new SimpleIntegerProperty();
		task = new HashMap<>();
		fillTask();
	}

	public void fillTask() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/files/mathProblems.txt"));
			String line;
			try {
				while ((line = reader.readLine()) != null) {
					String[] parts = line.split("=");
					task.put(parts[0], parts[1].trim());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void startTimer() {
		Thread timerThread = new Thread(() -> {
			try {
				while (helpActTime != 0) {
					Thread.sleep(1000);
					helpActTime-=1;
					actTime.set(helpActTime);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		timerThread.start();
	}

	public SimpleIntegerProperty getActTime() {
		return actTime;
	}

	public void setActTime(SimpleIntegerProperty actTime) {
		this.actTime = actTime;
	}
	public int getHelpCountCorrectAnswer() {
		return helpCountCorrectAnswer;
	}

	public void setHelpCountCorrectAnswer(int helpCountCorrectAnswer) {
		this.helpCountCorrectAnswer += helpCountCorrectAnswer;
		countCorrectAnswer.set(this.helpCountCorrectAnswer);
	}

	public SimpleIntegerProperty getCountCorrectAnswer() {
		return countCorrectAnswer;
	}

	public Map<String, String> getTask() {
		return task;
	}
	

}