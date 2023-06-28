package Business.Item.Trap;

import Business.GameLogic.Field;
import Business.GameLogic.Game;
import Business.Gamepiece.Gamepiece;
import Business.Gamepiece.Pawn;
import Business.Item.Item;
import javafx.beans.property.SimpleBooleanProperty;

import java.util.List;

public interface Trap extends Item {

    void applyTrap(Gamepiece gamepiece, SimpleBooleanProperty engaged, Game game);

    boolean isActive();
    void setActive(boolean isActive);

    public List<Field> possiblePlacements(Game game);

    public boolean isValidPlacement(Field newPos, Game game);
}
