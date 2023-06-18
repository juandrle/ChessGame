package UI.Elements.Game;

import Business.GameLogic.Game;
import UI.Presentation.MonsterApplication;
import UI.Scenes;
import UI.ViewController;

public class GameViewController extends ViewController<MonsterApplication> {
    private final Game game;
    private final GameView view;
    public GameViewController(MonsterApplication application, Game game) {
        super(application);
        rootView = new GameView();
        view = (GameView) rootView;
        this.game = game;
        initialize();

    }

    @Override
    public void initialize() {
        view.demoGameField.setOnAction(e -> application.switchScene(Scenes.GAMEFIELD_VIEW));
        view.demoCalcGame.setOnAction(e -> application.switchScene(Scenes.CALCULATIONGAME_VIEW));
    }
}
