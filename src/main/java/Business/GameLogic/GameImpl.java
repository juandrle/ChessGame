package Business.GameLogic;

import Business.Gamepiece.Gamepiece;
import Business.Gamepiece.Pawn;
import Business.Gamepiece.Tower;
import Business.Item.Item;
import Business.Item.StatusChange.Manipulator.TimeManipulator;
import Business.Item.StatusChange.Shield;

import java.io.*;
import java.util.Date;
import java.util.Timer;

public class GameImpl implements Game{

    private int turnCount = 0;
    private final Gamefield gamefield;
    private Player currPlayer = null;
    private Player nextPlayer = null;
    public GameImpl(){
        this.gamefield = new GamefieldImpl();
        switchPlayersTurn();
    }

    public void switchPlayersTurn(){
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
    }

    public Game startGame(){
        return new GameImpl();
    }
    @Override
    public Game loadGame(File file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = null;

        String typ;
        int row, column;
        Item inventory;
        boolean isMoveable;
        boolean pl1 = false;
        boolean pl2 = false;

        while ((line = br.readLine()) != null) {
            if(line.split(":")[0].equals("match between")) continue;
            else if(line.split(":")[0].trim().equals("Player1")){
                pl1 = true;
                getGamefield().getPlayer1().setName(line.split(":")[1].trim());
                continue;
            }
            else if(pl1){
                if(line.split(":")[0].trim().equals("Pawn")){
                    typ = "Pawn";
                    continue;
                }
                else if(line.split(":")[0].trim().equals("Tower")){
                    typ = "Tower";
                    continue;
                }
                else if(line.split(":")[0].trim().equals("Queen")){
                    typ = "Queen";
                    continue;
                }
                if(line.split(":")[0].trim().equals("row"))row = Integer.parseInt(line.split(":")[1].trim());
                if(line.split(":")[0].trim().equals("column"))column = Integer.parseInt(line.split(":")[1].trim());
                if(line.split(":")[0].trim().equals("inventory") && !line.split(":")[1].trim().equals("null") ){
                    if(line.split(":")[0].trim().equals("Shield")){
                        inventory = new Shield("Shield");
                    }
                    else if(line.split(":")[0].trim().equals("TimeManipulator")){
                        inventory = new TimeManipulator("TimeManipulator");
                    }
                }
                //pruefe item und erzeuge es dann moveable dann figur erzeugen bis alle abgearbeitet
                //dann für p2
                // dann items genau wie bei player erzeugen und plazieren
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
                    else if(f.getItem().toString().equals("Shield")) myWriter.append("Shield\n");
                    myWriter.append("row:" + f.getRow()+ "\n");
                    myWriter.append("column:" + f.getColumn() + "\n");
                    if(f.getItem().isDropable()) myWriter.append("isDropable:true \n");
                    else myWriter.append("isDropable:false \n");
                    myWriter.append("endItem \n");
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

    public Player getNextPlayer() {
        return this.nextPlayer;
    }
}
