package business.gamepiece;

import business.gameLogic.Field;
import business.gameLogic.Game;
import business.item.Item;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;

import java.util.List;

public interface Gamepiece {

    boolean isValidMove(Field newPos, Game game);

    boolean isMoveable();
    
    void setInventory(Item inv);

    Item getInventory();

    void setRank(int rnk);

    int getRank();

    void setMoveable(boolean moveab);

    void setPosition(Field field);

    Field getPosition();
    SimpleObjectProperty<Image> getImage();

    void setImage(Image image);
    List<Field> possibleMoves(Game game);
    void setPoints(int points);
    int getPoints();
    int getTimeMultiplier();
    void setTimeMultiplier(int multiplier);
    SimpleIntegerProperty propertyTimeMultiplier();

}
