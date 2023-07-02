package business.gamepiece;


import business.gameLogic.Field;
import business.gameLogic.Game;
import business.item.trap.Trap;
import javafx.beans.property.SimpleObjectProperty;

public class Pawn extends GamepieceImpl{
       public Pawn(){
        super();
        this.inventory = null;
        this.rank = 0;
        this.moveable = true;
        image = new SimpleObjectProperty<>();
    }
    @Override
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
}

