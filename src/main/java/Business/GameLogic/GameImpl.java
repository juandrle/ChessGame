package Business.GameLogic;

public class GameImpl implements Game{

    private int turnCount = 0;
    private final Gamefield gamefield;
    private Player currPlayer = null;
    public GameImpl(){
        this.gamefield = new GamefieldImpl();
    }

    public void setTurnCount(int tc){
        this.turnCount = tc;
    }

    public int getTurnCount(){
        return this.turnCount;
    }

    public void switchPlayersTurn(){
        if (this.currPlayer == null || this.currPlayer.equals(this.gamefield.getPlayer2()))
            this.currPlayer = this.gamefield.getPlayer1();
        else this.currPlayer = this.gamefield.getPlayer2();
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
        return this.gamefield;
    }
}
