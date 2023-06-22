package UI.Elements.Start;

import Business.GameLogic.Game;
import UI.Presentation.MonsterApplication;
import UI.Scenes;
import UI.ViewController;

public class StartViewController extends ViewController<MonsterApplication> {
    private final Game game;
    private final StartView view;
    public StartViewController(MonsterApplication application, Game game) {
        super(application);
        rootView = new StartView();
        view = (StartView) rootView;
        this.game = game;
        initialize();

    }

    @Override
    public void initialize() {
        view.startNewGame.setOnAction(e -> application.switchScene(Scenes.COMBINED_VIEW));
        //view.loadGame.setOnAction(e -> game.loadGame(""));
    }
}
