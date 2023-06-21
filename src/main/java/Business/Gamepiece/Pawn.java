package Business.Gamepiece;

import java.util.ArrayList;
import java.util.List;

import Business.GameLogic.Field;
import Business.GameLogic.Game;
import Business.Item.Item;
import Business.Item.Trap.Trap;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;

public class Pawn implements Gamepiece{
    private Item inventory;
    private int rank;
    private boolean moveable;
    private Field position;
    SimpleObjectProperty<Image> image;
    private int points = -1;

    public Pawn(){
        this.inventory = null;
        this.rank = 0;
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

    @Override
    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public int getPoints() {
        return points;
    }

    public boolean isValidMove(Field newPos, Game game) {
        int checkRow = newPos.getRow() - position.getRow();
        int checkColumn = newPos.getColumn() - position.getColumn();

        if(checkRow >= -1 && checkRow <= 1 && checkColumn >= -1 && checkColumn <= 1){
            for(Gamepiece g: game.getCurrentPlayer().getOwnGamepieces()) {
                if(newPos == g.getPosition()) return false;
            }
            if(newPos.getItem() != null){
                if(newPos.getItem() instanceof Trap && !newPos.getItem().isDropable())return true;
                if(this.inventory != null) return false;
            }
            return true;
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

    @Override
    public SimpleObjectProperty<Image> getImage() {
        return this.image;
    }

    @Override
    public void setImage(Image image) {
        this.image.set(image);
    }

    public void setPosition(Field position) {
        this.position = position;
    }

}

