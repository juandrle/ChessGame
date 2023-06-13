package Business.Gamepiece;

import Business.GameLogic.Field;
import Business.Item.Item;

public class Pawn implements Gamepiece{
    private Item inventory;
    private int rank;
    private boolean moveable;
    private Field position;


    public Pawn(){
        inventory = null;
        int rank = 0;
        moveable = true;
    }

    public void setInventory(Item inventory){
        this.inventory = inventory;
    }

    public Item getInventory(){
        return this.inventory;
    }

    public void setRank(int rank){
        this.rank = rank;
    }

    public int getRank(){
        return this.rank;
    }

    public void setMoveable(boolean moveable){
        this.moveable = moveable;
    }

    public boolean isValidMove(Field newPos) {
        int checkRow = newPos.getRow() - position.getRow();
        int checkColumn = newPos.getColumn() - position.getColumn();

        if(checkRow >= -1 && checkRow <= 1 && checkColumn >= -1 && checkColumn <= 1){
            if(newPos.getColumn() >= 0 && newPos.getColumn() <= 7 && newPos.getRow() >= 0 && newPos.getRow() <= 7){
                return this.inventory == null || newPos.getItem() == null;
            }
        }
        return false;
    }

    public boolean isMoveable(){
        if (this.moveable) return true;
        return false;
    }

    public Field getPosition() {
        return this.position;
    }

    public void setPosition(Field position) {
        this.position = position;
    }
}
