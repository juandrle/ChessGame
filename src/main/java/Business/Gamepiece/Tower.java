package Business.Gamepiece;

public class Tower implements Gamepiece{
    
    private Item inventory;
    private int rank;
    private boolean moveable;
    private Field position;

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

    public boolean getInventory(){
        return moveable;
    }

    //TODO:
    public boolean isValidMove(Field newPos){
        return false;
    }

    public boolean isMoveable(){
        if (moveable) return true;
        else return false;
    }
}
