package Business.GameLogic;

import Business.Gamepiece.Gamepiece;
import Business.Gamepiece.Pawn;
import Business.Gamepiece.Queen;
import Business.Gamepiece.Tower;
import javafx.beans.property.SimpleBooleanProperty;

import java.util.ArrayList;
import java.util.List;

public class PlayerImpl implements Player {
    String name;
    List<Gamepiece> ownGamepieces;
    SimpleBooleanProperty engaged;
    private boolean turn;

    public PlayerImpl(String name) {
        this.name = name;
        engaged = new SimpleBooleanProperty();
        this.ownGamepieces = new ArrayList<>();
        this.ownGamepieces.add(new Pawn());
        this.ownGamepieces.add(new Pawn());
        this.ownGamepieces.add(new Queen());
        this.ownGamepieces.add(new Tower());
        this.turn = false;
    }

    @Override
    public boolean moveGamepiece(Gamepiece gamepiece, Field field, Game game) {
        //if (field.getGamepiece() != null)
        //engaged.set(true);
        if (!this.turn) return false;
        Gamepiece currGamepiece = chooseGamepiece(gamepiece);
        if (currGamepiece == null) return false;
        if (!currGamepiece.isValidMove(field, game)) return false;
        currGamepiece.getPosition().setGamepiece(null);
        currGamepiece.setPosition(field);
        currGamepiece.getPosition().setGamepiece(currGamepiece);
        if (field.getItem() != null) {
            currGamepiece.setInventory(field.getItem());
            currGamepiece.getPosition().setItem(null);
        }
        this.turn = false;
        return true;
    }

    @Override
    public Gamepiece chooseGamepiece(Gamepiece gamepiece) {
        for (Gamepiece currGamepiece : ownGamepieces)
            if (currGamepiece.equals(gamepiece))
                return currGamepiece;
        return null;
    }

    @Override
    public void useItem(Gamepiece gamepiece) {

    }

    @Override
    public void setName(String name) {

    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void removeGamepiece(Gamepiece gamepiece) {
        this.ownGamepieces.remove(gamepiece);
        engaged.set(false);
    }

    public List<Gamepiece> getOwnGamepieces() {
        return this.ownGamepieces;
    }

    public SimpleBooleanProperty isEngaged() {
        return engaged;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    @Override
    public boolean getTurn() {
        return this.turn;
    }
}
