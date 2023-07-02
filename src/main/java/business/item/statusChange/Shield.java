package business.item.statusChange;

import javafx.scene.image.Image;

public class Shield extends StatusChangeImpl {
    final Image image;


    public Shield() {
        this.setDescription("Mit diesem Item wird einer \n Spielfigur einmal das \n Leben gerettet");
        image = new Image("files/pictures/Items/Shield.png");
    }

    @Override
    public Image getImage() {
        return image;
    }


}
