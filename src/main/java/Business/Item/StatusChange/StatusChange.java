package Business.Item.StatusChange;

import Business.Gamepiece.Gamepiece;
import Business.Item.Item;

public interface StatusChange extends Item {
    void applyStatusChange(Gamepiece gamepiece);
}
