package Business.Item.StatusChange.Manipulator;

import Business.GameLogic.Game;
import Business.Gamepiece.Gamepiece;
import Business.Gamepiece.Queen;
import Business.Gamepiece.Tower;
import Business.Item.StatusChange.StatusChangeImpl;

public class RankManipulator extends StatusChangeImpl {

    public RankManipulator(String description) {
        super(description);
    }

    @Override
    public void applyStatusChange(Gamepiece gamepiece) {
        //TODO delete the old gamepiece everywhere <<<<<<<<<<<<<<<<<<<<<<<<<<

        if(gamepiece.getRank() == 0){//Bauer nimmt auf
            Gamepiece pawnToTower = new Tower();
            pawnToTower.setPosition(gamepiece.getPosition());
            if(gamepiece.getInventory() != null)
                pawnToTower.setInventory(gamepiece.getInventory());
        }
        else if(gamepiece.getRank() == 1){//Tower nimmt auf
            Gamepiece TowerToQueen = new Queen();
            TowerToQueen.setPosition(gamepiece.getPosition());
            if(gamepiece.getInventory() != null)
                TowerToQueen.setInventory(gamepiece.getInventory());
        }
        else if(gamepiece.getRank() == 2){//Queen nimmt auf
            Gamepiece queenToTower = new Tower();
            queenToTower.setPosition(gamepiece.getPosition());
            if(gamepiece.getInventory() != null)
                queenToTower.setInventory(gamepiece.getInventory());
        }
    }
}
