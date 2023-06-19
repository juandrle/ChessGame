package Business.Item.Trap;

import javafx.scene.image.Image;

public class TrapImpl implements Trap {
    String description;
    Image image;

    public TrapImpl(String description) {
        this.description = description;
    }

    @Override
    public boolean isDropable() {
        return true;
    }

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void applyTrap() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
