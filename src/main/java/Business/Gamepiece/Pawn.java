package Business.Gamepiece;

import Business.GameLogic.Field;
import Business.Item.Item;

public class Pawn implements Gamepiece{
<<<<<<< HEAD

=======
>>>>>>> 1b5ab4ce0f50c017df7e452d6f630b8e9f0e6d3f
    private Item inventory;
    private int rank;
    private boolean moveable;
    private Field position;
<<<<<<< HEAD
=======

>>>>>>> 1b5ab4ce0f50c017df7e452d6f630b8e9f0e6d3f

    public Pawn(){
        this.inventory = null;
        this.rank = 0;
        this.moveable = true;
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
<<<<<<< HEAD
                if(this.inventory != null && newPos.getItem() != null){
                    return false;
                }
                return true;
=======
                return this.inventory == null || newPos.getItem() == null;
>>>>>>> 1b5ab4ce0f50c017df7e452d6f630b8e9f0e6d3f
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
