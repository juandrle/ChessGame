package Business.GameLogic;

import Business.Competition.Competition;
import Business.Gamepiece.Gamepiece;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;


public interface Player {
    boolean moveGamepiece(Gamepiece gamepiece, Field field, Game game);

    Gamepiece chooseGamepiece(Gamepiece gamepiece);

    void useItem(Gamepiece gamepiece);

    void setName(String name);

    String getName();

    void removeGamepiece(Player player, Competition competition);

    void addNewGamepiece(Gamepiece g);

    void initGamepieces();

    ObservableList<Gamepiece> getOwnGamepieces();

    SimpleBooleanProperty isEngaged();

    void setTurn(boolean turn);

    boolean getTurn();

    Gamepiece getCurrGamepiece();

    Gamepiece getEnemyGamepiece();

    void setCurrGamepiece(Gamepiece gamepiece);

    void setEnemyGamepiece(Gamepiece gamepiece);

    void setCompetitionField(Field field);
    public SimpleObjectProperty<Gamepiece> currGamepieceProperty();
    public SimpleBooleanProperty turnProperty();

    boolean getExtraTime();
    void setExtraTime(boolean extraTime);

}
