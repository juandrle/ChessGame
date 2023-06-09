package Business.Gamepiece;

import Business.GameLogic.Field;
import Business.Item.Item;

public class Queen implements Gamepiece{
    
<<<<<<< HEAD
    private Item inventory;
    private int rank;
    private boolean moveable;
    private Field position;
=======
    Item inventory;
    int rank;
    boolean moveable;
    Field position;
>>>>>>> e2c3bf25c1bdd0ba08f824621f697e227fdb2c8f

    public Queen(){
        inventory = null;
        int rank = 2;
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

<<<<<<< HEAD
    //TODO:
    public boolean isValidMove(Field newPos){
=======
    public boolean isValidMove(){
>>>>>>> e2c3bf25c1bdd0ba08f824621f697e227fdb2c8f
        return false;
    }

    public boolean isMoveable(){
        if (moveable) return true;
        else return false;
    }
}