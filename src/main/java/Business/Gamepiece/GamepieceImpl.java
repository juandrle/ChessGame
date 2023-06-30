package Business.Gamepiece;

import Business.GameLogic.Field;
import Business.GameLogic.Game;
import Business.Item.Item;
import Business.Item.Trap.Trap;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public abstract class GamepieceImpl implements Gamepiece {

    protected Item inventory;
    protected int rank;
    protected boolean moveable;
    protected Field position;
    protected SimpleIntegerProperty timeMultiplier;
    SimpleObjectProperty<Image> image;
    protected int points = -1;

    protected GamepieceImpl(){
        this.inventory = null;
        timeMultiplier = new SimpleIntegerProperty(10);
        this.rank = 0;
        this.moveable = true;
        image = new SimpleObjectProperty<>();
    }

    @Override
    public boolean isValidMove(Field newPos, Game game){return false;};

    public void setInventory(Item inventory) {
        this.inventory = inventory;
    }

    public Item getInventory() {
        return this.inventory;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getRank() {
        return this.rank;
    }

    public void setMoveable(boolean moveable) {
        this.moveable = moveable;
    }


    public void setPosition(Field field) {
        this.position = field;
        field.setGamepiece(this);
    }

    public Field getPosition() {
        return this.position;
    }

    public boolean isMoveable() {
        return moveable;
    }

    @Override
    public SimpleObjectProperty<Image> getImage() {
        return this.image;
    }

    @Override
    public void setImage(Image image) {
        this.image.set(image);
    }

    @Override
    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public int getPoints() {
        return points;
    }

    public boolean moveHorizontal (Gamepiece gamepiece,Field pos, Field newPos, Game game, List<Gamepiece> ownGamepiece){
        int curColumn = pos.getColumn();
        int curRow = pos.getRow();
        if (curColumn < newPos.getColumn()) {
            for (int i = curColumn + 1; i <= newPos.getColumn(); i++) {
                Item tmpItem = game.getGamefield().getField(curRow, i).getItem();
                Field tmpField = game.getGamefield().getField(curRow, i);
                if (!checkField(gamepiece.getInventory(),tmpItem, newPos, tmpField, ownGamepiece)) return false;
            }
            return true;
        }

        if (curColumn > newPos.getColumn()) {
            for (int i = curColumn - 1; i >= newPos.getColumn(); i--) {
                Item tmpItem = game.getGamefield().getField(curRow, i).getItem();
                Field tmpField = game.getGamefield().getField(curRow, i);
                if (!checkField(gamepiece.getInventory(),tmpItem, newPos, tmpField, ownGamepiece)) return false;
            }
            return true;
        }
        return false;
    }

    public boolean moveVertikal (Gamepiece gamepiece,Field pos, Field newPos, Game game, List<Gamepiece> ownGamepiece){
        int curColumn = pos.getColumn();
        int curRow = pos.getRow();
        if (curRow < newPos.getRow()) {
            for (int i = curRow + 1; i <= newPos.getRow(); i++) {
                Item tmpItem = game.getGamefield().getField(i, curColumn).getItem();
                Field tmpField = game.getGamefield().getField(i, curColumn);
                if (!checkField(gamepiece.getInventory(),tmpItem, newPos, tmpField, ownGamepiece)) return false;
            }
            return true;
        }

        if (curRow > newPos.getRow()) {
            for (int i = curRow - 1; i >= newPos.getRow(); i--) {
                Item tmpItem = game.getGamefield().getField(i, curColumn).getItem();
                Field tmpField = game.getGamefield().getField(i, curColumn);
                if (!checkField(gamepiece.getInventory(),tmpItem, newPos, tmpField, ownGamepiece)) return false;
            }
            return true;
        }
        return true;
    }

    public List<Field> possibleMoves(Game game) {
        List<Field> result = new ArrayList<>();
        if (!this.isMoveable()) return null;
        else {
            for (Field f : game.getGamefield().getFields()) {
                if (isValidMove(f, game)) result.add(f);
            }
        }
        return result;
    }

    protected boolean checkField(Item inventory, Item tmpItem, Field newPos, Field tmpField, List<Gamepiece> ownGamepieces) {

        for (Gamepiece g : ownGamepieces) {
            if (tmpField == g.getPosition()) return false;
        }
        if (newPos == tmpField) {
            if (inventory != null && tmpItem != null) {
                return false;
            }
            return true;
        }
        if (tmpItem != null) {
            if (tmpItem instanceof Trap && !tmpItem.isDropable())
                return true;
            return false;
        }
        if (tmpField.getGamepiece() != null) return false;
        return true;
    }

    public void setTimeMultiplier(int timeMultiplier) {
        this.timeMultiplier.set(timeMultiplier);
    }

    public int getTimeMultiplier() {
        return timeMultiplier.get();
    }
    public SimpleIntegerProperty propertyTimeMultiplier(){
        return timeMultiplier;
    }
}
