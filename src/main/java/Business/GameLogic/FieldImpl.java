package Business.GameLogic;

import Business.Gamepiece.Gamepiece;
import Business.Item.Item;

public class FieldImpl implements Field{
    final private int column;
    final private int row;
    private Item item;
    private Gamepiece gamepiece;

    public FieldImpl(int row, int column, Gamepiece gamepiece, Item item) {
        this.row = row;
        this.column = column;
        this.gamepiece = gamepiece;
        this.item = item;
    }

    public Gamepiece getGamepiece() {
        return gamepiece;
    }

    public void setGamepiece(Gamepiece gamepiece) {
        this.gamepiece = gamepiece;
    }

    public Item getItem() {
        return this.item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getRow() {
        return this.row;
    }
    public int getColumn() {
        return this.column;
    }
}
