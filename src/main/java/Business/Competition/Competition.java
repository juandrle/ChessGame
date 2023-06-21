package Business.Competition;

import Business.Gamepiece.Gamepiece;
import javafx.beans.property.SimpleBooleanProperty;

public interface Competition {
	void startTimer();
	void setPoints(Gamepiece gamepiece);
	Gamepiece whoWin(Gamepiece gamepiece1, Gamepiece gamepiece2);
	public SimpleBooleanProperty gameEndsProperty();
}
