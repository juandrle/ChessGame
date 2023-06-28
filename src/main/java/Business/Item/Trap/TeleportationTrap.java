package Business.Item.Trap;

import Business.GameLogic.Game;
import Business.GameLogic.Player;
import Business.Gamepiece.Gamepiece;
import Business.Gamepiece.Pawn;
import Business.Gamepiece.Queen;
import Business.Gamepiece.Tower;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.image.Image;

public class TeleportationTrap extends TrapImpl {
    Image image;
    public TeleportationTrap(String description) {
        super(description);
        image = new Image("files/pictures/Items/Teleportationtrap.png");
    }

    @Override
    public void applyTrap(Gamepiece gamepiece, SimpleBooleanProperty engaged, Game game) {
        Player enemyGamepieces = null;
        if(game.getGamefield().getPlayer1() != game.getCurrentPlayer()){
            enemyGamepieces = game.getGamefield().getPlayer1();
        }else if (game.getGamefield().getPlayer2() != game.getCurrentPlayer()){
            enemyGamepieces = game.getGamefield().getPlayer2();
        }

        for(Gamepiece enemygamepiece : enemyGamepieces.getOwnGamepieces()){
            if(enemygamepiece instanceof Pawn){
                if(gamepiece instanceof Pawn || gamepiece instanceof Tower){
                    engaged.set(true);
                    break;
                }
            }else if(enemygamepiece instanceof Tower){
                if(gamepiece instanceof Queen){
                    engaged.set(true);
                    break;
                }
            }else if(enemygamepiece instanceof Queen){
                if(gamepiece instanceof  Queen){
                    engaged.set(true);
                    break;
                }
            }
        }
    }
    @Override
    public Image getImage() {
        return image;
    }
}
