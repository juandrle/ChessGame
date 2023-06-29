package Business.GameLogic;

import Business.Gamepiece.Gamepiece;
import Business.Gamepiece.Pawn;
import Business.Gamepiece.Queen;
import Business.Gamepiece.Tower;
import Business.Item.Item;
import Business.Item.StatusChange.Manipulator.RankManipulator;
import Business.Item.StatusChange.Manipulator.TimeManipulator;
import Business.Item.StatusChange.Shield;
import Business.Item.StatusChange.StatusChangeImpl;
import Business.Item.Trap.MotionTrap;
import Business.Item.Trap.TeleportationTrap;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;

public class GameImpl implements Game{

    private int turnCount = 0;
    private Gamefield gamefield;
    private Player currPlayer = null;
    private Player nextPlayer = null;

    private Map<Gamepiece,Integer> effectedGamepieces;
    public GameImpl(){
        effectedGamepieces = new HashMap<>();
    }
    public void newGame(){
        this.gamefield = new GamefieldImpl(true,this);
        switchPlayersTurn();
    }
    public void switchPlayersTurn(){
        turnCount++;
        if (this.currPlayer == null || this.currPlayer.equals(this.gamefield.getPlayer2())) {
            this.currPlayer = this.gamefield.getPlayer1();
            this.nextPlayer = this.gamefield.getPlayer2();
            this.gamefield.getPlayer1().setTurn(true);
        }
        else {
            this.currPlayer = this.gamefield.getPlayer2();
            this.nextPlayer = this.gamefield.getPlayer1();
            this.gamefield.getPlayer2().setTurn(true);
        }
        if (effectedGamepieces != null)checkEffectedGamepieces();
    }

    public Map<Gamepiece, Integer> getEffectedGamepieces() {
        return effectedGamepieces;
    }

    public void checkEffectedGamepieces(){
        if(!effectedGamepieces.isEmpty()){
            for(Gamepiece key : effectedGamepieces.keySet()){
               if(effectedGamepieces.get(key) == turnCount){
                   key.setMoveable(true);
                    effectedGamepieces.remove(key);
               }
            }
        }
    }

    public int getTurnCount() {
        return turnCount;
    }

