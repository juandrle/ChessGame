package Business.Item.StatusChange;

import Business.Gamepiece.Gamepiece;

public class StatusChangeImpl implements StatusChange {
    private String description;

    public StatusChangeImpl(String description) {
        this.description = description;
    }

    @Override
    public boolean isDropable() {
        return false;
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
