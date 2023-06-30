package Business.Item.StatusChange.Manipulator;

import Business.Gamepiece.Gamepiece;
import Business.Item.StatusChange.StatusChangeImpl;
import javafx.scene.image.Image;

public class TimeManipulator extends StatusChangeImpl {

    Image image;
    public TimeManipulator(String description) {
        super(description);
        image = new Image("files/pictures/Items/Timemanipulator.png");
    }

    @Override
    public void applyStatusChange(Gamepiece gamepiece) {

    }
    @Override
    public Image getImage() {
        return image;
    }
}
