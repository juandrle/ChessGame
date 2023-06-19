package UI.Elements.Start;

import Business.GameLogic.Game;
import UI.Elements.Game.GameView;
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
        view.demoGameField.setOnAction(e -> application.switchScene(Scenes.GAMEFIELD_VIEW));
        view.demoCalcGame.setOnAction(e -> application.switchScene(Scenes.CALCULATIONGAME_VIEW));
        view.demoReacGame.setOnAction(e -> application.switchScene(Scenes.CLICKEVENTGAME_VIEW));
        view.demoGame.setOnAction(e -> application.switchScene(Scenes.GAME_VIEW));
    }
}
