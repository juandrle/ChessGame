package business.gamepiece;

import java.util.List;

import business.gameLogic.Field;
import business.gameLogic.Game;
import javafx.beans.property.SimpleObjectProperty;

public class Tower extends GamepieceImpl {


    public Tower() {
        super();
        this.inventory = null;
        this.rank = 1;
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

        return false;
    }
}
