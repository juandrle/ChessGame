package Business.Gamepiece;

import java.util.ArrayList;
import java.util.List;

import Business.GameLogic.Field;
import Business.GameLogic.Game;
import Business.Item.Item;
import Business.Item.Trap.Trap;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;

public class Queen implements Gamepiece {

    private Item inventory;
    private int rank;
    private boolean moveable;
    private Field position;
    SimpleObjectProperty<Image> image;
    private int points = -1;


    public Queen() {
        this.inventory = null;
        this.rank = 2;
        this.moveable = true;
        image = new SimpleObjectProperty<>();
    }

    public void setInventory(Item inv) {
        inventory = inv;
    }

    public Item getInventory() {
        return inventory;
    }

    public void setRank(int rnk) {
        rank = rnk;
    }

    public int getRank() {
        return rank;
    }

    public void setMoveable(boolean moveab) {
        moveable = moveab;
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

        if (curRow == newPos.getRow()) {//wagerecht
            if (curColumn < newPos.getColumn()) {
                for (int i = curColumn + 1; i <= newPos.getColumn(); i++) {
                    Item tmpItem = game.getGamefield().getField(curRow, i).getItem();
                    Field tmpField = game.getGamefield().getField(curRow, i);
                    if (!checkField(tmpItem, newPos, tmpField, ownGamepiece)) return false;
                }
                return true;
            }

            if (curColumn > newPos.getColumn()) {
                for (int i = curColumn - 1; i >= newPos.getColumn(); i--) {
                    Item tmpItem = game.getGamefield().getField(curRow, i).getItem();
                    Field tmpField = game.getGamefield().getField(curRow, i);
                    if (!checkField(tmpItem, newPos, tmpField, ownGamepiece)) return false;
                }
                return true;
            }

        } else if (curColumn == newPos.getColumn()) {//senkrecht
            if (curRow < newPos.getRow()) {
                for (int i = curRow + 1; i <= newPos.getRow(); i++) {
                    Item tmpItem = game.getGamefield().getField(i, curColumn).getItem();
                    Field tmpField = game.getGamefield().getField(i, curColumn);
                    if (!checkField(tmpItem, newPos, tmpField, ownGamepiece)) return false;
                }
                return true;
            }

            if (curRow > newPos.getRow()) {
                for (int i = curRow - 1; i >= newPos.getRow(); i--) {
                    Item tmpItem = game.getGamefield().getField(i, curColumn).getItem();
                    Field tmpField = game.getGamefield().getField(i, curColumn);
                    if (!checkField(tmpItem, newPos, tmpField, ownGamepiece)) return false;
                }
                return true;
            }
        }
        if (curColumn != newPos.getColumn() && curRow != newPos.getRow()) {//Diagonal
            int tmpColumn = curColumn - newPos.getColumn();
            int tmpRow = curRow - newPos.getRow();
            if (Math.abs(tmpColumn) - Math.abs(tmpRow) == 0) {
                if (tmpRow > 0 && tmpColumn > 0) {// nach links unten
                    for (int i = 1; i <= Math.abs(tmpRow); i++) {
                        Field tmpField = game.getGamefield().getField(curRow - i, curColumn - i);
                        if (!checkField(tmpField.getItem(), newPos, tmpField, ownGamepiece)) return false;
                    }
                    return true;
                } else if (tmpRow < 0 && tmpColumn > 0) {// nach links oben <<<<<< Check
                    for (int i = 1; i <= Math.abs(tmpRow); i++) {
                        Field tmpField = game.getGamefield().getField(curRow + i, curColumn - i);
                        if (!checkField(tmpField.getItem(), newPos, tmpField, ownGamepiece)) return false;
                    }
                    return true;
                } else if (tmpRow > 0 && tmpColumn < 0) {// nach rechts unten <<<<<<<<<<<<<check
                    for (int i = 1; i <= Math.abs(tmpRow); i++) {
                        Field tmpField = game.getGamefield().getField(curRow - i, curColumn + i);
                        if (!checkField(tmpField.getItem(), newPos, tmpField, ownGamepiece)) return false;
                    }
                    return true;
                } else if (tmpRow < 0 && tmpColumn < 0) {// nach rechts oben
                    for (int i = 1; i <= Math.abs(tmpRow); i++) {
                        Field tmpField = game.getGamefield().getField(curRow + i, curColumn + i);
                        if (!checkField(tmpField.getItem(), newPos, tmpField, ownGamepiece)) return false;
                    }
                    return true;
                }
            }
            return false;
        }

        return false;
    }

    public boolean isMoveable() {
        return moveable;
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


    private boolean checkField(Item tmpItem, Field newPos, Field tmpField, List<Gamepiece> ownGamepieces) {

        for (Gamepiece g : ownGamepieces) {
            if (tmpField == g.getPosition()) return false;
        }
        if (newPos == tmpField) {
            if (this.getInventory() != null && tmpItem != null) {
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
}