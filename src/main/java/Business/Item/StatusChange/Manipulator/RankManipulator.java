package Business.Item.StatusChange.Manipulator;

import Business.Gamepiece.Gamepiece;
import Business.Item.StatusChange.StatusChangeImpl;

public class RankManipulator extends StatusChangeImpl {

    public RankManipulator(String description) {
        super(description);
    }

    @Override
    public void applyStatusChange(Gamepiece gamepiece) {
        //TODO delete the old gamepiece everywhere <<<<<<<<<<<<<<<<<<<<<<<<<<

        if(gamepiece.getRank() == 0){//Bauer nimmt auf
            Tower pawnToTower = new Tower();
            pawnToTower.setPosition(gamepiece.getPosition());
            if(gamepiece.getInventory != null)
                pawnToTower.setInventory(gamepiece.getInventory());
        }
        else if(gamepiece.getRank() == 1){//Tower nimmt auf
            Queen TowertoQueen = new Queen();
            TowertoQueen.setPosition(gamepiece.getPosition());
            if(gamepiece.getInventory != null)
                TowertoQueen.setInventory(gamepiece.getInventory());
        }
        else if(gamepiece.getRank() == 2){//Queen nimmt auf
            Tower queenToTower = new Tower();
            queenToTower.setPosition(gamepiece.getPosition());
            if(gamepiece.getInventory != null)
                queenToTower.setInventory(gamepiece.getInventory());
        }
    }
}
