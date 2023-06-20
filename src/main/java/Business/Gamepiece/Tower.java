package Business.Gamepiece;

import java.util.ArrayList;
import java.util.List;

import Business.GameLogic.Field;
import Business.GameLogic.Game;
import Business.Item.Item;
import Business.Item.Trap.Trap;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;

public class Tower implements Gamepiece{
    private Item inventory;
    private int rank;
    private boolean moveable;
    private Field position;
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

    public boolean isValidMove(Field newPos, Game game) {
        int curRow = position.getRow();
        int curColumn = position.getColumn();
        List<Gamepiece> ownGamepiece = game.getCurrentPlayer().getOwnGamepieces();

        if(curRow == newPos.getRow()){
            if(curColumn < newPos.getColumn()){
                for(int i = curColumn; i <= newPos.getColumn();i++){
                    Item tmpItem = game.getGamefield().getField(curRow,i).getItem();
                    Field tmpField = game.getGamefield().getField(curRow,i);
                    if(!checkField(tmpItem,newPos,tmpField,ownGamepiece)) return false;
                }
                return true;
            }

            if(curColumn > newPos.getColumn()){
                for(int i = curColumn; i >= newPos.getColumn();i--){
                    Item tmpItem = game.getGamefield().getField(curRow,i).getItem();
                    Field tmpField = game.getGamefield().getField(curRow,i);
                    if(!checkField(tmpItem,newPos,tmpField,ownGamepiece)) return false;
                }
                return true;
            }

        }

        else if(curColumn == newPos.getColumn()){
            if(curRow < newPos.getRow()){
                for(int i = curRow; i <= newPos.getRow();i++){
                    Item tmpItem = game.getGamefield().getField(i,curColumn).getItem();
                    Field tmpField = game.getGamefield().getField(i,curColumn);
                    if(!checkField(tmpItem,newPos,tmpField,ownGamepiece)) return false;
                }
                return true;
            }

            if(curRow > newPos.getRow()){
                for(int i = curRow; i >= newPos.getRow();i--){
                    Item tmpItem = game.getGamefield().getField(i,curColumn).getItem();
                    Field tmpField = game.getGamefield().getField(i,curColumn);
                    if(!checkField(tmpItem,newPos,tmpField,ownGamepiece)) return false;
                }
                return true;
            }
        }

        return false;
    }


    public boolean isMoveable(){
        return moveable;
    }

        public List<Field> possibleMoves(Game game){
        List<Field> result = new ArrayList<Field>();
        if(!this.isMoveable())return null;
        else{
            for(Field f: game.getGamefield().getFields()){
                if(isValidMove(f, game)) result.add(f);
            }
        }
        return result;
    }


    private boolean checkField(Item tmpItem, Field newPos,Field tmpField,List<Gamepiece> ownGamepieces){

        for(Gamepiece g: ownGamepieces) {
            if(tmpField == g.getPosition()) return false;
        }
        if(newPos == tmpField){
            if(this.inventory != null && tmpItem != null){
                return false;
            }
            return true;
        }
        if(tmpItem != null){
            if(tmpItem instanceof Trap && !tmpItem.isDropable())
                return true;
            return false;
        }
        if(tmpField.getGamepiece() != null) return false;
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
