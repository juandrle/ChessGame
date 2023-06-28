package Business.Item.Trap;

import Business.GameLogic.Game;
import Business.Gamepiece.Gamepiece;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.image.Image;

public class MotionTrap extends TrapImpl {
    Image image;

    public MotionTrap(String description) {
        super(description);
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
