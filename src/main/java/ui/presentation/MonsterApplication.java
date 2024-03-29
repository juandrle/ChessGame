package ui.presentation;

import business.gameLogic.Game;
import business.gameLogic.GameImpl;
import ui.Scenes;
import ui.elements.start.StartViewController;
import ui.ViewController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Objects;

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
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/application.css")).toExternalForm());
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
