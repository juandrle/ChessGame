package business.gameLogic;

import business.gamepiece.Gamepiece;
import business.item.Item;
import business.item.statusChange.manipulator.RankManipulator;
import business.item.statusChange.manipulator.TimeManipulator;
import business.item.statusChange.Shield;
import business.item.trap.MotionTrap;
import business.item.trap.TeleportationTrap;

import java.util.ArrayList;
import java.util.List;

public class GamefieldImpl implements Gamefield {
    private int maxItemAmount;
    private final List<Field> fields;
    private final Player player1;
    private final Player player2;
    private final Game game;

    public GamefieldImpl(boolean newGame, Game game) {
        this.maxItemAmount = 10;
        this.fields = new ArrayList<>();
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
        Item item;
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                gamepiece = null;
                item = null;
                switch (row) {
                    case 3 -> {
                        switch (column) {
                            case 0 -> gamepiece = this.player1.getOwnGamepieces().get(2);

                            case 1 -> gamepiece = this.player1.getOwnGamepieces().get(0);

                            case 6 -> gamepiece = this.player2.getOwnGamepieces().get(0);

                            case 7 -> gamepiece = this.player2.getOwnGamepieces().get(3);
                        }
                    }
                    case 4 -> {
                        switch (column) {
                            case 0 -> gamepiece = this.player1.getOwnGamepieces().get(3);

                            case 1 -> gamepiece = this.player1.getOwnGamepieces().get(1);

                            case 6 -> gamepiece = this.player2.getOwnGamepieces().get(1);

                            case 7 -> gamepiece = this.player2.getOwnGamepieces().get(2);
                        }
                    }
                }
                if ((int) (Math.random() * 6) == 1 && this.maxItemAmount > 0 && gamepiece == null) {
                    randNum = (int) (Math.random() * 5);
                    switch (randNum) {
                        case 0 -> item = new Shield("shield");
                        case 1 -> item = new RankManipulator("rankmanipulator",game);
                        case 2 -> item = new TimeManipulator("timemanipulator");
                        case 3 -> item = new TeleportationTrap("teleportationtrap");
                        case 4 -> item = new MotionTrap("motiontrap");
                    }
                    this.maxItemAmount--;
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
