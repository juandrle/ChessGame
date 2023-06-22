package Business.GameLogic;

public class GameImpl implements Game{

    private int turnCount = 0;
    private final Gamefield gamefield;
    private Player currPlayer = null;
    private Player nextPlayer = null;
    public GameImpl(){
        this.gamefield = new GamefieldImpl();
        switchPlayersTurn();
    }

    public void switchPlayersTurn(){
        if (this.currPlayer == null || this.currPlayer.equals(this.gamefield.getPlayer2())) {
            this.currPlayer = this.gamefield.getPlayer1();
            this.nextPlayer = this.gamefield.getPlayer2();
            this.gamefield.getPlayer1().setTurn(true);
        }
        else {
            this.currPlayer = this.gamefield.getPlayer2();
            this.nextPlayer = this.gamefield.getPlayer1();
            this.gamefield.getPlayer2().setTurn(true);
        }
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

    public Player getNextPlayer() {
        return this.nextPlayer;
    }
}
