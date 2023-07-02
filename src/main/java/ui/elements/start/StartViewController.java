package ui.elements.start;

import business.gameLogic.Game;
import business.gameLogic.Player;
import ui.elements.fullGame.CombinedViewController;
import ui.presentation.MonsterApplication;
import ui.Scenes;
import ui.ViewController;

import java.io.File;
import java.io.IOException;

public class StartViewController extends ViewController<MonsterApplication> {
    private final Game game;
    private final StartView view;
    private final Player player;
    CombinedViewController combinedViewController;
    public StartViewController(MonsterApplication application, Game game, Player player) {
        super(application);
        rootView = new StartView();
        this.game = game;
        view = (StartView) rootView;
        this.player = player;
        initialize();

    }

    @Override
    public void initialize() {
        view.startNewGame.setOnAction(e -> {
            game.newGame();
            combinedViewController = new CombinedViewController(application, game);
            application.getScenes().put(Scenes.COMBINED_VIEW, combinedViewController.getRootView());
            application.switchScene(Scenes.COMBINED_VIEW);
            view.header.setText("Welcome to our Monsterappdemo!");
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
            view.header.setText("Welcome to our Monsterappdemo!");
        });

        if(player != null) {
            if (player.getOwnGamepieces().isEmpty()) {
                view.header.setText(player.getName() + " Wins!");
            }
        }
    }
}
