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

    //TODO:
public boolean isValidMove(FieldImpl newPos) {
        int curRow = position.getRow();
        int curColoumn = position.getColumn();

        if(curRow == newPos.getRow()){//Horizontal
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
        else if(curColoumn == newPos.getColoumn()){// Vertikal
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
        else if(curColoumn != newPos.getColoumn() && curRow != newPos.getRow()){//Diagonal
            int tmpColoumn = curColoumn - newPos.getColoumn();
            int tmpRow = curRow - newPos.getRow();
            if(newPos.getRow() >= 0 && newPos.getRow() <= 7 && newPos.getColumn() >= 0 && newPos.getColumn() <= 7){
                if(Math.abs(tmpColoumn) - Math.abs(tmpRow) == 0){
                    if(tmpRow > 0 && tmpColoumn > 0){// nach links unten
                        for(int i = 1; i <= Math.abs(tmpRow);i++){
                            if(!gamefield.getField(curRow - i,curColoumn - i).getItem() != null)// check every single field if it contains item if not its valid else its not
                                return false;
                        }
                    }
                    else if(tmpRow < 0 && tmpColoumn > 0){// nach links oben <<<<<< Check
                        for(int i = 1; i <= Math.abs(tmpRow);i++){
                            if(!gamefield.getField(curRow + i,curColoumn - i).getItem() != null)// check every single field if it contains item if not its valid else its not
                                return false;
                        }
                    }
                    else if(tmpRow > 0 && tmpColoumn < 0){// nach rechts unten <<<<<<<<<<<<<check
                        for(int i = 1; i <= Math.abs(tmpRow);i++){
                            if(!gamefield.getField(curRow - i,curColoumn + i).getItem() != null)// check every single field if it contains item if not its valid else its not
                                return false;
                        }
                    }
                    else if(tmpRow < 0 && tmpColoumn < 0){// nach rechts oben
                        for(int i = 1; i <= Math.abs(tmpRow);i++){
                            if(!gamefield.getField(curRow + i,curColoumn + i).getItem() != null)// check every single field if it contains item if not its valid else its not
                                return false;
                        }
                    }
                    
                }
                return true; <<<check
            }
        }
        return false;
    }

    public boolean isMoveable(){
        if (moveable) return true;
        else return false;
    }
}