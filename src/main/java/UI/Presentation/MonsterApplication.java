package UI.Presentation;

import Business.Competition.CalculatorGame;
import Business.GameLogic.Game;
import Business.GameLogic.GameImpl;
import UI.Elements.CalculationGame.CalculationGameViewController;
import UI.Elements.ClickEventGame.ClickEventGameViewController;
import UI.Elements.Game.GameViewController;
import UI.Elements.GameField.GameFieldViewController;
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
            controller = new StartViewController(this, game);
            scenes.put(Scenes.START_VIEW, controller.getRootView());

            controller = new GameViewController(this, game);
            scenes.put(Scenes.GAME_VIEW, controller.getRootView());

            controller = new GameFieldViewController(this, game);
            scenes.put(Scenes.GAMEFIELD_VIEW, controller.getRootView());

            controller = new CalculationGameViewController(new CalculatorGame(60), this, game);
            scenes.put(Scenes.CALCULATIONGAME_VIEW, controller.getRootView());

            controller = new ClickEventGameViewController(this, game);
            scenes.put(Scenes.CLICKEVENTGAME_VIEW, controller.getRootView());

            Pane root = scenes.get(Scenes.START_VIEW);
            scene = new Scene(root, 550, 500);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
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

    public static void main(String[] args) {
        launch(args);
    }
}
