package Business.GameLogic;

public class GameImpl implements Game{

    private int turnCount = 0;
    private final Gamefield gamefield;
    public GameImpl(){
        gamefield = new GamefieldImpl();
    }

    public void setTurnCount(int tc){
        turnCount = tc;
    }

    public int getTurnCount(){
        return turnCount;
    }

    public void switchPlayersTurn(){
    }

    public Game startGame(){
        return null;
    }

    public Game loadGame(){
        return null;
    }

    public void saveGame(){

    }

    public void exitGame(){

    }

    public void runGame(){

    }
    public Gamefield getGamefield() {
        return gamefield;
    }
}
