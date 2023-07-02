package business.gamepiece;

import java.util.List;

import business.gameLogic.Field;
import business.gameLogic.Game;
import business.item.Item;
import javafx.beans.property.SimpleObjectProperty;

public class Queen extends GamepieceImpl {
    public Queen() {
        super();
        this.inventory = null;
        this.rank = 2;
        this.moveable = true;
        image = new SimpleObjectProperty<>();
    }
    @Override
    public boolean isValidMove(Field newPos, Game game) {
        int curRow = position.getRow();
        int curColumn = position.getColumn();
        List<Gamepiece> ownGamepiece = game.getCurrentPlayer().getOwnGamepieces();

        if (curRow == newPos.getRow()) {
            return moveHorizontal(this,this.getPosition(),newPos,game,ownGamepiece);

        } else if (curColumn == newPos.getColumn()) {
            return moveVertikal(this,this.getPosition(),newPos,game,ownGamepiece);
        }

        if (curColumn != newPos.getColumn() && curRow != newPos.getRow()) {//Diagonal
            int tmpColumn = curColumn - newPos.getColumn();
            int tmpRow = curRow - newPos.getRow();
            if (Math.abs(tmpColumn) - Math.abs(tmpRow) == 0) {
                if (tmpRow > 0 && tmpColumn > 0) {// nach links unten
                    for (int i = 1; i <= Math.abs(tmpRow); i++) {
                        Item tmpItem = game.getGamefield().getField(curRow - i, curColumn -i).getItem();
                        Field tmpField = game.getGamefield().getField(curRow - i, curColumn - i);
                        if (checkField(this.getInventory(), tmpItem, newPos, tmpField, ownGamepiece)) return false;
                    }
                    return true;
                } else if (tmpRow < 0 && tmpColumn > 0) {// nach links oben <<<<<< Check
                    for (int i = 1; i <= Math.abs(tmpRow); i++) {
                        Item tmpItem = game.getGamefield().getField(curRow + i, curColumn -i).getItem();
                        Field tmpField = game.getGamefield().getField(curRow + i, curColumn - i);
                        if (checkField(this.getInventory(), tmpItem, newPos, tmpField, ownGamepiece)) return false;
                    }
                    return true;
                } else if (tmpRow > 0 && tmpColumn < 0) {// nach rechts unten <<<<<<<<<<<<<check
                    for (int i = 1; i <= Math.abs(tmpRow); i++) {
                        Item tmpItem = game.getGamefield().getField(curRow -i, curColumn +i).getItem();
                        Field tmpField = game.getGamefield().getField(curRow - i, curColumn + i);
                        if (checkField(this.getInventory(), tmpItem, newPos, tmpField, ownGamepiece)) return false;
                    }
                    return true;
                } else if (tmpRow < 0 && tmpColumn < 0) {// nach rechts oben
                    for (int i = 1; i <= Math.abs(tmpRow); i++) {
                        Item tmpItem = game.getGamefield().getField(curRow + i, curColumn +i).getItem();
                        Field tmpField = game.getGamefield().getField(curRow + i, curColumn + i);
                        if (checkField(this.getInventory(), tmpItem, newPos, tmpField, ownGamepiece)) return false;
                    }
                    return true;
                }
            }
            return false;
        }

        return false;
    }





}