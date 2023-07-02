package business.item;

import business.gameLogic.Field;
import javafx.scene.image.Image;

public interface Item {
    boolean isDropable();
    void setIsDropable(boolean dropable);

    void setPosition(Field f);

    Image getImage();
    String getDescription();

}
