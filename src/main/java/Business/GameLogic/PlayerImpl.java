package Business.GameLogic;

import Business.Gamepiece.Gamepiece;
import Business.Gamepiece.Pawn;
import Business.Gamepiece.Queen;
import Business.Gamepiece.Tower;
import Business.Item.Trap.MotionTrap;

import java.util.ArrayList;
import java.util.List;

public class PlayerImpl implements Player{
    String name;
    List<Gamepiece> ownGamepieces;

    public PlayerImpl(String name, boolean newGame) {
        this.name = name;
        this.ownGamepieces = new ArrayList<>();
        if(newGame) initGamepieces();

    }
    @Override
    public void moveGamepiece(Gamepiece gamepiece, Field field) {
        chooseGamepiece(gamepiece).setPosition(field);
    }

    @Override
    public Gamepiece chooseGamepiece(Gamepiece gamepiece) {
        for (Gamepiece currGamepiece: ownGamepieces)
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
        return null;
    }

    @Override
    public void removeGamepiece(Gamepiece gamepiece) {
        this.ownGamepieces.remove(gamepiece);
    }

    @Override
    public void addNewGamepiece(Gamepiece g) {
        this.ownGamepieces.add(g);
    }

    @Override
    public void initGamepieces() {
        this.ownGamepieces.add(new Pawn());
        this.ownGamepieces.add(new Pawn());
        this.ownGamepieces.add(new Queen());
        this.ownGamepieces.add(new Tower());
    }

    public List<Gamepiece> getOwnGamepieces() {
        return this.ownGamepieces;
    }
}
