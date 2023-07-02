package business.gameLogic;

import business.competition.Competition;
import business.gamepiece.Gamepiece;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;


public interface Player {
    boolean moveGamepiece(Gamepiece gamepiece, Field field, Game game);

    void chooseGamepiece(Gamepiece gamepiece);

    boolean useItem(Gamepiece gamepiece);

    void setName(String name);

    SimpleBooleanProperty itemUsedProperty();
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
    SimpleObjectProperty<Gamepiece> currGamepieceProperty();
    SimpleBooleanProperty turnProperty();
    void setPosItemUsed(Field field, Gamepiece gamepiece, Game game);
    boolean isItemUsed();

}
