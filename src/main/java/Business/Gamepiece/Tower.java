package Business.Gamepiece;

import Business.GameLogic.Field;
import Business.Item.Item;

public class Tower implements Gamepiece{
    
    Item inventory;
    int rank;
    boolean moveable;

    public Queen(){
        inventory = null;
        int rank = 1;
        moveable = true;
    }

    public void setPosition(Field pos){
        position = pos;
    }

    public Field getPosition(){
        return position;
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

    //TODO:
    public boolean isValidMove(){
        return false;
    }

    public boolean isMoveable(){
        if (moveable) return true;
        else return false;
    }
}
