package Business.GameLogic;

import java.io.IOException;

public interface Game {


    public void switchPlayersTurn();

    public Game startGame();

    public Game loadGame();

    public void saveGame() throws IOException;

    public void exitGame();

    public void runGame();
    public Gamefield getGamefield();

    public Player getCurrentPlayer();
}
