package UI.Presentation;

import Business.Competition.CalculatorGame;
import Business.Competition.ReactionGame;
import Business.GameLogic.Game;
import Business.GameLogic.GameImpl;
import UI.Elements.Competition.CalculationGame.CalculationGameViewController;
import UI.Elements.Competition.ClickEventGame.ReactionGameViewController;
import UI.Elements.FullGame.CombinedViewController;
import UI.Scenes;
import UI.Elements.Start.StartViewController;
import UI.ViewController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.HashMap;

public class MonsterApplication extends Application {
    private Scene scene;
    private HashMap<Scenes, Pane> scenes;
    Game game;

    @Override
    public void init() throws Exception {
        scenes = new HashMap<>();
        game = new GameImpl();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            ViewController<MonsterApplication> controller;
            controller = new StartViewController(this, game,null);
            scenes.put(Scenes.START_VIEW, controller.getRootView());

            Pane root = scenes.get(Scenes.START_VIEW);
            scene = new Scene(root, 700, 800);
            scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void switchScene(Scenes sceneName) {
        Pane nextScene;

        if (scenes.containsKey(sceneName)) {
            nextScene = scenes.get(sceneName);
            scene.setRoot(nextScene);
        }
    }

    public HashMap<Scenes, Pane> getScenes() {
        return scenes;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
