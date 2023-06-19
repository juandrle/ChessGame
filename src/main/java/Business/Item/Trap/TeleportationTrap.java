package Business.Item.Trap;

import javafx.scene.image.Image;

public class TeleportationTrap extends TrapImpl {
    Image image;
    public TeleportationTrap(String description) {
        super(description);
        image = new Image("files/pictures/Items/Teleportationtrap.png");
    }

    @Override
    public void applyTrap() {
    }
    @Override
    public Image getImage() {
        return image;
    }
}
