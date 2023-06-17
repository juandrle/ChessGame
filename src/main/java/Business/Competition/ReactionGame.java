package Business.Competition;
import javafx.beans.property.SimpleIntegerProperty;

public class ReactionGame extends CompetitionImpl{
	private int helpCountCorrectAnswer;
	private SimpleIntegerProperty countCorrectAnswer;
	private SimpleIntegerProperty actTime;
	private int helpActTime;
	
	public ReactionGame(int time) {
		super(time);
		this.helpActTime = time;
		this.helpCountCorrectAnswer = 0;
		this.actTime = new SimpleIntegerProperty();
		this.countCorrectAnswer = new SimpleIntegerProperty();
		
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
	
	
	
	public void setHelpActTime(int helpActTime) {
		this.helpActTime = helpActTime;
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
}

