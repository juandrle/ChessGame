package Business.Competition;

import Business.Gamepiece.Gamepiece;

public interface Competition {
	void startTimer();
	void setPoints(Gamepiece gamepiece);
	Gamepiece whoWin(Gamepiece gamepiece1, Gamepiece gamepiece2);
}
