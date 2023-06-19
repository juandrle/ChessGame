package Business.Item.Trap;

import javafx.scene.image.Image;

public class MotionTrap extends TrapImpl {
    Image image;

    public MotionTrap(String description) {
        super(description);
        image = new Image("files/pictures/Items/Motiontrap.png");
    }

    @Override
    public void applyTrap() {
    }
    @Override
    public Image getImage() {
        return image;
    }
}
