package Business.Item.StatusChange;

import Business.Gamepiece.Gamepiece;
import javafx.scene.image.Image;

public class Shield extends StatusChangeImpl {
    Image image;

    public Shield(String description) {
        super(description);
        image = new Image("files/pictures/Items/Shield.png");
    }

    @Override
    public void applyStatusChange(Gamepiece gamepiece) {
        Shield shield = new Shield("Das Schild schütz Sie bei einer Niederlage in einem Minispiel und gibt ihnen die Chance auf ein Rematch.");
        gamepiece.setInventory(shield);
    }
    @Override
    public Image getImage() {
        return image;
    }

}
