package UI.Game;

import Business.GameLogic.Game;
import UI.Presentation.MonsterApplication;
import UI.ViewController;

public class GameViewController extends ViewController<MonsterApplication> {
    public GameViewController(MonsterApplication application, Game game) {
        super(application);
    }

    @Override
    public void initialize() {

    }
}
