package Business.GameLogic;

import Business.Gamepiece.Gamepiece;
import Business.Item.Item;

public interface Field {
    Gamepiece getGamepiece();

    void setGamepiece(Gamepiece gamepiece);

    Item getItem();

    void setItem(Item item);

    int getRow();

    int getColumn();

}
