package Business.GameLogic;

import Business.Competition.Competition;
import Business.Gamepiece.Gamepiece;
import Business.Gamepiece.Pawn;
import Business.Gamepiece.Queen;
import Business.Gamepiece.Tower;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PlayerImpl implements Player {
    private String name;
    private ObservableList<Gamepiece> ownGamepieces;
    private SimpleBooleanProperty engaged;
    private boolean turn;
    private Gamepiece currGamepiece;
    private Gamepiece enemyGamepiece;
    private Field competitionField;

    public PlayerImpl(String name) {
        this.name = name;
        engaged = new SimpleBooleanProperty();
        this.ownGamepieces = FXCollections.observableArrayList();
        this.ownGamepieces.add(new Pawn());
        this.ownGamepieces.add(new Pawn());
        this.ownGamepieces.add(new Queen());
        this.ownGamepieces.add(new Tower());
        this.turn = false;
    }

    @Override
    public boolean moveGamepiece(Gamepiece gamepiece, Field field, Game game) {
        currGamepiece = chooseGamepiece(gamepiece);
        if (field.getGamepiece() != null) {
            enemyGamepiece = field.getGamepiece();
            engaged.set(true);
            currGamepiece.getPosition().setGamepiece(null);
            this.turn = false;
            competitionField = field;
            return true;
        }
        if (!this.turn) return false;
        if (currGamepiece == null) return false;
        if (!currGamepiece.isValidMove(field, game)) return false;
        currGamepiece.getPosition().setGamepiece(null);
        currGamepiece.setPosition(field);
        currGamepiece.getPosition().setGamepiece(currGamepiece);
        if (field.getItem() != null) {
            currGamepiece.setInventory(field.getItem());
            currGamepiece.getPosition().setItem(null);
        }
        this.turn = false;
        return true;
    }

    @Override
    public Gamepiece chooseGamepiece(Gamepiece gamepiece) {
        for (Gamepiece currGamepiece : ownGamepieces)
            if (currGamepiece.equals(gamepiece)) {
                return currGamepiece;
            }
        return null;
    }

    @Override
    public void useItem(Gamepiece gamepiece) {

    }

    @Override
    public void setName(String name) {

    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void removeGamepiece(Player player, Competition competition) {
        System.out.println(currGamepiece.toString() + competition.whoWin(currGamepiece, enemyGamepiece).toString());
        if (!currGamepiece.equals(competition.whoWin(currGamepiece, enemyGamepiece))) {
            this.ownGamepieces.remove(currGamepiece);
            enemyGamepiece.setPosition(competitionField);
            competitionField.setGamepiece(enemyGamepiece);
            enemyGamepiece.setPoints(-1);

        } else {
            player.setCurrGamepiece(enemyGamepiece);
            player.setEnemyGamepiece(currGamepiece);
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
        return currGamepiece;
    }

    public Gamepiece getEnemyGamepiece() {
        return enemyGamepiece;
    }

    public void setCurrGamepiece(Gamepiece gamepiece) {
        this.currGamepiece = gamepiece;
    }

    public void setEnemyGamepiece(Gamepiece gamepiece) {
        this.enemyGamepiece = gamepiece;
    }

    @Override
    public void setCompetitionField(Field field) {
        this.competitionField = field;
    }
}
