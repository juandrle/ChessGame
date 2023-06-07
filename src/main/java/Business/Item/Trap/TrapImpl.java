package Business.Item.Trap;

public class TrapImpl implements Trap {
    String description;

    public TrapImpl(String description) {
        this.description = description;
    }

    @Override
    public boolean isDropable() {
        return true;
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
