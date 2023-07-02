package business.item;

import business.gameLogic.Field;
import javafx.scene.image.Image;

public class ItemImpl implements Item {

    Image image;
    String description;
    private boolean isDropable = false;

    Field position;
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

    public Image getImage() {
        return image;
    }
    public String getDescription(){
        return description;
    }
}
