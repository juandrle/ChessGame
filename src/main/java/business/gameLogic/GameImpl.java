package business.gameLogic;

import business.gamepiece.Gamepiece;
import business.gamepiece.Pawn;
import business.gamepiece.Queen;
import business.gamepiece.Tower;
import business.item.Item;
import business.item.statusChange.manipulator.RankManipulator;
import business.item.statusChange.manipulator.TimeManipulator;
import business.item.statusChange.Shield;
import business.item.trap.MotionTrap;
import business.item.trap.TeleportationTrap;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GameImpl implements Game {

    private int turnCount;
    private Gamefield gamefield;
    private Player currPlayer = null;
    private Player nextPlayer = null;

    private final Map<Gamepiece, Integer> effectedGamepieces;

    public GameImpl() {
        this.turnCount = 0;
        effectedGamepieces = new HashMap<>();
    }

    public void newGame() {
        this.gamefield = new GamefieldImpl(true, this);
        switchPlayersTurn();
    }

    @Override
    public void setTurnCount(int count) {
        this.turnCount = count;
    }

    public void switchPlayersTurn() {
        if (turnCount % 2 == 0) {
            this.currPlayer = this.gamefield.getPlayer1();
            this.nextPlayer = this.gamefield.getPlayer2();
            this.gamefield.getPlayer1().setTurn(true);

        } else {
            this.currPlayer = this.gamefield.getPlayer2();
            this.nextPlayer = this.gamefield.getPlayer1();
            this.gamefield.getPlayer2().setTurn(true);
        }
        checkEffectedGamepieces();
        turnCount++;
    }

    public Map<Gamepiece, Integer> getEffectedGamepieces() {
        return effectedGamepieces;
    }

    public void checkEffectedGamepieces() {
        if (!effectedGamepieces.isEmpty()) {
            for (Gamepiece key : effectedGamepieces.keySet()) {
                if (effectedGamepieces.get(key) == turnCount) {
                    key.setMoveable(true);
                    effectedGamepieces.remove(key);
                }
            }
        }
    }

    public int getTurnCount() {
        return turnCount;
    }

    public Game startGame() {
        return new GameImpl();
    }

    @Override
    public void loadGame(String filepath) throws IOException {
        //load a file from path
        File file = new File(filepath);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;

        int row = 0, column = 0;
        Item inventory = null;
        boolean pl1 = false;
        boolean pl2 = false;
        Gamepiece fig = null;
        Item it = null;
        Gamefield loadedGamefield = new GamefieldImpl(false, this);
        this.gamefield = loadedGamefield;
        switchPlayersTurn();



        while (!(line = br.readLine()).equals("Items")) {
            String tag = line.split(":")[0];
            String value = line.split(":")[1].trim();


            switch (tag) {
                case "match between" -> {
                }
                case "Turn" -> turnCount = Integer.parseInt(value);
                case "Player1" -> {
                    pl1 = true;
                    loadedGamefield.getPlayer1().setName(value);
                }
                case "Player2" -> {
                    pl1 = false;
                    pl2 = true;
                    loadedGamefield.getPlayer2().setName(value);
                }
                case "isTurn" -> {
                    if (pl1) loadedGamefield.getPlayer1().setTurn(value.equals("true"));
                    else loadedGamefield.getPlayer2().setTurn(value.equals("true"));
                }
                case "Pawn" -> {
                    fig = new Pawn();
                    fig.setRank(0);
                }
                case "Tower" -> {
                    fig = new Tower();
                    fig.setRank(1);
                }
                case "Queen" -> {
                    fig = new Queen();
                    fig.setRank(2);
                }
                case "row" -> row = Integer.parseInt(value);
                case "column" -> column = Integer.parseInt(value);
                case "inventory" -> {
                    if (!value.equals("null")) {
                        if (value.equals("Shield")) inventory = new Shield();
                        if (value.equals("TimeManipulator")) inventory = new TimeManipulator();
                    } else inventory = null;
                    Objects.requireNonNull(fig).setInventory(inventory);
                }
                case "isMoveable" -> {
                    fig.setMoveable(!value.equals("false"));
                }
                case "endGamepiece" -> {
                    if (pl1) {
                        loadedGamefield.getPlayer1().addNewGamepiece(fig);
                        Objects.requireNonNull(fig).setPosition(loadedGamefield.getField(row, column));
                    }
                    if (pl2) {
                        loadedGamefield.getPlayer2().addNewGamepiece(fig);
                        Objects.requireNonNull(fig).setPosition(loadedGamefield.getField(row, column));
                    }
                    fig = null;
                    inventory = null;
                    row = 0;
                    column = 0;
                }
            }
        }
        while (!(line = br.readLine()).equals("EverythingOk")) {

            String tag = line.split(":")[0];
            String value = line.split(":")[1].trim();


            switch (tag) {
                case "TimeManipulator" -> it = new TimeManipulator();
                case "RankManipulator" -> it = new RankManipulator(this);
                case "Shield" -> it = new Shield();
                case "MotionTrap" -> it = new MotionTrap();
                case "TeleportationTrap" -> it = new TeleportationTrap();
                case "row" -> row = Integer.parseInt(value);
                case "column" -> column = Integer.parseInt(value);
                case "iDropable" -> {
                    it.setIsDropable(value.equals("true"));
                }
                case "endItem" -> {
                    Objects.requireNonNull(it).setPosition(loadedGamefield.getField(row, column));
                    row = 0;
                    column = 0;
                }
                case "EverythingOk" -> {
                    return;
                }
            }
        }

    }

    public void saveGame() throws IOException {
        // create namefield for name des spiels oder spielernamen mit timestamp
        Player p1 = this.getGamefield().getPlayer1();
        Player p2 = this.getGamefield().getPlayer2();
        try {
            File savedGame = new File("filename.txt");
            savedGame.setWritable(true);

            Date date = new Date();

            savedGame.createNewFile();

            FileWriter myWriter = new FileWriter(savedGame);

            myWriter.write("match between:" + p1.getName() + ":" + p2.getName() + ":" + date + "\n");
            myWriter.write("Turn:" + (turnCount-1) + "\n");
            myWriter.append("Player1:").append(p1.getName()).append("\n").append("isTurn:").append(gamefield.getPlayer1().getTurn() ? "true\n" : "false\n");
            for (Gamepiece p : p1.getOwnGamepieces()) {
                if (p instanceof Pawn) myWriter.append("Pawn:_\n");
                else if (p instanceof Tower) myWriter.append("Tower:_\n");
                else myWriter.append("Queen:_\n");
                myWriter.append("row:").append(String.valueOf(p.getPosition().getRow())).append("\n");
                myWriter.append("column:").append(String.valueOf(p.getPosition().getColumn())).append("\n");
                // switch case für alle items
                if (p.getInventory() != null) {
                    if (p.getInventory() instanceof TimeManipulator) myWriter.append("inventory:TimeManipulator\n");
                    else if (p.getInventory() instanceof RankManipulator)
                        myWriter.append("inventory:RankManipulator\n");
                    else if (p.getInventory() instanceof Shield) myWriter.append("inventory:Shield\n");
                    else if (p.getInventory() instanceof MotionTrap) myWriter.append("inventory:MotionTrap\n");
                    else if (p.getInventory() instanceof TeleportationTrap)
                        myWriter.append("inventory:TeleportationTrap\n");
                } else myWriter.append("inventory:null\n");
                if (p.isMoveable()) myWriter.append("isMoveable:true\n");
                else myWriter.append("isMoveable:false\n");
                myWriter.append("endGamepiece:_\n");
            }

            myWriter.append("Player2:").append(p2.getName()).append("\n").append("isTurn:").append(gamefield.getPlayer1().getTurn() ? "true\n" : "false\n");;
            for (Gamepiece p : p2.getOwnGamepieces()) {
                if (p instanceof Pawn) myWriter.append("Pawn:_\n");
                else if (p instanceof Tower) myWriter.append("Tower:_\n");
                else myWriter.append("Queen:_\n");
                myWriter.append("row:").append(String.valueOf(p.getPosition().getRow())).append("\n");
                myWriter.append("column:").append(String.valueOf(p.getPosition().getColumn())).append("\n");
                if (p.getInventory() != null){
                    if (p.getInventory() instanceof TimeManipulator) myWriter.append("inventory:TimeManipulator\n");
                    else if (p.getInventory() instanceof RankManipulator)myWriter.append("inventory:RankManipulator\n");
                    else if (p.getInventory() instanceof Shield) myWriter.append("inventory:Shield\n");
                    else if (p.getInventory() instanceof MotionTrap) myWriter.append("inventory:MotionTrap\n");
                    else if (p.getInventory() instanceof TeleportationTrap)myWriter.append("inventory:TeleportationTrap\n");
                } else myWriter.append("inventory:null\n");
                if (p.isMoveable()) myWriter.append("isMoveable:true\n");
                else myWriter.append("isMoveable:false\n");
                myWriter.append("endGamepiece:_\n");
            }

            myWriter.append("Items\n");
            for (Field f : getGamefield().getFields()) {
                if (f.getItem() != null) {
                    if (f.getItem() instanceof TimeManipulator) myWriter.append("TimeManipulator:_\n");
                    else if (f.getItem() instanceof RankManipulator) myWriter.append("RankManipulator:_\n");
                    else if (f.getItem() instanceof Shield) myWriter.append("Shield:_\n");
                    else if (f.getItem() instanceof MotionTrap) myWriter.append("MotionTrap:_\n");
                    else if (f.getItem() instanceof TeleportationTrap) myWriter.append("TeleportationTrap:_\n");
                    myWriter.append("row:").append(String.valueOf(f.getRow())).append("\n");
                    myWriter.append("column:").append(String.valueOf(f.getColumn())).append("\n");
                    if (f.getItem().isDropable()) myWriter.append("isDropable:true \n");
                    else myWriter.append("isDropable:false \n");
                    myWriter.append("endItem:_ \n");
                }
            }
            myWriter.append("EverythingOk:_");
            myWriter.close();
        } catch (IOException ioe) {
            System.out.println("hey leider ist was schief gelaufen");
        }


    }

    public Gamefield getGamefield() {
        return this.gamefield;
    }

    @Override
    public Player getCurrentPlayer() {
        return this.currPlayer;
    }

    public Player getNextPlayer() {
        return this.nextPlayer;
    }
}
