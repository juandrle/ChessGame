package business.item.trap;

import business.gameLogic.Game;
import business.gamepiece.Gamepiece;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.image.Image;

public class MotionTrap extends TrapImpl {
    final Image image;

    public MotionTrap() {
        super();
        setDescription(getDescription() + "Diese verhindert nach Aktivierung \n die Bewegung der Figur \n die sie aktiviert hat");
        image = new Image("files/pictures/Items/Motiontrap.png");
    }

    @Override
    public void applyTrap(Gamepiece gamepiece,SimpleBooleanProperty engaged, Game game) {
        gamepiece.setMoveable(false);
        game.getEffectedGamepieces().put(gamepiece,game.getTurnCount()+3);
    }
    @Override
    public Image getImage() {
        return image;
    }
}
