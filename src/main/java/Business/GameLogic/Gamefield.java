package Business.GameLogic;

import java.util.List;

public interface Gamefield {


    public void createGamefield();

    public Player getPlayer1();

    public List<Field> getFields();

    public Field getField(int row, int column);

    public Player getPlayer2();


}

