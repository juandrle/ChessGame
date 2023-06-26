package Business.Item.Trap;

import Business.GameLogic.Field;
import Business.GameLogic.Game;
import Business.Gamepiece.Pawn;
import Business.Item.Item;

import java.util.List;

public interface Trap extends Item {

    void applyTrap();

    boolean isActive();

    public List<Field> possibleMoves(Game game);

    public boolean isValidMove(Field newPos, Game game);
}
