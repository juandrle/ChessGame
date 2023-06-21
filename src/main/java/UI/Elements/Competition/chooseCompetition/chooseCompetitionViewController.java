package UI.Elements.Competition.chooseCompetition;

import Business.Competition.CalculatorGame;
import Business.Competition.ReactionGame;
import Business.GameLogic.Game;
import UI.Elements.Competition.CalculationGame.CalculationGameView;
import UI.Elements.Competition.CalculationGame.CalculationGameViewController;
import UI.Elements.Competition.ClickEventGame.ReactionGameView;
import UI.Elements.Competition.ClickEventGame.ReactionGameViewController;
import UI.Elements.FullGame.CombinedView;
import UI.Presentation.MonsterApplication;
import UI.ViewController;
import javafx.scene.layout.Pane;

public class chooseCompetitionViewController extends ViewController<MonsterApplication> {
    chooseCompetitionView view;
    CalculationGameViewController calculationGameViewController;
    ReactionGameViewController reactionGameViewController;
    Game game;
    public chooseCompetitionViewController(MonsterApplication application, Game game) {
        super(application);
        this.game = game;
        rootView = new chooseCompetitionView();
        view = (chooseCompetitionView) rootView;
        initialize();

    }

    @Override
    public void initialize() {
        view.clickGame.setOnAction(e -> {
            reactionGameViewController = new ReactionGameViewController(new ReactionGame(60), application, game);

            // Swap the rootView with the new view
            //setRootView(reactionGameViewController.getRootView());
            CombinedView parent =(CombinedView)view.getParent();
                    parent.setCenter(reactionGameViewController.getRootView());

        });
        view.calcGame.setOnAction(e -> {
            calculationGameViewController = new CalculationGameViewController(new CalculatorGame(60), application, game);
            //setRootView(calculationGameViewController.getRootView());
            CombinedView parent = (CombinedView) view.getParent();
            parent.setCenter(calculationGameViewController.getRootView());
        });
    }
}
