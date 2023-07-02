package business.gameLogic;

import business.gamepiece.Gamepiece;

import java.io.IOException;
import java.util.Map;

public interface Game {

    void setTurnCount(int count);
    void switchPlayersTurn();

    Game startGame();

    void loadGame(String filepath) throws IOException;

    void saveGame() throws IOException;

    Gamefield getGamefield();

    Player getCurrentPlayer();

    Player getNextPlayer();
    void newGame();
    Map<Gamepiece, Integer> getEffectedGamepieces();

    int getTurnCount();
}
