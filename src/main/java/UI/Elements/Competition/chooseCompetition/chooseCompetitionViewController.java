package UI.Elements.Competition.chooseCompetition;

import Business.Competition.CalculatorGame;
import Business.Competition.Competition;
import Business.Competition.ReactionGame;
import Business.GameLogic.Game;
import Business.Gamepiece.Gamepiece;
import Business.Item.StatusChange.Shield;
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
    private int time;
    private final int defaultTime = 30;

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
            time = defaultTime;
            parent.playerTurn.setText(game.getCurrentPlayer().getName() + "'s Competition");
            reactionGameViewController = new ReactionGameViewController(currReactionGame = new ReactionGame(time), application, game);
            parent.setCenter(reactionGameViewController.getRootView());
            competitionStarter(currReactionGame, parent);

        });
        view.calcGame.setOnAction(e -> {
            time = defaultTime;
            CombinedView parent = (CombinedView) view.getParent();
            parent.playerTurn.setText(game.getCurrentPlayer().getName() + "'s Competition");
            calculationGameViewController = new CalculationGameViewController(currCalculatorGame = new CalculatorGame(time), application, game);
            parent.setCenter(calculationGameViewController.getRootView());
           competitionStarter(currCalculatorGame, parent);

        });
        game.getCurrentPlayer().getCurrGamepiece().propertyTimeMultiplier().addListener((observable, oldValue, newValue) -> time*= (int) newValue);
        game.getCurrentPlayer().getEnemyGamepiece().propertyTimeMultiplier().addListener((observable, oldValue, newValue) -> time*= (int) newValue);

    }

    public void competitionStarter(Competition competition, CombinedView parent) {
        Gamepiece pl1Gp = game.getCurrentPlayer().getCurrGamepiece();
        Gamepiece pl2Gp = game.getCurrentPlayer().getEnemyGamepiece();
        competition.gameEndsProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                Platform.runLater(() -> parent.setCenter(this.getRootView()));
                if (game.getCurrentPlayer().getCurrGamepiece().getPoints() == -1) {
                    competition.setPoints(game.getCurrentPlayer().getCurrGamepiece());
                }
                else {
                    competition.setPoints(game.getCurrentPlayer().getEnemyGamepiece());
                }
                if(competition.whoWin(pl1Gp,pl2Gp) == pl2Gp//player 2 won and player1 has shield
                 && pl1Gp.getInventory() instanceof Shield){
                    pl1Gp.setInventory(null);
                    pl1Gp.setPoints(-1);
                    pl2Gp.setPoints(-1);
                    initialize();
                }
                else if(competition.whoWin(pl1Gp,pl2Gp) == pl1Gp//player 1 won and player2 has shield
                        && pl2Gp.getInventory() instanceof Shield){
                    pl2Gp.setInventory(null);
                    pl1Gp.setPoints(-1);
                    pl2Gp.setPoints(-1);
                    initialize();
                }
                else if (game.getCurrentPlayer().getCurrGamepiece().getPoints() != -1
                        && game.getCurrentPlayer().getEnemyGamepiece().getPoints() != -1) {
                    Platform.runLater(() -> parent.setCenter(gameFieldViewController.getRootView()));
                    game.getCurrentPlayer().removeGamepiece(game.getNextPlayer(), competition);
                    parent.nextTurn.setDisable(false);
                }
            }
        });
    }
}
