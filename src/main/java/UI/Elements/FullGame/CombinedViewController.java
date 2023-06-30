package UI.Elements.FullGame;

import Business.GameLogic.Game;
import UI.Elements.Competition.chooseCompetition.chooseCompetitionViewController;
import UI.Elements.Game.GameViewController;
import UI.Elements.GameField.GameFieldViewController;
import UI.Presentation.MonsterApplication;
import UI.Scenes;
import UI.ViewController;
import javafx.animation.Interpolator;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

import java.io.IOException;

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
        initialize();
    }

    @Override
    public void initialize() {
        turnSwitch(false);
        view.setBottom(gameViewController.getRootView());
        view.setCenter(gameFieldViewController.getRootView());
        view.getBottom().setStyle("-fx-alignment: center;");
        view.nextTurn.setOnAction(e ->
                turnSwitch(true)
        );
        view.saveGame.setOnAction(e -> {
            try {
                game.saveGame();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        view.exitGame.setOnAction(e -> {
            application.switchScene(Scenes.START_VIEW);
            application.getScenes().remove(Scenes.COMBINED_VIEW);
        });
        game.getCurrentPlayer().isEngaged().addListener((observable, oldValue, newValue) -> {
            view.alertPane.setAlertLabelText("Pieces are engaged!");
            animation();
            if (newValue) {

                chooseCompetitionViewController = new chooseCompetitionViewController(application, game, gameFieldViewController);

                view.setCenter(chooseCompetitionViewController.getRootView());
                view.nextTurn.setDisable(true);
            }
            if (!newValue) {
                view.nextTurn.setDisable(false);
            }
        });
        game.getNextPlayer().isEngaged().addListener((observable, oldValue, newValue) -> {
            view.alertPane.setAlertLabelText("Pieces are engaged!");
            animation();
            if (newValue) {

                chooseCompetitionViewController = new chooseCompetitionViewController(application, game, gameFieldViewController);

                view.setCenter(chooseCompetitionViewController.getRootView());
                view.nextTurn.setDisable(true);
            }
            if (!newValue) {
                view.nextTurn.setDisable(false);
            }
        });
        game.getCurrentPlayer().turnProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                view.playerTurn.setText(game.getCurrentPlayer().getName() + " can't move anymore");
                view.alertPane.setAlertLabelText(game.getCurrentPlayer().getName() + " can't move anymore use item or end turn");
                animation();
            }
        });
        game.getNextPlayer().turnProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                view.playerTurn.setText(game.getCurrentPlayer().getName() + " can't move anymore");
                view.alertPane.setAlertLabelText(game.getCurrentPlayer().getName() + " can't move anymore use item or end turn");
                animation();
            }
        });
    }

    void turnSwitch(boolean swap) {
        if (swap) game.switchPlayersTurn();
        String color = game.getCurrentPlayer().equals(game.getGamefield().getPlayer1()) ? "(White)" : "(Black)";
        view.playerTurn.setText("Currently it's " + game.getCurrentPlayer().getName() + "'s turn " + color);
    }

    private void animation() {
        SequentialTransition anim = new SequentialTransition();
        TranslateTransition transitionAnim = new TranslateTransition();
        transitionAnim.setNode(view.topMenu);
        transitionAnim.setToY(0);
        transitionAnim.setDuration(Duration.millis(250));
        transitionAnim.setInterpolator(Interpolator.EASE_OUT);

        TranslateTransition transitionAnimBack = new TranslateTransition();
        transitionAnimBack.setNode(view.topMenu);
        transitionAnimBack.setToY(-view.alertPane.getPrefHeight() - 1);
        transitionAnimBack.setDelay(Duration.seconds(10));
        transitionAnimBack.setDuration(Duration.millis(200));
        transitionAnimBack.setInterpolator(Interpolator.EASE_IN);
        anim.getChildren().addAll(transitionAnim, transitionAnimBack);

        anim.playFromStart();
    }

}

