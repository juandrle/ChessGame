package Business.GameLogic;

import Business.Gamepiece.Gamepiece;
import Business.Item.Item;

public class FieldImpl implements Field{
    private int coloumn;
    private int row;
    private Item item;
    private Gamepiece gamepiece;

    public FieldImpl(int row, int coloumn, Gamepiece gamepiece, Item item) {
        this.row = row;
        this.coloumn = coloumn;
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
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColoumn() {
        return coloumn;
    }

    public void setColoumn(int coloumn) {
        this.coloumn = coloumn;
    }
}
