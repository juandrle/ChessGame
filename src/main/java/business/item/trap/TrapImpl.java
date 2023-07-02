package business.item.trap;

import business.gameLogic.Game;
import business.gamepiece.Gamepiece;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.image.Image;
import business.gameLogic.Field;

import java.util.ArrayList;
import java.util.List;

public class TrapImpl implements Trap {
    String description;
    Image image;
    private boolean isDropable = true;
    private boolean isActive = false;
    Field position;


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
        this.position = f;
        f.setItem(this);
    }

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public boolean isActive() {
        return isActive;
    }

    @Override
    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public void applyTrap(Gamepiece gamepiece,SimpleBooleanProperty engaged, Game game) {
    }

    @Override
    public List<Field> possiblePlacements(Game game) {
        //um die gefundenen möglichen Züge zu speichern
        List<Field> result = new ArrayList<>();
        //if(!this.isMoveable())return null;
        //else{
        //überprüft, ob es ein gültiger Zug für das aktuelle Objekt ist.Wenn ja, wird das Feld der result-Liste hinzugefügt
        for (Field f : game.getGamefield().getFields()) {
            if (isValidPlacement(f, game)) result.add(f);
        }
        // }
        return result;
    }

    @Override
    public boolean isValidPlacement(Field newPos, Game game) {
        //Differenz zwischen der Zeile und Spalte der neuen Position
        int checkRow = newPos.getRow() - game.getCurrentPlayer().getCurrGamepiece().getPosition().getRow();
        int checkColumn = newPos.getColumn() - game.getCurrentPlayer().getCurrGamepiece().getPosition().getColumn();

        //überprüft, ob die Differenzen checkRow und checkColumn im Bereich von -1 bis 1 liegen
        if (checkRow >= -1 && checkRow <= 1 && checkColumn >= -1 && checkColumn <= 1) {
            // überprüft, ob die neue Position (newPos) bereits von einem andere figur besetzt ist
            for (Gamepiece g : game.getCurrentPlayer().getOwnGamepieces()) {
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
