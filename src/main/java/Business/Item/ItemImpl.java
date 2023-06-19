package Business.Item;

import javafx.scene.image.Image;

public class ItemImpl implements Item {

    Image image;
    @Override
    public boolean isDropable() {
        return false;
    }
    public Image getImage() {
        return image;
    }
}
