package Business.GameLogic;

public class GameImpl implements Game{
    private final Gamefield gamefield;
    private Player currPlayer = null;
    public GameImpl(){
        this.gamefield = new GamefieldImpl();
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

    @Override
    public Player getCurrentPlayer() {
        return this.currPlayer;
    }
}
