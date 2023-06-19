package Business.Gamepiece;

import java.util.ArrayList;
import java.util.List;

import Business.GameLogic.Field;
import Business.GameLogic.Game;
import Business.Item.Item;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;

public class Tower implements Gamepiece{
    private Item inventory;
    private int rank;
    private boolean moveable;
    private Field position;
    private Game game;
    SimpleObjectProperty<Image> image;

    public Tower(){
        this.inventory = null;
        this.rank = 1;
        this.moveable = true;
        image = new SimpleObjectProperty<>();
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


    public void setPosition(Field field) {
        this.position = field;
    }

    public Field getPosition() {
        return this.position;
    }

    public boolean isValidMove(Field newPos) {
        int curRow = position.getRow();
        int curColumn = position.getColumn();

        if(curRow == newPos.getRow()){
            if(curColumn < newPos.getColumn()){
                for(int i = curColumn; i <= newPos.getColumn();i++){
                    Item tmpItem = game.getGamefield().getField(curRow,i).getItem();
                    if(!checkFieldColumn(curRow,curColumn,tmpItem,newPos)) return false;
                }
                return true;
            }

            if(curColumn > newPos.getColumn()){
                for(int i = curColumn; i >= newPos.getColumn();i--){
                    Item tmpItem = game.getGamefield().getField(curRow,i).getItem();
                    if(!checkFieldColumn(curRow,curColumn,tmpItem,newPos)) return false;
                }
                return true;
            }

        }

        else if(curColumn == newPos.getColumn()){
            if(curRow < newPos.getRow()){
                for(int i = curRow; i <= newPos.getRow();i++){
                    Item tmpItem = game.getGamefield().getField(i,curColumn).getItem();
                    if(!checkFieldRow(curRow,curColumn,tmpItem,newPos)) return false;
                }
                return true;
            }

            if(curRow > newPos.getRow()){
                for(int i = curRow; i >= newPos.getRow();i--){
                    Item tmpItem = game.getGamefield().getField(i,curColumn).getItem();
                    if(!checkFieldRow(curRow,curColumn,tmpItem,newPos)) return false;
                }
                return true;
            }            
        }

        return false;
    }

    public boolean isMoveable(){
        return moveable;
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

    private boolean checkFieldRow(int curRow, int curColumn,Item tmpItem, Field newPos){
        if(newPos.getGamepiece() != null)return false;
        else if(curRow == newPos.getRow()){
            if(this.inventory != null && tmpItem != null){
                return false;
            }
            //unsicher
            if(tmpItem != null){
                if(tmpItem.getClass().getClassLoader().getParent().getName().equals("Trap") && !tmpItem.isDropable())
                    return true;
                return false;
        
            }
        }
        if(tmpItem != null){
                if(tmpItem.getClass().getClassLoader().getParent().getName().equals("Trap") && !tmpItem.isDropable())
                    return true;
                return false;
        }
        return true;
    }

        private boolean checkFieldColumn(int curRow, int curColumn,Item tmpItem, Field newPos){
        if(newPos.getGamepiece() != null)return false;
        else if(curColumn == newPos.getColumn()){
            if(this.inventory != null && tmpItem != null){
                return false;
            }
            //unsicher
            if(tmpItem != null){
                if(tmpItem.getClass().getClassLoader().getParent().getName().equals("Trap") && !tmpItem.isDropable())
                    return true;
                return false;
        
            }
        }
        if(tmpItem != null){
                if(tmpItem.getClass().getClassLoader().getParent().getName().equals("Trap") && !tmpItem.isDropable())
                    return true;
                return false;
        }
        return true;
    }
    @Override
    public SimpleObjectProperty<Image> getImage() {
        return this.image;
    }

    @Override
    public void setImage(Image image) {
        this.image.set(image);
    }
}
