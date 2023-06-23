package Business.Item.Trap;

import javafx.scene.image.Image;

public class TrapImpl implements Trap {
    String description;
    Image image;
    private boolean isDropable = false;

    public TrapImpl(String description) {
        this.description = description;
    }

    @Override
    public boolean isDropable() {
        return this.isDropable;
    }

    @Override
    public void setIsDropable(boolean isDropable) {
        this.isDropable = isDropable;
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
