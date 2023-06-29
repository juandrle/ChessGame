package Business.GameLogic;

import Business.Gamepiece.Gamepiece;
import Business.Item.Item;
import Business.Item.StatusChange.Manipulator.RankManipulator;
import Business.Item.StatusChange.Manipulator.TimeManipulator;
import Business.Item.StatusChange.Shield;
import Business.Item.Trap.MotionTrap;
import Business.Item.Trap.TeleportationTrap;

import java.util.ArrayList;
import java.util.List;

public class GamefieldImpl implements Gamefield {
    int maxItemAmount = 10;
    List<Field> fields;
    Player player1;
    Player player2;
    boolean newGame = true;
    Game game;

    public GamefieldImpl(boolean newGame, Game game) {
        this.fields = new ArrayList<>();
        this.newGame = newGame;
        this.game = game;
        if (newGame)this.player1 = new PlayerImpl("susi",true);
        else this.player1 = new PlayerImpl("susi", false);
        if (newGame)this.player2 = new PlayerImpl("Wolle",true);
        else this.player2 = new PlayerImpl("busi", false);
        if(newGame)createGamefield();
        else createDefaultGamefield();

    }

    public void createDefaultGamefield(){
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                this.fields.add(new FieldImpl(row, column, null, null));
            }
        }
    }

    public void createGamefield() {
        Gamepiece gamepiece;
        int randNum;
        Item item = null;
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                switch (row) {
                    case 3 -> {
                        switch (column) {
                            case 0 -> gamepiece = this.player1.getOwnGamepieces().get(2);

                            case 1 -> gamepiece = this.player1.getOwnGamepieces().get(0);

                            case 6 -> gamepiece = this.player2.getOwnGamepieces().get(0);

                            case 7 -> gamepiece = this.player2.getOwnGamepieces().get(3);

                            default -> gamepiece = null;
                        }
                    }
                    case 4 -> {
                        switch (column) {
                            case 0 -> gamepiece = this.player1.getOwnGamepieces().get(3);

                            case 1 -> gamepiece = this.player1.getOwnGamepieces().get(1);

                            case 6 -> gamepiece = this.player2.getOwnGamepieces().get(1);

                            case 7 -> gamepiece = this.player2.getOwnGamepieces().get(2);

                            default -> gamepiece = null;
                        }
                    }
                    default -> {
                        gamepiece = null;
                        if ((int) (Math.random() * 6) == 1 && this.maxItemAmount > 0) {
                            randNum = (int) (Math.random() * 5);
                            switch (randNum) {
                                case 0 -> item = new Shield("shield");
                                case 1 -> item = new RankManipulator("rankmanipulator",game);
                                case 2 -> item = new TimeManipulator("timemanipulator");
                                case 3 -> item = new TeleportationTrap("teleportationtrap");
                                case 4 -> item = new MotionTrap("motiontrap");
                            }
                            this.maxItemAmount--;
                        } else item = null;
                    }
                }
                this.fields.add(new FieldImpl(row, column, gamepiece, item));

                if (gamepiece != null) gamepiece.setPosition(fields.get(row * 8 + column));
            }
        }
    }

    public Player getPlayer1() {
        return this.player1;
    }

    public List<Field> getFields() {
        return this.fields;
    }

    public Field getField(int row, int column) {
        for (Field f : this.fields) {
            if (f.getColumn() == column && f.getRow() == row)
                return f;
        }
        return null;
    }

    public Player getPlayer2() {
        return this.player2;
    }


}
