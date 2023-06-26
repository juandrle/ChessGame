package Business.GameLogic;

import Business.Gamepiece.Gamepiece;
import Business.Gamepiece.Pawn;
import Business.Gamepiece.Queen;
import Business.Gamepiece.Tower;
import Business.Item.Item;
import Business.Item.StatusChange.Manipulator.TimeManipulator;
import Business.Item.StatusChange.Shield;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;

public class GameImpl implements Game{

    private int turnCount = 0;
    private final Gamefield gamefield;
    private Player currPlayer = null;
    private Player nextPlayer = null;

    private Map<Gamepiece,Integer> effectedGamepieces;
    public GameImpl(){
        this.gamefield = new GamefieldImpl();
        switchPlayersTurn();
        effectedGamepieces = new HashMap<>();
    }

    public void switchPlayersTurn(){
        if (this.currPlayer == null || this.currPlayer.equals(this.gamefield.getPlayer2())) {
            this.currPlayer = this.gamefield.getPlayer1();
            this.nextPlayer = this.gamefield.getPlayer2();
            this.gamefield.getPlayer1().setTurn(true);
            turnCount +=1;
        }
        else {
            this.currPlayer = this.gamefield.getPlayer2();
            this.nextPlayer = this.gamefield.getPlayer1();
            this.gamefield.getPlayer2().setTurn(true);
            turnCount +=1;
        }
        checkEffectedGamepieces();
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

    public Game startGame(){
        return new GameImpl();
    }
    @Override
    public Game loadGame(File file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = null;

        String typ = "";
        int row = 0, column = 0;
        Item inventory = null;
        boolean isMoveable = false;
        boolean isDropable = false;
        boolean pl1 = false;
        boolean pl2 = false;
        boolean itemlist = false;



        while ((line = br.readLine()) != null) {
            if(line.split(":")[0].equals("match between")) continue;
            else if(line.split(":")[0].trim().equals("Player1")){
                pl1 = true;
                getGamefield().getPlayer1().setName(line.split(":")[1].trim());
            }
            else if(pl1) {
                if (line.split(":")[0].trim().equals("Pawn")) typ = "Pawn";
                else if (line.split(":")[0].trim().equals("Tower")) typ = "Tower";
                else if (line.split(":")[0].trim().equals("Queen")) typ = "Queen";

                else if (line.split(":")[0].trim().equals("row")) row = Integer.parseInt(line.split(":")[1].trim());
                else if (line.split(":")[0].trim().equals("column"))
                    column = Integer.parseInt(line.split(":")[1].trim());
                else if (line.split(":")[0].trim().equals("inventory") && !line.split(":")[1].trim().equals("null")) {
                    if (line.split(":")[0].trim().equals("Shield")) {
                        inventory = new Shield("Shield");
                    } else if (line.split(":")[0].trim().equals("TimeManipulator")) {
                        inventory = new TimeManipulator("TimeManipulator");
                    }
                } else if (line.split(":")[0].equals("isMoveable") && line.split(":")[1].trim().equals("true"))
                    isMoveable = true;
                else if (line.split(":")[0].equals("isMoveable") && line.split(":")[1].trim().equals("false"))
                    isMoveable = false;
                else if (line.equals("endGamepiece")) {
                    Gamepiece fig;
                    if (typ.equals("Pawn")) fig = new Pawn();
                    if (typ.equals("Tower")) fig = new Tower();
                    else fig = new Queen();

                    fig.setMoveable(isMoveable);
                    fig.setPosition(gamefield.getField(row, column));
                    fig.setInventory(inventory);

                    gamefield.getPlayer1().addNewGamepiece(fig);
                    typ = "";
                    isMoveable = false;
                    inventory = null;
                    row = 0;
                    column = 0;
                }
            }
                else if(line.split(":")[0].trim().equals("Player2")){
                    pl1 = false;
                    pl2 = true;
                    getGamefield().getPlayer2().setName(line.split(":")[1].trim());
                }
                else if(pl2){
                    if(line.split(":")[0].trim().equals("Pawn"))typ = "Pawn";
                    else if(line.split(":")[0].trim().equals("Tower"))typ = "Tower";
                    else if(line.split(":")[0].trim().equals("Queen")) typ = "Queen";

                    else if(line.split(":")[0].trim().equals("row"))row = Integer.parseInt(line.split(":")[1].trim());
                    else if(line.split(":")[0].trim().equals("column"))column = Integer.parseInt(line.split(":")[1].trim());
                    else if(line.split(":")[0].trim().equals("inventory") && !line.split(":")[1].trim().equals("null") ){
                        if(line.split(":")[0].trim().equals("Shield")){
                            inventory = new Shield("Shield");
                        }
                        else if(line.split(":")[0].trim().equals("TimeManipulator")){
                            inventory = new TimeManipulator("TimeManipulator");
                        }
                    }
                    else if(line.split(":")[0].equals("isMoveable") && line.split(":")[1].trim().equals("true"))isMoveable = true;
                    else if(line.split(":")[0].equals("isMoveable") && line.split(":")[1].trim().equals("false")) isMoveable = false;
                    else if(line.equals("endGamepiece")){
                        Gamepiece fig;
                        if(typ.equals("Pawn"))fig = new Pawn();
                        if(typ.equals("Tower")) fig = new Tower();
                        else fig = new Queen();

                        fig.setMoveable(isMoveable);
                        fig.setPosition(gamefield.getField(row,column));
                        fig.setInventory(inventory);

                        gamefield.getPlayer2().addNewGamepiece(fig);
                        typ = "";
                        isMoveable = false;
                        inventory = null;
                        row = 0;
                        column = 0;
                    }
                    else if(line.split(":")[0].trim().equals("Items")){
                        pl2 = false;
                        itemlist = true;
                    }
                // dann items genau wie bei player erzeugen und plazieren
            }
                else if(itemlist){
                    if(line.trim().equals("TimeManipulator"))typ = "TimeManipulator";
                    else if(line.trim().equals("RankManipulator"))typ = "RankManipulator";
                    else if(line.trim().equals("Shield"))typ = "Shield";
                    else if(line.trim().equals("MotionTrap"))typ = "MotionTrap";
                    else if(line.trim().equals("TeleportationTrap"))typ = "TeleportationTrap";
                    else if(line.trim().equals("StatusChangeImpl"))typ = "StatusChangeImpl";
                    else if(line.split(":")[0].trim().equals("row"))row = Integer.parseInt(line.split(":")[1].trim());
                    else if(line.split(":")[0].trim().equals("column"))column = Integer.parseInt(line.split(":")[1].trim());
                    else if(line.split(":")[0].equals("iDropable") && line.split(":")[1].trim().equals("true"))isDropable = true;
                    else if(line.split(":")[0].equals("iDropable") && line.split(":")[1].trim().equals("false")) isDropable = false;
                    else if(line.equals("endItem")){
                        Item it;
                        if(typ.equals("TimeManipultor"))it = new TimeManipulator("TimeManipulator");
                        if(typ.equals("RankManipultor"))it = new TimeManipulator("RankManipultor");
                        if(typ.equals("Shield"))it = new TimeManipulator("Shield");
                        if(typ.equals("MotionTrap"))it = new TimeManipulator("MotionTrap");
                        if(typ.equals("TeleportationTrap"))it = new TimeManipulator("TeleportationTrap");
                        else it = new TimeManipulator("StatusChange");

                        it.setIsDropable(isDropable);
                        it.setPosition(gamefield.getField(row,column));

                        typ = "";
                        isDropable = false;
                        row = 0;
                        column = 0;
                    }
                    if(line.equals("EverythingOk")) return this;
                }

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

            myWriter.write("match between:" +p1 + ":" + p2 + ":" + date.toString() + "\n");
            myWriter.append("Player1:"+p1 + "\n");
            for(Gamepiece p: p1.getOwnGamepieces()){
                if(p instanceof Pawn)myWriter.append("Pawn:\n");
                else if(p instanceof Tower)myWriter.append("Tower:\n");
                else myWriter.append("Queen:\n");
                myWriter.append("row:" + p.getPosition().getRow()+"\n");
                myWriter.append("column:" + p.getPosition().getColumn()+"\n");
                if (p.getInventory() != null) myWriter.append("inventory:" + p.getInventory().toString() + "\n");
                else myWriter.append("inventory:null\n");
                if(p.isMoveable()) myWriter.append("isMoveable:true\n");
                else myWriter.append("isMoveable:false\n");
                myWriter.append("endGamepiece\n");
            }

            myWriter.append("Player2:"+p2 + "\n");
            for(Gamepiece p: p2.getOwnGamepieces()){
                if(p instanceof Pawn)myWriter.append("Pawn\n");
                else if(p instanceof Tower)myWriter.append("Tower\n");
                else myWriter.append("Queen\n");
                myWriter.append("row:" + p.getPosition().getRow() + "\n");
                myWriter.append("column:" + p.getPosition().getColumn() + "\n");
                if (p.getInventory() != null) myWriter.append("inventory:" + p.getInventory().toString() + "\n");
                else myWriter.append("inventory:null\n");
                if(p.isMoveable()) myWriter.append("isMoveable:true\n");
                else myWriter.append("isMoveable:false\n");
                myWriter.append("endGamepiece\n");
            }

            myWriter.append("Items");
            for(Field f: getGamefield().getFields()){
                if(f.getItem() != null){
                    if(f.getItem().toString().equals("TimeManipultor")) myWriter.append("TimeManipultor\n");
                    else if(f.getItem().toString().equals("RankManipulator")) myWriter.append("RankManipulator\n");
                    else if(f.getItem().toString().equals("Shield")) myWriter.append("Shield\n");
                    else if(f.getItem().toString().equals("MotionTrap")) myWriter.append("MotionTrap\n");
                    else if(f.getItem().toString().equals("TeleportationTrap")) myWriter.append("TeleportationTrap\n");
                    else if(f.getItem().toString().equals("StatusChangeImpl")) myWriter.append("StatusChangeImpl\n");
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
