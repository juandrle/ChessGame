package business.item.statusChange.manipulator;

import business.item.statusChange.StatusChangeImpl;
import javafx.scene.image.Image;

public class TimeManipulator extends StatusChangeImpl {

    final Image image;
    public TimeManipulator() {
        setDescription("Dieses Item ist nur \n vor einem Wettkampf \n einsetzbar und erhöht \n die Zeit um 50%");
        image = new Image("files/pictures/Items/Timemanipulator.png");
    }

    @Override
    public Image getImage() {
        return image;
    }
}
