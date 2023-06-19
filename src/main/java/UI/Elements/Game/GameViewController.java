package UI.Elements.Game;

import Business.GameLogic.Game;
import Business.Gamepiece.Gamepiece;
import UI.Presentation.MonsterApplication;
import UI.ViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class GameViewController extends ViewController<MonsterApplication> {
    private final Game game;
    private final GameView view;
    public ObservableList<Gamepiece> player1Pieces;
    private ObservableList<Gamepiece> player2Pieces;
    public GameViewController(MonsterApplication application, Game game) {
        super(application);
        rootView = new GameView();
        view = (GameView) rootView;
        this.game = game;
        player1Pieces = FXCollections.observableArrayList();
        player1Pieces.addAll(game.getGamefield().getPlayer1().getOwnGamepieces());
        player2Pieces = FXCollections.observableArrayList();
        player2Pieces.addAll(game.getGamefield().getPlayer2().getOwnGamepieces());
        initialize();

    }

    @Override
    public void initialize() {
        view.player1Gamepieces.setItems(player1Pieces);
        view.player2Gamepieces.setItems(player2Pieces);

        view.player1Gamepieces.setCellFactory(e -> new ListCell<>(){

            ImageView gamepiece = new ImageView();
            {
                gamepiece.setFitWidth(40);
                gamepiece.setFitHeight(40);
                setGraphic(gamepiece);
            }

            @Override
            protected void updateItem(Gamepiece item, boolean empty) {
                super.updateItem(item, empty);
                if (!empty) {
                    switch (item.getRank()) {
                        case 0 -> gamepiece.setImage(new Image("files/pictures/gamepieces/PawnPlayer1.png"));
                        case 1 -> gamepiece.setImage(new Image("files/pictures/gamepieces/TowerPlayer1.png"));
                        case 2 -> gamepiece.setImage(new Image("files/pictures/gamepieces/QueenPlayer1.png"));
                    }
                }
            }
        });
        view.player2Gamepieces.setCellFactory(e -> new ListCell<>(){
            ImageView gamepiece = new ImageView();
            {
                gamepiece.setFitWidth(40);
                gamepiece.setFitHeight(40);
                setGraphic(gamepiece);
            }

            @Override
            protected void updateItem(Gamepiece item, boolean empty) {
                super.updateItem(item, empty);
                if (!empty) {
                    switch (item.getRank()) {
                        case 0 -> gamepiece.setImage(new Image("files/pictures/gamepieces/PawnPlayer2.png"));
                        case 1 -> gamepiece.setImage(new Image("files/pictures/gamepieces/TowerPlayer2.png"));
                        case 2 -> gamepiece.setImage(new Image("files/pictures/gamepieces/QueenPlayer2.png"));
                    }
                }
            }
        });
    }
}
