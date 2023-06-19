package Business.Gamepiece;

import Business.GameLogic.Field;
import Business.Item.Item;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;

public interface Gamepiece {

    public boolean isValidMove(Field newPos);

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
}
