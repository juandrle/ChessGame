package Business.Gamepiece;

public interface Gamepiece {
    Item inventory;
    int rank;
    boolean moveable;

    public boolean isValidMove();

    public boolean isMoveable();
}
