package Business.Gamepiece;

import Business.GameLogic.Field;
import Business.Item.Item;

public class Queen implements Gamepiece{
    
    Item inventory;
    int rank;
    boolean moveable;
    Field position;

    public Queen(){
        inventory = null;
        int rank = 2;
        moveable = true;
    }

    public void setInventory(Item inv){
        inventory = inv;
    }

    public Item getInventory(){
        return inventory;
    }

    public void setRank(int rnk){
        rank = rnk;
    }

    public int getRank(){
        return rank;
    }

    public void setMoveable(boolean moveab){
        moveable = moveab;
    }
    public void setPosition(Field field) {
        this.position = field;
    }

    public Field getPosition() {
        return this.position;
    }

    public boolean isValidMove(){
        return false;
    }

    public boolean isMoveable(){
        return true;
    }
}