package Business.Gamepiece;

import Business.GameLogic.Field;
import Business.Item.Item;

public class Tower implements Gamepiece{
    
    Item inventory;
    int rank;
    boolean moveable;
    GamefieldImpl gamefield;

    public Queen(){
        inventory = null;
        int rank = 1;
        moveable = true;
    }

    public void setPosition(Field position){
        this.position = position;
    }

    public Field getPosition(){
        return position;
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

    public void setPosition(Fieldimpl field) {
        this.position = field;
    }

    public Fieldimpl getPosition() {
        return this.position;
    }

    public boolean isValidMove(FieldImpl newPos) {
        int curRow = position.getRow();
        int curColoumn = position.getColumn();

        if(curRow == newPos.getRow()){
            if(newPos.getColumn() >= 0 && newPos.getColumn() <= 7){
                if(this.inventory != null){
                    for(int i = curColoumn; i <= newPos.getColoumn();i++){
                        if(!gamefield.getField(curRow,i).getItem() != null)// check every single field if it contains item if not its valid else its not
                            return false;
                    }
                }
                return true;
            }
        }
        else if(curColoumn == newPos.getColoumn()){
            if(newPos.getRow() >= 0 && newPos.getRow() <= 7){
                if(this.inventory != null){
                    for(int i = curRow; i <= newPos.getRow();i++){
                        if(!gamefield.getField(i,curColoumn).getItem() != null)// check every single field if it contains item if not its valid else its not
                            return false;
                    }
                }
            }
                return true;
        }
        return false;
    }

    public boolean isMoveable(){
        if (moveable) return true;
        else return false;
    }
}
