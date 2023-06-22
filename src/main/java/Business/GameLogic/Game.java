package Business.GameLogic;

import java.io.File;
import java.io.IOException;

public interface Game {


    void switchPlayersTurn();

    Game startGame();

    Game loadGame(File file) throws IOException;

    void saveGame() throws IOException;

    void exitGame();

    void runGame();

    Gamefield getGamefield();

    Player getCurrentPlayer();

    Player getNextPlayer();
}
