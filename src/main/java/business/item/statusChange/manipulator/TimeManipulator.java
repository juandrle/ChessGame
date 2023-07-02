package business.item.statusChange.manipulator;

import business.item.statusChange.StatusChangeImpl;
import javafx.scene.image.Image;

public class TimeManipulator extends StatusChangeImpl {

    final Image image;
    public TimeManipulator(String description) {
        super(description);
        image = new Image("files/pictures/Items/Timemanipulator.png");
    }

    @Override
    public Image getImage() {
        return image;
    }
}
