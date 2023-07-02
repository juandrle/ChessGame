package business.item.statusChange;

import business.gamepiece.Gamepiece;
import business.item.Item;

public interface StatusChange extends Item {
    void applyStatusChange(Gamepiece gamepiece);
}
