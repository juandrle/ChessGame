package Business.Gamepiece;

import Business.GameLogic.Field;
import Business.GameLogic.Gamefield;
import Business.Item.Item;

public class Queen implements Gamepiece{

    private Item inventory;
    private int rank;
    private boolean moveable;
    private Field position;
    private Gamefield gamefield;


    public Queen(){
        this.inventory = null;
        this.rank = 2;
        this.moveable = true;
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
public boolean isValidMove(Field newPos) {
        int curRow = position.getRow();
        int curColumn = position.getColumn();

        if(curRow == newPos.getRow()){//Horizontal
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
        else if(curColumn == newPos.getColumn()){// Vertikal
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
        else if(curColumn != newPos.getColumn() && curRow != newPos.getRow()){//Diagonal
            int tmpColumn = curColumn - newPos.getColumn();
            int tmpRow = curRow - newPos.getRow();
            if(newPos.getRow() >= 0 && newPos.getRow() <= 7 && newPos.getColumn() >= 0 && newPos.getColumn() <= 7){
                if(Math.abs(tmpColumn) - Math.abs(tmpRow) == 0){
                    if(tmpRow > 0 && tmpColumn > 0){// nach links unten
                        for(int i = 1; i <= Math.abs(tmpRow);i++){
                            if(gamefield.getField(curRow - i,curColumn - i).getItem() != null)// check every single field if it contains item if not its valid else its not
                                return false;
                        }
                    }
                    else if(tmpRow < 0 && tmpColumn > 0){// nach links oben <<<<<< Check
                        for(int i = 1; i <= Math.abs(tmpRow);i++){
                            if(gamefield.getField(curRow + i,curColumn - i).getItem() != null)// check every single field if it contains item if not its valid else its not
                                return false;
                        }
                    }
                    else if(tmpRow > 0 && tmpColumn < 0){// nach rechts unten <<<<<<<<<<<<<check
                        for(int i = 1; i <= Math.abs(tmpRow);i++){
                            if(gamefield.getField(curRow - i,curColumn + i).getItem() != null)// check every single field if it contains item if not its valid else its not
                                return false;
                        }
                    }
                    else if(tmpRow < 0 && tmpColumn < 0){// nach rechts oben
                        for(int i = 1; i <= Math.abs(tmpRow);i++){
                            if(gamefield.getField(curRow + i,curColumn + i).getItem() != null)// check every single field if it contains item if not its valid else its not
                                return false;
                        }
                    }
                    
                }
                return true;
            }
        }
        return false;
    }

    public boolean isMoveable(){
        if (moveable) return true;
        else return false;
    }

}