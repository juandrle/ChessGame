package Business.GameLogic;

import Business.Competition.Competition;
import Business.Gamepiece.Gamepiece;
import Business.Gamepiece.Pawn;
import Business.Gamepiece.Queen;
import Business.Gamepiece.Tower;
import Business.Item.StatusChange.Manipulator.RankManipulator;
import Business.Item.StatusChange.Manipulator.TimeManipulator;
import Business.Item.Trap.MotionTrap;
import Business.Item.Trap.TeleportationTrap;
import Business.Item.Trap.Trap;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PlayerImpl implements Player {
    private String name;
    private ObservableList<Gamepiece> ownGamepieces;
    private SimpleBooleanProperty engaged;
    private boolean turn;
    private SimpleObjectProperty<Gamepiece> currGamepiece;
    private Gamepiece enemyGamepiece;
    private Field competitionField;
    private boolean extraTime = false;

    public PlayerImpl(String name, boolean newGame) {
        this.name = name;
        engaged = new SimpleBooleanProperty();
        this.ownGamepieces = FXCollections.observableArrayList();
        currGamepiece = new SimpleObjectProperty<>();
        if(newGame) initGamepieces();
        this.turn = false;

    }

    public boolean getExtraTime(){
        return this.extraTime;
    }

    public void setExtraTime(boolean extra){
        this.extraTime = extra;
    }

    @Override
    public boolean moveGamepiece(Gamepiece gamepiece, Field field, Game game) {
        chooseGamepiece(gamepiece);
        if (field.getGamepiece() != null) {
            enemyGamepiece = field.getGamepiece();
            engaged.set(true);
            if(gamepiece.getInventory() instanceof TimeManipulator) this.setExtraTime(true);
            currGamepiece.get().getPosition().setGamepiece(null);
            this.turn = false;
            competitionField = field;
            return true;
        }
        if (!this.turn) return false;
        if (currGamepiece == null) return false;
        if (!currGamepiece.get().isValidMove(field, game)) return false;
        currGamepiece.get().getPosition().setGamepiece(null);
        currGamepiece.get().setPosition(field);
        currGamepiece.get().getPosition().setGamepiece(currGamepiece.get());
        if (field.getItem() != null) {
            currGamepiece.get().setInventory(field.getItem());
            currGamepiece.set(null);
            chooseGamepiece(gamepiece);
            currGamepiece.get().getPosition().setItem(null);
        }
        this.turn = false;
        return true;
    }

    @Override
    public Gamepiece chooseGamepiece(Gamepiece gamepiece) {
        for (Gamepiece currGamepiece : ownGamepieces)
            if (currGamepiece.equals(gamepiece)) {
                this.currGamepiece.set(currGamepiece);
                return currGamepiece;
            }
        return null;
    }

    @Override
    public void useItem(Gamepiece gamepiece) {
        if(gamepiece.getInventory() instanceof RankManipulator){
            ((RankManipulator) gamepiece.getInventory()).applyStatusChange(gamepiece);
        }
        else if( gamepiece.getInventory() instanceof TimeManipulator) {
            // gamepiece.getInventory();

        }else if (gamepiece.getInventory() instanceof Trap) {

            // Überprüfen, ob die Falle aktiv ist
            if (!((Trap) gamepiece.getInventory()).isDropable()) {
                if (gamepiece.getInventory() instanceof MotionTrap) {
                    gamepiece.setMoveable(false);
                    //TODO game.getEffectedGamepieces().add(gamepice,game.getTurncount+6) soll hier sein
                } else if (gamepiece.getInventory() instanceof TeleportationTrap) {
                    //TODO if frage welcher player und der player der man nicht ist ist der enemy und dann get.owngamepieces danach durch iterieren...
                }
            }

            else {
                //TODO ablegen von fallen und setDrople(false)

            }
        }

        System.out.println(chooseGamepiece(gamepiece).getInventory());
        }


    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void removeGamepiece(Player player, Competition competition) {
        if (!currGamepiece.get().equals(competition.whoWin(currGamepiece.get(), enemyGamepiece))) {
            this.ownGamepieces.remove(currGamepiece.get());
            enemyGamepiece.setPosition(competitionField);
            competitionField.setGamepiece(enemyGamepiece);
            enemyGamepiece.setPoints(-1);
        } else {
            player.setCurrGamepiece(enemyGamepiece);
            player.setEnemyGamepiece(currGamepiece.get());
            player.setCompetitionField(competitionField);
            player.removeGamepiece(this, competition);
        }
        engaged.set(false);
    }

    @Override
    public void addNewGamepiece(Gamepiece g) {

    }

    @Override
    public void initGamepieces() {
        ownGamepieces.add(new Pawn());
        ownGamepieces.add(new Pawn());
        ownGamepieces.add(new Queen());
        ownGamepieces.add(new Tower());
    }

    @Override
    public ObservableList<Gamepiece> getOwnGamepieces() {
        return ownGamepieces;
    }

    public SimpleBooleanProperty isEngaged() {
        return engaged;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    @Override
    public boolean getTurn() {
        return this.turn;
    }

    public Gamepiece getCurrGamepiece() {
        return currGamepiece.get();
    }

    public SimpleObjectProperty<Gamepiece> currGamepieceProperty() {
        return currGamepiece;
    }

    public Gamepiece getEnemyGamepiece() {
        return enemyGamepiece;
    }

    public void setCurrGamepiece(Gamepiece gamepiece) {
        this.currGamepiece.set(gamepiece);
    }

    public void setEnemyGamepiece(Gamepiece gamepiece) {
        this.enemyGamepiece = gamepiece;
    }

    @Override
    public void setCompetitionField(Field field) {
        this.competitionField = field;
    }
}
