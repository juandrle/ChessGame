package Business.GameLogic;

import javafx.beans.property.SimpleObjectProperty;

public class GameImpl implements Game {
    private final SimpleObjectProperty<Gamefield> gamefield;
    private Player currPlayer;

    public GameImpl() {
        this.gamefield = new SimpleObjectProperty<>(new GamefieldImpl());
        currPlayer = this.gamefield.get().getPlayer1();
        this.gamefield.get().getPlayer1().setTurn(true);

    }

    public void switchPlayersTurn() {
        if (this.currPlayer.equals(this.gamefield.get().getPlayer2())) {
            this.currPlayer = this.gamefield.get().getPlayer1();
            this.gamefield.get().getPlayer1().setTurn(true);
        } else {
            this.currPlayer = this.gamefield.get().getPlayer2();
            this.gamefield.get().getPlayer2().setTurn(true);
        }
    }

    public Game startGame() {
        return null;
    }

    public Game loadGame() {
        return null;
    }

    public void saveGame() {

    }

    public void exitGame() {

    }

    public void runGame() {

    }

    public Gamefield getGamefield() {
        return this.gamefield.get();
    }
    public SimpleObjectProperty<Gamefield> getGamefieldProperty(){
        return this.gamefield;
    }

    @Override
    public Player getCurrentPlayer() {
        return this.currPlayer;
    }
}
