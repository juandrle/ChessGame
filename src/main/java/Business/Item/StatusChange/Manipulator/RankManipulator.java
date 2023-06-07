package Business.Item.StatusChange.Manipulator;

import Business.Gamepiece.Gamepiece;
import Business.Item.StatusChange.StatusChangeImpl;

public class RankManipulator extends StatusChangeImpl {

    public RankManipulator(String description) {
        super(description);
    }

    @Override
    public void applyStatusChange(Gamepiece gamepiece) {
        int rank;
        if ((rank = gamepiece.getRank()) < 2)
            gamepiece.setRank(rank + 1);
        else gamepiece.setRank(rank - 1);
    }
}
