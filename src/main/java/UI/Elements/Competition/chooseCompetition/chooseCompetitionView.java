package UI.Elements.Competition.chooseCompetition;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class chooseCompetitionView extends StackPane {
    public Button calcGame;
    public Button clickGame;

    public chooseCompetitionView(){
        Label header = new Label("Please choose your competition");
        calcGame = new Button("Play the calculation game");
        clickGame = new Button("Play the click Event");
        VBox menu = new VBox(header,calcGame,clickGame);
        menu.setAlignment(Pos.CENTER);
    }
}
