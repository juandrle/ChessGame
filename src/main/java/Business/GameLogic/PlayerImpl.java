package Business.GameLogic;

import Business.Competition.Competition;
import Business.Gamepiece.Gamepiece;
import Business.Gamepiece.Pawn;
import Business.Gamepiece.Queen;
import Business.Gamepiece.Tower;
import Business.Item.Item;
import Business.Item.StatusChange.Manipulator.RankManipulator;
import Business.Item.StatusChange.Manipulator.TimeManipulator;
import Business.Item.Trap.MotionTrap;
import Business.Item.Trap.TeleportationTrap;
import Business.Item.Trap.Trap;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class PlayerImpl implements Player {
    private String name;
    private SimpleBooleanProperty itemUsed;
    private ObservableList<Gamepiece> ownGamepieces;
    private SimpleBooleanProperty engaged;
    private SimpleBooleanProperty turn;
    private SimpleObjectProperty<Gamepiece> currGamepiece;
    private Gamepiece enemyGamepiece;
    private Field competitionField;

    public PlayerImpl(String name, boolean newGame) {
        itemUsed = new SimpleBooleanProperty(false);
        this.name = name;
        turn = new SimpleBooleanProperty(false);
        engaged = new SimpleBooleanProperty();
        this.ownGamepieces = FXCollections.observableArrayList();
        currGamepiece = new SimpleObjectProperty<>();
        if(newGame) initGamepieces();

    }
    @Override
    public boolean moveGamepiece(Gamepiece gamepiece, Field field, Game game) {
        chooseGamepiece(gamepiece);
        if (field.getGamepiece() != null) {
            enemyGamepiece = field.getGamepiece();
            engaged.set(true);
            currGamepiece.get().getPosition().setGamepiece(null);
            this.turn.set(false);
            competitionField = field;
            return true;
        }
        if (!this.turn.get()) return false;
        if (currGamepiece == null) return false;
        if (!currGamepiece.get().isValidMove(field, game)) return false;
        currGamepiece.get().getPosition().setGamepiece(null);
        currGamepiece.get().setPosition(field);
        currGamepiece.get().getPosition().setGamepiece(currGamepiece.get());
        if (field.getItem() != null) {
            if (field.getItem() instanceof Trap && (((Trap) field.getItem()).isActive())) {
                ((Trap) field.getItem()).applyTrap(gamepiece, engaged, game);

            }else{
                currGamepiece.get().setInventory(field.getItem());
            }
            currGamepiece.set(null);
            chooseGamepiece(gamepiece);
            currGamepiece.get().getPosition().setItem(null);
        }
        this.turn.set(false);
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
    public boolean useItem(Gamepiece gamepiece) {
        // hier noch true falls gedroppt werden soll und false falls nicht
        if(gamepiece.getInventory() instanceof RankManipulator){
            ((RankManipulator) gamepiece.getInventory()).applyStatusChange(gamepiece);
            gamepiece.setInventory(null);
        }
        else if( gamepiece.getInventory() instanceof TimeManipulator) {
            gamepiece.setTimeMultiplier(15);
            gamepiece.setInventory(null);

        }else if (gamepiece.getInventory() instanceof Trap) {
            itemUsed.set(true);
            return true;
        }
        return false;
        }


    public boolean setPosItemUsed(Field field, Gamepiece gamepiece,Game game) {

        if (game.getCurrentPlayer().useItem(gamepiece)) {

            List<Gamepiece> ownGamepieces = game.getCurrentPlayer().getOwnGamepieces();
            List<Gamepiece> enmyGamepieces = game.getNextPlayer().getOwnGamepieces();

            // bereits von einem Gamepiece besetzt ist?
            for (Gamepiece g : ownGamepieces) {
                if (field == g.getPosition()) {
                    return false;
                }
            }

            for (Gamepiece g : enmyGamepieces) {
                if (field == g.getPosition()) {
                    return false;
                }
            }

            Item item = gamepiece.getInventory();
            Item tmpItem = field.getItem();

            if (tmpItem == null ) {

                if (item instanceof Trap) {
                    ((Trap) item).setActive(true);
                    item.setIsDropable(false);
                    itemUsed.set(false);

                    // neue Position setzten
                    field.setItem((Trap) item);
                    gamepiece.setInventory(null);
                }
            }
        }
        return true;
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
            if(competitionField == null){
                enemyGamepiece.getPosition().setGamepiece(null);
                competitionField = currGamepiece.get().getPosition();
            }
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
        getCurrGamepiece().setTimeMultiplier(10);
        getEnemyGamepiece().setTimeMultiplier(10);
        engaged.set(false);
    }

    @Override
    public void addNewGamepiece(Gamepiece g) {
        this.ownGamepieces.add(g);
    }

    @Override
    public void initGamepieces() {
        ownGamepieces.add(new Pawn());
        ownGamepieces.add(new Pawn());
        ownGamepieces.add(new Queen());
        ownGamepieces.add(new Tower());
    }

    public SimpleBooleanProperty itemUsedProperty() {
        return itemUsed;
    }


    public boolean isItemUsed() {
        return itemUsed.get();
    }

    public void setItemUsed(boolean used) {
        itemUsed.set(used);
    }
    @Override
    public ObservableList<Gamepiece> getOwnGamepieces() {
        return ownGamepieces;
    }

    public SimpleBooleanProperty isEngaged() {
        return engaged;
    }

    public void setTurn(boolean turn) {
        this.turn.set(turn);
    }

    @Override
    public boolean getTurn() {
        return this.turn.get();
    }

    public SimpleBooleanProperty turnProperty() {
        return turn;
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
