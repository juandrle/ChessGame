package business.item.statusChange;

import business.gameLogic.Field;
import business.gamepiece.Gamepiece;
import javafx.scene.image.Image;

public class StatusChangeImpl implements StatusChange {
    private String description;
    Image image;
    Field position;

    public StatusChangeImpl(String description) {
        this.description = description;
    }

    @Override
    public boolean isDropable() {
        return false;
    }

    @Override
    public void setIsDropable(boolean dropable) {

    }

    @Override
    public void setPosition(Field f) {
        this.position = f;
        f.setItem(this);
    }



    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public void applyStatusChange(Gamepiece gamepiece) {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
