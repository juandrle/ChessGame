package Business.Gamepiece;

import Business.GameLogic.Field;
import Business.GameLogic.Game;
import Business.Item.Item;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;

import java.util.List;

public interface Gamepiece {

    public boolean isValidMove(Field newPos, Game game);

    public boolean isMoveable();
    
    public void setInventory(Item inv);

    public Item getInventory();

    public void setRank(int rnk);

    public int getRank();

    public void setMoveable(boolean moveab);

    void setPosition(Field field);

    public Field getPosition();
    public SimpleObjectProperty<Image> getImage();

    public void setImage(Image image);
    public List<Field> possibleMoves(Game game);
    public void setPoints(int points);
    public int getPoints();
    public int getTimeMultiplier();
    public void setTimeMultiplier(int multiplier);
    public SimpleIntegerProperty propertyTimeMultiplier();

}
