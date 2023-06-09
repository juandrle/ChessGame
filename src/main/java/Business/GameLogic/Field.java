package Business.GameLogic;

import Business.Gamepiece.Gamepiece;
import Business.Item.Item;

public interface Field {
    public Gamepiece getGamepiece();

    public void setGamepiece(Gamepiece gamepiece);

    public Item getItem();

    public void setItem(Item item);

    public int getRow();
    public int getColumn();

}
