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
import UI.Elements.Game.GameView;
import UI.Elements.GameField.GameFieldViewController;
import UI.Presentation.MonsterApplication;
import UI.ViewController;
import javafx.application.Platform;

public class ChooseCompetitionViewController extends ViewController<MonsterApplication> {
    ChooseCompetitionView view;
    CalculationGameViewController calculationGameViewController;
    ReactionGameViewController reactionGameViewController;
    Game game;
    ReactionGame currReactionGame;
    CalculatorGame currCalculatorGame;
    GameFieldViewController gameFieldViewController;
    private int time;
    private final int defaultTime = 30;

    public ChooseCompetitionViewController(MonsterApplication application, Game game, GameFieldViewController gameFieldViewController) {
        super(application);
        this.game = game;

        this.gameFieldViewController = gameFieldViewController;
        rootView = new ChooseCompetitionView();
        view = (ChooseCompetitionView) rootView;
        initialize();

    }

    @Override
    public void initialize() {
        view.getCenter().setTranslateX(-60);
        time = defaultTime;
        view.clickGame.setOnAction(e -> {
            CombinedView parent = (CombinedView) view.getParent();
            parent.playerTurn.setText(game.getCurrentPlayer().getName() + "'s Competition");
            reactionGameViewController = new ReactionGameViewController(currReactionGame = new ReactionGame(time), application, game);
            parent.setCenter(reactionGameViewController.getRootView());
            view.getCenter().setTranslateX(-60);
            competitionStarter(currReactionGame, parent);

        });
        view.calcGame.setOnAction(e -> {
            game.getCurrentPlayer().getEnemyGamepiece().propertyTimeMultiplier().addListener((observable, oldValue, newValue) -> time*= game.getCurrentPlayer().getEnemyGamepiece().getTimeMultiplier());
            calculationGameViewController = new CalculationGameViewController(currCalculatorGame = new CalculatorGame(time), application, game);
            CombinedView parent = (CombinedView) view.getParent();
            parent.playerTurn.setText(game.getCurrentPlayer().getName() + "'s Competition");
            calculationGameViewController = new CalculationGameViewController(currCalculatorGame = new CalculatorGame(time), application, game);
            parent.setCenter(calculationGameViewController.getRootView());
            view.getCenter().setTranslateX(-60);
           competitionStarter(currCalculatorGame, parent);

        });
        game.getCurrentPlayer().getCurrGamepiece().propertyTimeMultiplier().addListener((observable, oldValue, newValue) -> {
            time= time * newValue.intValue() / 10;
            System.out.println("die zeit ist" + time );
        });
        game.getCurrentPlayer().getEnemyGamepiece().propertyTimeMultiplier().addListener((observable, oldValue, newValue) -> time= time * newValue.intValue() / 10);

    }

    public void competitionStarter(Competition competition, CombinedView parent) {
        Gamepiece pl1Gp = game.getCurrentPlayer().getCurrGamepiece();
        Gamepiece pl2Gp = game.getCurrentPlayer().getEnemyGamepiece();
        competition.gameEndsProperty().addListener((observable, oldValue, newValue) -> {
            time = defaultTime;
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
                if(competition.whoWin(pl1Gp,pl2Gp) == pl2Gp//player 2 won and player1 has shield
                 && pl1Gp.getInventory() instanceof Shield){
                    pl1Gp.setInventory(null);
                    ((GameView) parent.getBottom()).selPiece1Item.setImage(null);
                    pl1Gp.setPoints(-1);
                    pl2Gp.setPoints(-1);
                    initialize();
                }
                else if(competition.whoWin(pl1Gp,pl2Gp) == pl1Gp//player 1 won and player2 has shield
                        && pl2Gp.getInventory() instanceof Shield){
                    pl2Gp.setInventory(null);
                    ((GameView) parent.getBottom()).selPiece2Item.setImage(null);
                    pl1Gp.setPoints(-1);
                    pl2Gp.setPoints(-1);
                    initialize();
                }
                else if (game.getCurrentPlayer().getCurrGamepiece().getPoints() != -1
                        && game.getCurrentPlayer().getEnemyGamepiece().getPoints() != -1) {
                    Platform.runLater(() -> parent.setCenter(gameFieldViewController.getRootView()));
                    game.getCurrentPlayer().removeGamepiece(game.getNextPlayer(), competition);
                    gameFieldViewController.clean();
                    parent.nextTurn.setDisable(false);
                }
            }
        });
    }
}