    public Game startGame(){
        return new GameImpl();
    }
    @Override
    public Game loadGame(String filepath) throws IOException {
        //load a file from path
        File file = new File(filepath);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = null;

        String typ = "";
        int row = 0, column = 0;
        Item inventory = null;
        boolean isDropable = false;
        boolean pl1 = false;
        boolean pl2 = false;
        Gamefield loadedGamefield = new GamefieldImpl(false,this);



        while (!(line = br.readLine()).equals("Items")) {
            String tag = line.split(":")[0];
            String value = line.split(":")[1].trim();
            Gamepiece fig = null;
            inventory = null;
            row = 0;
            column = 0;

            switch(tag){
                case "match between":
                    continue;
                case "Turn":
                    turnCount = Integer.parseInt(value);
                    break;
                case "Player1":
                    pl1 = true;
                    loadedGamefield.getPlayer1().setName(value);
                    break;
                case "Player2":
                    pl1 = false;
                    pl2 = true;
                    loadedGamefield.getPlayer2().setName(value);
                    break;
                case "Pawn":
                    fig = new Pawn();
                    break;
                case "Tower":
                    fig = new Tower();
                    break;
                case "Queen":
                    fig = new Queen();
                    break;
                case "row":
                    row = Integer.parseInt(value);
                    break;
                case "column":
                    column = Integer.parseInt(value);
                    break;
                case "inventory":
                    if(!value.equals("null")) {
                        if (value.equals("Shield")) inventory = new Shield("Shield");
                        if (value.equals("TimeManipulator")) inventory = new TimeManipulator("TimeManipulator");
                    }
                    else inventory = null;
                    fig.setInventory(inventory);
                    break;
                case "isMoveable":
                    if(value.equals("false")) fig.setMoveable(false);
                        else fig.setMoveable(true);
                        break;
                case "endGamepiece":
                    if(pl1) {
                        loadedGamefield.getPlayer1().addNewGamepiece(fig);
                        fig.setPosition(loadedGamefield.getField(row, column));
                    }
                    if(pl2) {
                        loadedGamefield.getPlayer2().addNewGamepiece(fig);
                        fig.setPosition(loadedGamefield.getField(row, column));
                    }
                    break;
            }
        }
        while(!(line = br.readLine()).equals("EverythingOk")){

            String tag = line.split(":")[0];
            String value = line.split(":")[1].trim();
            Item it = null;
            isDropable = false;

            switch(tag){
                case "TimeManipulator":
                    it = new TimeManipulator("TimeManipulator");
                    break;
                case "RankManipulator":
                    it = new RankManipulator("RankManipulator",this);
                    break;
                case "Shield":
                    it = new Shield("Shield");
                    break;
                case "MotionTrap":
                    it = new MotionTrap("MotionTrap");
                    break;
                case "row":
                    row = Integer.parseInt(value);
                    break;
                case "column":
                    column = Integer.parseInt(value);
                    break;
                case "iDropable":
                    if(value.equals("true")) it.setIsDropable(true);
                    else it.setIsDropable(false);
                    break;
                case "endItem":
                    it.setPosition(loadedGamefield.getField(row,column));
                    row = 0;
                    column = 0;
                    break;
        }

        while(br.readLine() != null)
            if(line.equals("EverythingOk")) return this;

        }
        return null;
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

            myWriter.write("match between:" +p1.getName() + ":" + p2.getName() + ":" + date.toString() + "\n");
            myWriter.write("Turn:" + turnCount);
            myWriter.append("Player1:"+p1.getName() + "\n");
            for(Gamepiece p: p1.getOwnGamepieces()){
                if(p instanceof Pawn)myWriter.append("Pawn:\n");
                else if(p instanceof Tower)myWriter.append("Tower:\n");
                else myWriter.append("Queen:\n");
                myWriter.append("row:" + p.getPosition().getRow()+"\n");
                myWriter.append("column:" + p.getPosition().getColumn()+"\n");
                // switch case für alle items
                if (p.getInventory() != null){
                    if(p.getInventory() instanceof TimeManipulator) myWriter.append("inventory:TimeManipulator\n");
                    else if(p.getInventory() instanceof RankManipulator) myWriter.append("inventory:RankManipulator\n");
                    else if(p.getInventory() instanceof Shield) myWriter.append("inventory:Shield\n");
                    else if(p.getInventory() instanceof MotionTrap) myWriter.append("inventory:MotionTrap\n");
                    else if(p.getInventory() instanceof TeleportationTrap) myWriter.append("inventory:TeleportationTrap\n");
                    else if(p.getInventory() instanceof StatusChangeImpl) myWriter.append("inventory:StatusChangeImpl\n");
                }
                else myWriter.append("inventory:null\n");
                if(p.isMoveable()) myWriter.append("isMoveable:true\n");
                else myWriter.append("isMoveable:false\n");
                myWriter.append("endGamepiece\n");
            }

            myWriter.append("Player2:"+p2.getName() + "\n");
            for(Gamepiece p: p2.getOwnGamepieces()){
                if(p instanceof Pawn)myWriter.append("Pawn\n");
                else if(p instanceof Tower)myWriter.append("Tower\n");
                else myWriter.append("Queen\n");
                myWriter.append("row:" + p.getPosition().getRow() + "\n");
                myWriter.append("column:" + p.getPosition().getColumn() + "\n");
                if (p.getInventory() != null) myWriter.append("inventory:" + p.getInventory().toString().split("\\.")[3] + "\n");
                else myWriter.append("inventory:null\n");
                if(p.isMoveable()) myWriter.append("isMoveable:true\n");
                else myWriter.append("isMoveable:false\n");
                myWriter.append("endGamepiece\n");
            }

            myWriter.append("Items\n");
            for(Field f: getGamefield().getFields()){
                if(f.getItem() != null){
                    if(f.getItem() instanceof TimeManipulator) myWriter.append("TimeManipulator\n");
                    else if(f.getItem() instanceof RankManipulator) myWriter.append("RankManipulator\n");
                    else if(f.getItem() instanceof Shield) myWriter.append("Shield\n");
                    else if(f.getItem() instanceof MotionTrap) myWriter.append("MotionTrap\n");
                    else if(f.getItem() instanceof TeleportationTrap) myWriter.append("TeleportationTrap\n");
                    else if(f.getItem() instanceof StatusChangeImpl) myWriter.append("StatusChangeImpl\n");
                    myWriter.append("row:" + f.getRow()+ "\n");
                    myWriter.append("column:" + f.getColumn() + "\n");
                    if(f.getItem().isDropable()) myWriter.append("isDropable:true \n");
                    else myWriter.append("isDropable:false \n");
                    myWriter.append("endItem \n");
                }
            }
            myWriter.append("EverythingOk");
            myWriter.close();
        }
        catch (IOException ioe){
            System.out.println("hey leider ist was schief gelaufen");
        }


    }

    public void exitGame(){

    }

    public void runGame(){

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
