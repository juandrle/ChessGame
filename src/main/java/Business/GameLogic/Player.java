package Business.GameLogic;

import Business.Gamepiece.Gamepiece;

public interface Player {
    public void moveGamepiece(Gamepiece gamepiece, Field field);

    Gamepiece chooseGamepiece(Gamepiece gamepiece);

    public void useItem();

    public void setName(String name);
    public String getName();
    public void removeGamepiece(Gamepiece gamepiece);



}
