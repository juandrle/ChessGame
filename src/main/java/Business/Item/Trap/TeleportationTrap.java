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

        Player enemyPlayer = null;
        if (game.getCurrentPlayer() == game.getGamefield().getPlayer1()) {
            enemyPlayer = game.getGamefield().getPlayer2();
        } else if (game.getCurrentPlayer() == game.getGamefield().getPlayer2()) {
            enemyPlayer =game.getGamefield().getPlayer1();
        }

        for(Gamepiece enemygamepiece :  enemyPlayer.getOwnGamepieces()){
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
