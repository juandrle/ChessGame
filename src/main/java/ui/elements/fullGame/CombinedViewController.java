package ui.elements.fullGame;

import business.gameLogic.Game;
import javafx.beans.value.ObservableValue;
import ui.elements.competition.chooseCompetition.ChooseCompetitionViewController;
import ui.elements.game.GameView;
import ui.elements.game.GameViewController;
import ui.elements.gameField.GameFieldViewController;
import ui.presentation.MonsterApplication;
import ui.Scenes;
import ui.ViewController;
import javafx.animation.Interpolator;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.util.Duration;

import java.io.IOException;

public class CombinedViewController extends ViewController<MonsterApplication> {
    final Game game;
    final CombinedView view;
    private final GameFieldViewController gameFieldViewController;
    private final GameViewController gameViewController;
    private ChooseCompetitionViewController chooseCompetitionViewController;

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
        view.setBottom(gameViewController.getRootView());
        view.setCenter(gameFieldViewController.getRootView());
        turnSwitch(false);
        view.getCenter().setTranslateX(-65);
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
            game.setTurnCount(0);
            application.switchScene(Scenes.START_VIEW);
            application.getScenes().remove(Scenes.COMBINED_VIEW);
        });
        game.getCurrentPlayer().isEngaged().addListener(this::piecesEngaged);
        game.getNextPlayer().isEngaged().addListener(this::piecesEngaged);
        game.getCurrentPlayer().turnProperty().addListener(this::turnOver);
        game.getNextPlayer().turnProperty().addListener(this::turnOver);
    }

    void turnSwitch(boolean swap) {
        if (swap) game.switchPlayersTurn();

        String color = game.getCurrentPlayer().equals(game.getGamefield().getPlayer1()) ? "(White)" : "(Black)";
        if (color.equals("(White)")) {
            ((GameView)view.getBottom()).useItemPlayer1.setDisable(false);
            ((GameView)view.getBottom()).useItemPlayer2.setDisable(true);
        } else {
            ((GameView)view.getBottom()).useItemPlayer1.setDisable(true);
            ((GameView)view.getBottom()).useItemPlayer2.setDisable(false);
        }
        view.playerTurn.setText(game.getCurrentPlayer().getName() + "'s turn " + color);
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

    private void piecesEngaged(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
        Platform.runLater(() -> {
            view.alertPane.setAlertLabelText("Pieces are engaged!");
            animation();
        });
        if (newValue) {

            chooseCompetitionViewController = new ChooseCompetitionViewController(application, game, gameFieldViewController);

            view.setCenter(chooseCompetitionViewController.getRootView());
            view.nextTurn.setDisable(true);
        }
        if (!newValue) {
            view.nextTurn.setDisable(false);
        }
    }
    private void turnOver(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
        if (!newValue) {
            view.playerTurn.setText("Please End Turn");
            view.alertPane.setAlertLabelText(game.getCurrentPlayer().getName() + " can't move anymore use item or end turn");
            animation();
        }
    }
}

