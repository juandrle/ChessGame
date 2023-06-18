package Business.Gamepiece;

import java.util.ArrayList;
import java.util.List;

import Business.GameLogic.Field;
import Business.GameLogic.Game;
import Business.Item.Item;

public class Pawn implements Gamepiece{
    private Item inventory;
    private int rank;
    private boolean moveable;
    private Field position;
    private Game game;

    public Pawn(){
        this.inventory = null;
        this.rank = 0;
        this.moveable = true;
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

    public List<Field> possibleMoves(){
        List<Field> result = new ArrayList<Field>(null);
        if(!this.isMoveable())return null;
        else{
            for(Field f: game.getGamefield().getFields()){
                if(isValidMove(f)) result.add(f);
            }
        }
        return result;
    }

    public boolean isValidMove(Field newPos) {
        int checkRow = newPos.getRow() - position.getRow();
        int checkColumn = newPos.getColumn() - position.getColumn();

        if(checkRow >= -1 && checkRow <= 1 && checkColumn >= -1 && checkColumn <= 1){
            if(newPos.getGamepiece()== null){
                if(this.inventory == null || newPos.getItem() == null) return true;
            }
        }
        return false;
    }

    public boolean isMoveable(){
        if (this.moveable) return true;
        return false;
    }

    public Field getPosition() {
        return this.position;
    }

    public void setPosition(Field position) {
        this.position = position;
    }

}

