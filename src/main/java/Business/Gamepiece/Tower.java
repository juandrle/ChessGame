package Business.Gamepiece;

import Business.GameLogic.Field;
import Business.GameLogic.Gamefield;
import Business.Item.Item;

public class Tower implements Gamepiece{
<<<<<<< HEAD
    
=======

>>>>>>> 1b5ab4ce0f50c017df7e452d6f630b8e9f0e6d3f
    private Item inventory;
    private int rank;
    private boolean moveable;
    private Field position;
    private Gamefield gamefield;

    public Tower(){
        this.inventory = null;
        this.rank = 1;
        this.moveable = true;
    }

<<<<<<< HEAD
=======
    public void setPosition(Field position){
        this.position = position;
    }

    public Field getPosition(){
        return this.position;
    }
>>>>>>> 1b5ab4ce0f50c017df7e452d6f630b8e9f0e6d3f
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

<<<<<<< HEAD
    public void setPosition(Field field) {
        this.position = field;
    }

    public Field getPosition() {
        return this.position;
    }

=======
>>>>>>> 1b5ab4ce0f50c017df7e452d6f630b8e9f0e6d3f
    public boolean isValidMove(Field newPos) {
        int curRow = position.getRow();
        int curColumn = position.getColumn();

        if(curRow == newPos.getRow()){
            if(newPos.getColumn() >= 0 && newPos.getColumn() <= 7){
                if(this.inventory != null){
                    for(int i = curColumn; i <= newPos.getColumn();i++){
                        if(gamefield.getField(curRow,i).getItem() != null)// check every single field if it contains item if not its valid else its not
                            return false;
                    }
                }
                return true;
            }
        }
        else if(curColumn == newPos.getColumn()){
            if(newPos.getRow() >= 0 && newPos.getRow() <= 7){
                if(this.inventory != null){
                    for(int i = curRow; i <= newPos.getRow();i++){
                        if(gamefield.getField(i,curColumn).getItem() != null)// check every single field if it contains item if not its valid else its not
                            return false;
                    }
                }
            }
                return true;
        }
        return false;
    }

    public boolean isMoveable(){
        return moveable;
    }
}
