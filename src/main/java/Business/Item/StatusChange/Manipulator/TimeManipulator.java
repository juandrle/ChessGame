package Business.Item.StatusChange.Manipulator;

import Business.Gamepiece.Gamepiece;
import Business.Item.StatusChange.StatusChangeImpl;

public class TimeManipulator extends StatusChangeImpl {

    private String description;

    public TimeManipulator(String description) {
        super(description);
        this.description = description;
    }

    @Override
    public void applyStatusChange(Gamepiece gamepiece) {
        TimeManipulator timeManipulator = new TimeManipulator(description);
        gamepiece.setInventory(timeManipulator);
    }
}
