package business.item.statusChange;

import javafx.scene.image.Image;

public class Shield extends StatusChangeImpl {
    final Image image;


    public Shield(String description) {
        super(description);
        image = new Image("files/pictures/Items/Shield.png");
    }

    @Override
    public Image getImage() {
        return image;
    }


}
