package Business.Item.StatusChange.Manipulator;

import Business.Gamepiece.Gamepiece;
import Business.Item.StatusChange.StatusChangeImpl;

public class TimeManipulator extends StatusChangeImpl {

    public TimeManipulator(String description) {
        super(description);
    }

    @Override
    public void applyStatusChange(Gamepiece gamepiece) {
        TimeManipulator timeManipulator = new TimeManipulator();
        gamepiece.setInventory(timeManipulator);
    }
}
