package Business.GameLogic;

import Business.Gamepiece.Gamepiece;
import Business.Gamepiece.Pawn;
import Business.Gamepiece.Queen;
import Business.Gamepiece.Tower;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.Timestamp;
import java.util.Date;

public class GameImpl implements Game{

    private int turnCount = 0;
    private final Gamefield gamefield;
    private Player currPlayer = null;
    public GameImpl(){
        this.gamefield = new GamefieldImpl();
    }

    public void setTurnCount(int tc){
        this.turnCount = tc;
    }

    public int getTurnCount(){
        return this.turnCount;
    }

    public void switchPlayersTurn(){
        if (this.currPlayer == null || this.currPlayer.equals(this.gamefield.getPlayer2()))
            this.currPlayer = this.gamefield.getPlayer1();
        else this.currPlayer = this.gamefield.getPlayer2();
    }

    public Game startGame(){
        return null;
    }

    public Game loadGame(){
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
            myWriter.write(p1 + ":" + p2 + ":" + date.toString());
            myWriter.append("Player 1");
            for(Gamepiece p: p1.getOwnGamepieces()){
                if(p instanceof Pawn)myWriter.append("Pawn");
                else if(p instanceof Tower)myWriter.append("Tower");
                else myWriter.append("Queen");
                myWriter.append("row:" + p.getPosition().getRow());
                myWriter.append("column:" + p.getPosition().getColumn());
                if (p.getInventory() != null) myWriter.append("inventory:" + p.getInventory().toString());
                else myWriter.append("inventory:null");
                if(p.isMoveable()) myWriter.append("isMoveable:true");
                else myWriter.append("isMoveable:false");
            }

            myWriter.append("Player 2");
            for(Gamepiece p: p2.getOwnGamepieces()){
                if(p instanceof Pawn)myWriter.append("Pawn");
                else if(p instanceof Tower)myWriter.append("Tower");
                else myWriter.append("Queen");
                myWriter.append("row:" + p.getPosition().getRow());
                myWriter.append("column:" + p.getPosition().getColumn());
                if (p.getInventory() != null) myWriter.append("inventory:" + p.getInventory().toString());
                else myWriter.append("inventory:null");
                if(p.isMoveable()) myWriter.append("isMoveable:true");
                else myWriter.append("isMoveable:false");
            }

            myWriter.append("Items");
            for(Field f: getGamefield().getFields()){
                if(f.getItem() != null){
                    if(f.getItem().toString().equals("RankManipultor")) myWriter.append("RankManipultor");
                    else if(f.getItem().toString().equals("TimeManipultor")) myWriter.append("TimeManipultor");
                    else if(f.getItem().toString().equals("Shield")) myWriter.append("Shield");
                    else if(f.getItem().toString().equals("StatusChangeImpl")) myWriter.append("StatusChangeImpl");
                    else if(f.getItem().toString().equals("MotionTrap")) myWriter.append("MotionTrap");
                    else if(f.getItem().toString().equals("TeleportationTrap")) myWriter.append("TeleportationTrap");
                    myWriter.append("row:" + f.getRow());
                    myWriter.append("column:" + f.getColumn());
                    if(f.getItem().isDropable()) myWriter.append("isDropable:true");
                    else myWriter.append("isDropable:false");
                }
            }
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
}
