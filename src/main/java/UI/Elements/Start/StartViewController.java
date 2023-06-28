package UI.Elements.Start;

import Business.GameLogic.Game;
import UI.Elements.FullGame.CombinedViewController;
import UI.Presentation.MonsterApplication;
import UI.Scenes;
import UI.ViewController;

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
        //view.loadGame.setOnAction(e -> game.loadGame(""));
    }
}
