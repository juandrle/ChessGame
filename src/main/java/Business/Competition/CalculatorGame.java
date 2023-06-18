package Business.Competition;

import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CalculatorGame extends CompetitionImpl {

	private Map<String, String> task;

	public CalculatorGame(int time) {
		super(time);
		task = new HashMap<>();
		fillTask();
	}

	public void fillTask() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("src/application/Resources/mathProblems.txt"));
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

	public Map<String, String> getTask() {
		return task;
	}

}