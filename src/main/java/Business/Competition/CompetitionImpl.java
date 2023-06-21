package Business.Competition;

import Business.GameLogic.Game;
import Business.Gamepiece.Gamepiece;
import javafx.beans.property.SimpleIntegerProperty;

public class CompetitionImpl implements Competition{
	private int time;
	private int helpCountCorrectAnswer;
	private SimpleIntegerProperty countCorrectAnswer;
	private SimpleIntegerProperty actTime;
	private int helpActTime;

	private boolean gameEnds;
	

	public CompetitionImpl(int time) {
		super();
		this.time = time;
		this.helpActTime = time;
		this.helpCountCorrectAnswer = 0;
		this.actTime = new SimpleIntegerProperty();
		this.countCorrectAnswer = new SimpleIntegerProperty();
		this.gameEnds = false;
	}


	@Override
	public void setPoints(Gamepiece gamepiece) {
		if (helpActTime == 0){
			gamepiece.setPunkte(helpCountCorrectAnswer);
		}
	}

	@Override
	public Gamepiece whoWin(Gamepiece gamepiece1,Gamepiece gamepiece2) {

		if(gamepiece1.getPunkte() == gamepiece2.getPunkte()) {
			return null;
		}
		if(gamepiece1.getPunkte() > gamepiece2.getPunkte()){
			return gamepiece1;
		}else{
			return gamepiece2;
		}
	}
	
	@Override
	public void startTimer() {
		Thread timerThread = new Thread(() -> {
			try {
				while (helpActTime != 0) {
					Thread.sleep(1000);
					helpActTime-=1;
					actTime.set(helpActTime);
				}

				if (helpActTime == 0){
					gameEnds = true;
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
