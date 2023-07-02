package business.item.trap;

import business.gameLogic.Field;
import business.gameLogic.Game;
import business.gamepiece.Gamepiece;
import business.item.Item;
import javafx.beans.property.SimpleBooleanProperty;

import java.util.List;

public interface Trap extends Item {

    void applyTrap(Gamepiece gamepiece, SimpleBooleanProperty engaged, Game game);

    boolean isActive();
    void setActive(boolean isActive);

    List<Field> possiblePlacements(Game game);

    boolean isValidPlacement(Field newPos, Game game);
}
