package Business.GameLogic;

import java.io.IOException;

public interface Game {


    void switchPlayersTurn();

    Game startGame();

    Game loadGame();

    void saveGame() throws IOException;

    void exitGame();

    void runGame();

    Gamefield getGamefield();

    Player getCurrentPlayer();

    Player getNextPlayer();
}
