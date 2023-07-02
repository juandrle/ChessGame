package ui.elements.start;

import business.gameLogic.Game;
import ui.elements.fullGame.CombinedViewController;
import ui.presentation.MonsterApplication;
import ui.Scenes;
import ui.ViewController;

import java.io.File;
import java.io.IOException;

public class StartViewController extends ViewController<MonsterApplication> {
    private final Game game;
    private final StartView view;
    CombinedViewController combinedViewController;
    public StartViewController(MonsterApplication application, Game game) {
        super(application);
        rootView = new StartView();
        this.game = game;
        view = (StartView) rootView;
        initialize();

    }

    @Override
    public void initialize() {
        view.startNewGame.setOnAction(e -> {
            game.newGame();
            combinedViewController = new CombinedViewController(application, game);
            application.getScenes().put(Scenes.COMBINED_VIEW, combinedViewController.getRootView());
            application.switchScene(Scenes.COMBINED_VIEW);
        });
        view.loadGame.setOnAction(e -> {
            try {
                game.loadGame(String.valueOf(new File("filename.txt")));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            combinedViewController = new CombinedViewController(application, game);
            application.getScenes().put(Scenes.COMBINED_VIEW, combinedViewController.getRootView());
            application.switchScene(Scenes.COMBINED_VIEW);
        });
    }
}
