package Business.GameLogic;

public interface Game {


    public void switchPlayersTurn();

    public Game startGame();

    public Game loadGame();

    public void safeGame();

    public void exitGame();

    public void runGame();
}
