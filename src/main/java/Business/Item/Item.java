package Business.Item;

import Business.GameLogic.Field;
import javafx.scene.image.Image;

public interface Item {
    boolean isDropable();
    void setIsDropable(boolean dropable);

    void setPosition(Field f);

    public Image getImage();
    public String getDescription();

}
