package business.competition;

import business.gamepiece.Gamepiece;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class CompetitionImpl implements Competition {
    private int helpCountCorrectAnswer;
    private final SimpleIntegerProperty countCorrectAnswer;
    private SimpleIntegerProperty actTime;
    private int helpActTime;

    private final SimpleBooleanProperty gameEnds;


    public CompetitionImpl(int time) {
        super();
        this.helpActTime = time;
        this.helpCountCorrectAnswer = 0;
        this.actTime = new SimpleIntegerProperty();
        this.countCorrectAnswer = new SimpleIntegerProperty();
        gameEnds = new SimpleBooleanProperty(false);
    }


    @Override
    public void setPoints(Gamepiece gamepiece) {
        if (helpActTime == 0) {
            gamepiece.setPoints(helpCountCorrectAnswer);
        }
    }

    public SimpleBooleanProperty gameEndsProperty() {
        return gameEnds;
    }

    @Override
    public Gamepiece whoWin(Gamepiece gamepiece1, Gamepiece gamepiece2) {
        if (gamepiece1.getPoints() == gamepiece2.getPoints()) {
            return null;
        } else if (gamepiece1.getPoints() > gamepiece2.getPoints()) {
            return gamepiece1;
        } else {
            return gamepiece2;
        }
    }

    @Override
    public void startTimer() {
        Thread timerThread = new Thread(() -> {
            try {
                while (helpActTime != 0) {
                    Thread.sleep(1000);
                    helpActTime -= 1;
                    actTime.set(helpActTime);
                }

                this.gameEnds.set(true);
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
