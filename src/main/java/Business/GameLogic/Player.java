package Business.GameLogic;

import Business.Gamepiece.Gamepiece;
import javafx.beans.property.SimpleBooleanProperty;

import java.util.List;

public interface Player {
    public boolean moveGamepiece(Gamepiece gamepiece, Field field, Game game);

    Gamepiece chooseGamepiece(Gamepiece gamepiece);

    public void useItem(Gamepiece gamepiece);

    public void setName(String name);
    public String getName();
    public void removeGamepiece(Gamepiece gamepiece);
    public void addNewGamepiece(Gamepiece g);
    public void initGamepieces();
    public List<Gamepiece> getOwnGamepieces();
    public SimpleBooleanProperty isEngaged();
    public void setTurn(boolean turn);
    public boolean getTurn();


}
