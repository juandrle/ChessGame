package Business.GameLogic;

import Business.Gamepiece.Gamepiece;
import Business.Gamepiece.Pawn;
import Business.Gamepiece.Queen;
import Business.Gamepiece.Tower;

import java.util.ArrayList;
import java.util.List;

public class PlayerImpl implements Player{
    String name;
    List<Gamepiece> ownGamepieces;

    public PlayerImpl(String name) {
        this.name = name;
        ownGamepieces = new ArrayList<>();
        ownGamepieces.add(new Pawn());
        ownGamepieces.add(new Pawn());
        ownGamepieces.add(new Queen());
        ownGamepieces.add(new Tower());
    }
    @Override
    public void moveGamepiece(Gamepiece gamepiece, Field field) {

    }

    @Override
    public Gamepiece chooseGamepiece() {
        return null;
    }

    @Override
    public void useItem() {

    }

    @Override
    public void setName(String name) {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void removeGamepiece(Gamepiece gamepiece) {

    }

    @Override
    public Gamepiece getGamepiece(Gamepiece gamepiece) {
        return null;
    }
}
