package business.competition;

import business.gamepiece.Gamepiece;
import javafx.beans.property.SimpleBooleanProperty;

public interface Competition {
	void startTimer();
	void setPoints(Gamepiece gamepiece);
	Gamepiece whoWin(Gamepiece gamepiece1, Gamepiece gamepiece2);
	SimpleBooleanProperty gameEndsProperty();
}
