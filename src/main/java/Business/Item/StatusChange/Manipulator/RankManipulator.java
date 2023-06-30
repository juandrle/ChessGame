package Business.Item.StatusChange.Manipulator;

import Business.GameLogic.Game;
import Business.Gamepiece.Gamepiece;
import Business.Gamepiece.Queen;
import Business.Gamepiece.Tower;
import Business.Item.StatusChange.StatusChangeImpl;
import javafx.scene.image.Image;

public class RankManipulator extends StatusChangeImpl {
    Image image;
    Game game;

    public RankManipulator(String description, Game game) {
        super(description);
        image = new Image("files/pictures/Items/Rankmanipulator.png");
        this.game = game;
    }
    
    @Override
    public void applyStatusChange(Gamepiece gamepiece) {
        Gamepiece newPiece = null;
        if(gamepiece.getRank() == 0){//Bauer nimmt auf
            newPiece = new Tower();
        }
        else if(gamepiece.getRank() == 1){//Tower nimmt auf
            newPiece = new Queen();
        }
        else {//Queen nimmt auf
            newPiece = new Tower();
        }

        newPiece.setPosition(gamepiece.getPosition());
        gamepiece.getPosition().setGamepiece(newPiece);

        game.getCurrentPlayer().getOwnGamepieces().remove(gamepiece);
        game.getCurrentPlayer().getOwnGamepieces().add(newPiece);
    }
    @Override
    public Image getImage() {
        return image;
    }
}
