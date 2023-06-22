package Business.GameLogic;

import java.util.List;

public interface Gamefield {

    void createGamefield();

    Player getPlayer1();

    List<Field> getFields();

    Field getField(int row, int column);

    Player getPlayer2();
}

