package UI.Elements.FullGame;

import Business.GameLogic.Game;
import UI.Elements.Competition.chooseCompetition.chooseCompetitionViewController;
import UI.Elements.Game.GameViewController;
import UI.Elements.GameField.GameFieldView;
import UI.Elements.GameField.GameFieldViewController;
import UI.Presentation.MonsterApplication;
import UI.Scenes;
import UI.ViewController;

public class CombinedViewController extends ViewController<MonsterApplication> {
    Game game;
    CombinedView view;
    private GameFieldViewController gameFieldViewController;
    private GameViewController gameViewController;
    private chooseCompetitionViewController chooseCompetitionViewController;

    public CombinedViewController(MonsterApplication application, Game game) {
        super(application);
        this.game = game;
        rootView = new CombinedView();
        view = (CombinedView) rootView;
        gameFieldViewController = new GameFieldViewController(application, game);
        gameViewController = new GameViewController(application, game);
        chooseCompetitionViewController = new chooseCompetitionViewController(application, game, gameFieldViewController);
        initialize();
    }

    @Override
    public void initialize() {
        turnSwitch(false);
        view.setBottom(gameViewController.getRootView());
        view.setCenter(gameFieldViewController.getRootView());
        view.getBottom().setStyle("-fx-alignment: center;");
        view.nextTurn.setOnAction(e -> turnSwitch(true));
        //view.saveGame.setOnAction(e -> game.saveGame());
        view.loadGame.setOnAction(e -> game.loadGame());
        view.exitGame.setOnAction(e -> application.switchScene(Scenes.START_VIEW));
        game.getCurrentPlayer().isEngaged().addListener((observable, oldValue, newValue) -> {
            System.out.println(newValue);
            if (newValue) {
                view.setCenter(chooseCompetitionViewController.getRootView());
                view.nextTurn.setDisable(true);
            }
            if (!newValue) {
                gameFieldViewController.gamefieldInitializer();
                view.nextTurn.setDisable(false);
            }
        });
        game.getNextPlayer().isEngaged().addListener((observable, oldValue, newValue) -> {
            System.out.println(newValue);
            if (newValue) {
                view.setCenter(chooseCompetitionViewController.getRootView());
                view.nextTurn.setDisable(true);
            }
            if (!newValue) {
                gameFieldViewController.gamefieldInitializer();
                view.nextTurn.setDisable(false);
            }
        });
    }

    void turnSwitch(boolean swap) {
        if (swap) game.switchPlayersTurn();
        String color = game.getCurrentPlayer().equals(game.getGamefield().getPlayer1()) ? "(White)" : "(Black)";
        view.playerTurn.setText("Currently it's " + game.getCurrentPlayer().getName() + "'s turn " + color);
    }

}

