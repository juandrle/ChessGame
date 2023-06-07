package Business.GameLogic;

import Business.Gamepiece.Gamepiece;
import Business.Item.Item;

import java.util.ArrayList;
import java.util.List;

public class GamefieldImpl implements Gamefield{
    int maxItemamount;
    List<Field> fields;
    Player player1;
    Player player2;
    GamefieldImpl(){
        fields = new ArrayList<>();
        player1 = new PlayerImpl("susi");
        player2 = new PlayerImpl("busi");

    }

    public void createGamefield(){
        Gamepiece gamepiece;
        Item item;
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                switch (row) {
                    case 3:
                        if (column == 0)
                        break;
                    case 4:
                        break;
                }
                fields.add(new FieldImpl(row, column, gamepiece, item));
            }
        }
    }

    public void placeItems(){

    }

    public void setGamepieces(){
        
    }
}
