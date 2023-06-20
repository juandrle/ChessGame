package Business.Item;

import javafx.scene.image.Image;

public class ItemImpl implements Item {

    Image image;
    String description;
    @Override
    public boolean isDropable() {
        return false;
    }
    public Image getImage() {
        return image;
    }
    public String getDescription(){
        return description;
    }
}
