package UI.FullGame;

import Business.GameLogic.Game;
import UI.Elements.Game.GameViewController;
import UI.Elements.GameField.GameFieldViewController;
import UI.FullGame.CombinedView;
import UI.Presentation.MonsterApplication;
import UI.Scenes;
import UI.ViewController;

public class CombinedViewController extends ViewController<MonsterApplication> {
    Game game;
    CombinedView view;
    private GameFieldViewController gameFieldViewController;
    private GameViewController gameViewController;

    public CombinedViewController(MonsterApplication application, Game game) {
        super(application);
        this.game = game;
        rootView = new CombinedView();
        view = (CombinedView) rootView;
        gameFieldViewController = new GameFieldViewController(application,game);
        gameViewController = new GameViewController(application,game);
        initialize();
    }

    @Override
    public void initialize() {
        turnSwitch();
        view.setBottom(gameViewController.getRootView());
        view.setCenter(gameFieldViewController.getRootView());
        view.getBottom().setStyle("-fx-alignment: center;");
        view.nextTurn.setOnAction(e -> {
            turnSwitch();
        });
        view.exitGame.setOnAction(e -> application.switchScene(Scenes.START_VIEW));
    }
    void turnSwitch() {
        game.switchPlayersTurn();
        String color = game.getCurrentPlayer().equals(game.getGamefield().getPlayer1()) ? "(White)" : "(Black)";
        view.playerTurn.setText("Currently it's " + game.getCurrentPlayer().getName() + "'s turn " + color);
    }
}

