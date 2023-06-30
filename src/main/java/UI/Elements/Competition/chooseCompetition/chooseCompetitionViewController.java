package UI.Elements.Competition.chooseCompetition;

import Business.Competition.CalculatorGame;
import Business.Competition.Competition;
import Business.Competition.ReactionGame;
import Business.GameLogic.Game;
import UI.Elements.Competition.CalculationGame.CalculationGameViewController;
import UI.Elements.Competition.ClickEventGame.ReactionGameViewController;
import UI.Elements.FullGame.CombinedView;
import UI.Elements.GameField.GameFieldViewController;
import UI.Presentation.MonsterApplication;
import UI.ViewController;
import javafx.application.Platform;

public class chooseCompetitionViewController extends ViewController<MonsterApplication> {
    chooseCompetitionView view;
    CalculationGameViewController calculationGameViewController;
    ReactionGameViewController reactionGameViewController;
    Game game;
    ReactionGame currReactionGame;
    CalculatorGame currCalculatorGame;
    GameFieldViewController gameFieldViewController;
    private int time = 30;

    public chooseCompetitionViewController(MonsterApplication application, Game game, GameFieldViewController gameFieldViewController) {
        super(application);
        this.game = game;

        this.gameFieldViewController = gameFieldViewController;
        rootView = new chooseCompetitionView();
        view = (chooseCompetitionView) rootView;
        initialize();

    }

    @Override
    public void initialize() {
        view.clickGame.setOnAction(e -> {
            CombinedView parent = (CombinedView) view.getParent();
            reactionGameViewController = new ReactionGameViewController(currReactionGame = new ReactionGame(time), application, game);
            parent.setCenter(reactionGameViewController.getRootView());
            game.getCurrentPlayer().getCurrGamepiece().propertyTimeMultiplier().addListener((observable, oldValue, newValue) -> time*= game.getCurrentPlayer().getCurrGamepiece().getTimeMultiplier());
            game.getCurrentPlayer().getEnemyGamepiece().propertyTimeMultiplier().addListener((observable, oldValue, newValue) -> time*= game.getCurrentPlayer().getEnemyGamepiece().getTimeMultiplier());

            competitionStarter(currReactionGame, parent);


        });
        view.calcGame.setOnAction(e -> {
            calculationGameViewController = new CalculationGameViewController(currCalculatorGame = new CalculatorGame(time), application, game);
            CombinedView parent = (CombinedView) view.getParent();
            parent.setCenter(calculationGameViewController.getRootView());
            game.getCurrentPlayer().getCurrGamepiece().propertyTimeMultiplier().addListener((observable, oldValue, newValue) -> time*= game.getCurrentPlayer().getCurrGamepiece().getTimeMultiplier());
            game.getCurrentPlayer().getEnemyGamepiece().propertyTimeMultiplier().addListener((observable, oldValue, newValue) -> time*= game.getCurrentPlayer().getEnemyGamepiece().getTimeMultiplier());

           competitionStarter(currCalculatorGame, parent);

        });
    }

    public void competitionStarter(Competition competition, CombinedView parent) {
        competition.gameEndsProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                Platform.runLater(() -> parent.setCenter(this.getRootView()));
                if (game.getCurrentPlayer().getCurrGamepiece().getPoints() == -1) {
                    competition.setPoints(game.getCurrentPlayer().getCurrGamepiece());
                    System.out.println(time);
                }
                else {
                    competition.setPoints(game.getCurrentPlayer().getEnemyGamepiece());
                    System.out.println(time);
                }
                if (game.getCurrentPlayer().getCurrGamepiece().getPoints() != -1
                        && game.getCurrentPlayer().getEnemyGamepiece().getPoints() != -1) {
                    Platform.runLater(() -> parent.setCenter(gameFieldViewController.getRootView()));
                    game.getCurrentPlayer().removeGamepiece(game.getNextPlayer(), competition);
                    parent.nextTurn.setDisable(false);
                }
            }
        });
    }
}
