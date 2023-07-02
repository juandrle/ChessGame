package business.item.trap;

import business.gameLogic.Game;
import business.gameLogic.Player;
import business.gamepiece.Gamepiece;
import business.gamepiece.Pawn;
import business.gamepiece.Queen;
import business.gamepiece.Tower;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.image.Image;

import java.util.Objects;

public class TeleportationTrap extends TrapImpl {
    final Image image;
    public TeleportationTrap(String description) {
        super(description);
        image = new Image("files/pictures/Items/Teleportationtrap.png");
    }

    @Override
    public void applyTrap(Gamepiece gamepiece, SimpleBooleanProperty engaged, Game game) {

        Player enemyPlayer = null;
        if (game.getCurrentPlayer() == game.getGamefield().getPlayer1()) {
            enemyPlayer = game.getGamefield().getPlayer2();
        } else if (game.getCurrentPlayer() == game.getGamefield().getPlayer2()) {
            enemyPlayer =game.getGamefield().getPlayer1();
        }

        for(Gamepiece enemygamepiece :  Objects.requireNonNull(enemyPlayer).getOwnGamepieces()){
            System.out.println("enemy: "+enemygamepiece);
            System.out.println("own: "+gamepiece);
            if(enemygamepiece instanceof Pawn){
                System.out.println(gamepiece);
                System.out.println(enemygamepiece);
                game.getCurrentPlayer().setCurrGamepiece(gamepiece);
                game.getCurrentPlayer().setEnemyGamepiece(enemygamepiece);
                engaged.set(true);
                break;

            }else if(enemygamepiece instanceof Tower){
                System.out.println(gamepiece);
                System.out.println(enemygamepiece);
                game.getCurrentPlayer().setCurrGamepiece(gamepiece);
                game.getCurrentPlayer().setEnemyGamepiece(enemygamepiece);
                engaged.set(true);
                break;

            }else if(enemygamepiece instanceof Queen){
                System.out.println(gamepiece);
                System.out.println(enemygamepiece);
                game.getCurrentPlayer().setCurrGamepiece(gamepiece);
                game.getCurrentPlayer().setEnemyGamepiece(enemygamepiece);
                engaged.set(true);
                break;
            }
        }
    }
    @Override
    public Image getImage() {
        return image;
    }
}
