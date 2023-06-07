package Business.GameLogic;

import Business.Gamepiece.Gamepiece;

public interface Player {
    public void moveGamepiece(Gamepiece gamepiece);

    public Gamepiece chooseGamepiece();

    public void useItem();

    public void setName(String name);
    public String getName();
    public void removeGamepiece(Gamepiece gamepiece);
    public Gamepiece getGamepiece(Gamepiece gamepiece);


}
