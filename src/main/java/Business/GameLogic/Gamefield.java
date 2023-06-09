package Business.GameLogic;

import java.util.List;

public interface Gamefield {


    public void createGamefield();

    public Player getPlayer1();

    public List<Field> getFields();

    public FieldImpl getField();

    public Player getPlayer2();


}

