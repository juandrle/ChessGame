package Business.Item.Trap;

import Business.GameLogic.Field;
import Business.GameLogic.Game;
import Business.Gamepiece.Gamepiece;
import Business.Gamepiece.Pawn;
import Business.Item.StatusChange.Manipulator.TimeManipulator;
import javafx.scene.image.Image;
import Business.GameLogic.Field;
import java.util.ArrayList;
import java.util.List;

public class TrapImpl implements Trap {
    String description;
    Image image;
    private boolean isDropable = true;


    public TrapImpl(String description) {
        this.description = description;
    }

    @Override
    public boolean isDropable() {
        return this.isDropable;
    }

    @Override
    public void setIsDropable(boolean isDropable) {
        this.isDropable = isDropable;
    }

    @Override
    public void setPosition(Field f) {
    }

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void applyTrap() {
    }

    @Override
    public List<Field> possibleMoves(Game game){
        //um die gefundenen möglichen Züge zu speichern
        List<Field> result = new ArrayList<Field>();
        //if(!this.isMoveable())return null;
        //else{
        //überprüft, ob es ein gültiger Zug für das aktuelle Objekt ist.Wenn ja, wird das Feld der result-Liste hinzugefügt
            for(Field f: game.getGamefield().getFields()){
                if(isValidMove(f, game)) result.add(f);
            }
       // }
        return result;
    }

    @Override
    public boolean isValidMove(Field newPos, Game game) {
        //Differenz zwischen der Zeile und Spalte der neuen Position
        int checkRow = newPos.getRow() - game.getCurrentPlayer().getCurrGamepiece().getPosition().getRow();
        int checkColumn = newPos.getColumn() - game.getCurrentPlayer().getCurrGamepiece().getPosition().getColumn();

        //überprüft, ob die Differenzen checkRow und checkColumn im Bereich von -1 bis 1 liegen
        if(checkRow >= -1 && checkRow <= 1 && checkColumn >= -1 && checkColumn <= 1){
            // überprüft, ob die neue Position (newPos) bereits von einem andere figur besetzt ist
            for(Gamepiece g: game.getCurrentPlayer().getOwnGamepieces()) {
                if (newPos == g.getPosition()) return false;
            }
            //um anzuzeigen, dass der Zug gültig ist
            return true;
        }
        return false;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
