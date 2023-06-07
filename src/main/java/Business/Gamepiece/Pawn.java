package Business.Gamepiece;

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
        return true;
    }

    public boolean isMoveable(){
        if (moveable) return true;
        else return false;
    }
}
