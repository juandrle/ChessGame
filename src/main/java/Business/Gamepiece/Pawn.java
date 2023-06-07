package Business.Gamepiece;

public class Pawn implements Gamepiece{

    Item inventory;
    int rank;
    boolean moveable;

    public Pawn(){
        inventory = null;
        int rank = 0;
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

    public boolean getInventory(){
        return moveable;
    }
    //TODO:
    public boolean isValidMove(){
        return false;
    }

    public boolean isMoveable(){
        return true;
    }
}
