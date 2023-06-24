package Business.Item.Trap;

import Business.GameLogic.Field;
import Business.GameLogic.Game;
import Business.Gamepiece.Gamepiece;
import Business.Gamepiece.Pawn;
import Business.Item.StatusChange.Manipulator.TimeManipulator;
import javafx.scene.image.Image;
import Business.GameLogic.Field;
import java.util.ArrayList;
import java.util.List;

public class TrapImpl implements Trap {
    String description;
    Image image;
    private boolean isDropable = false;

    private Pawn pawnMovability;

    public TrapImpl(String description) {
        this.description = description;
        this.pawnMovability = new Pawn();
    }

    @Override
    public boolean isDropable() {
        return this.isDropable;
    }

    @Override
    public void setIsDropable(boolean isDropable) {
        this.isDropable = isDropable;
    }

    @Override
    public void setPosition(Field f) {
    }

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void applyTrap() {
    }

    @Override
    public Pawn getPawnMovability() {
        return pawnMovability;
    }

    @Override
    public List<Field> possibleMoves(Game game) {
        return pawnMovability.possibleMoves(game);
    }

    @Override
    public boolean isValidMove(Field newPos, Game game) {
        return pawnMovability.isValidMove(newPos, game);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
