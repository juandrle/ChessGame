package business.gameLogic;

import business.gamepiece.Gamepiece;
import business.item.Item;

public interface Field {
    Gamepiece getGamepiece();

    void setGamepiece(Gamepiece gamepiece);

    Item getItem();

    void setItem(Item item);

    int getRow();

    int getColumn();

}
