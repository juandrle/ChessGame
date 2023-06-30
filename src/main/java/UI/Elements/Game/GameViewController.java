package UI.Elements.Game;

import Business.GameLogic.Game;
import Business.Gamepiece.Gamepiece;
import UI.Presentation.MonsterApplication;
import UI.ViewController;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
        view.useItemPlayer1.setOnAction(e -> {
            game.getGamefield().getPlayer1().useItem(game.getGamefield().getPlayer1().getCurrGamepiece());
        });
        view.useItemPlayer2.setOnAction(e -> game.getGamefield().getPlayer2().useItem(game.getGamefield().getPlayer2().getCurrGamepiece()));
        view.player1Gamepieces.setItems(game.getGamefield().getPlayer1().getOwnGamepieces());
        view.player2Gamepieces.setItems(game.getGamefield().getPlayer2().getOwnGamepieces());

        view.player1Gamepieces.setCellFactory(e -> new ListCell<>() {

            ImageView gamepiece = new ImageView();

            {
                gamepiece.setFitWidth(40);
                gamepiece.setFitHeight(40);
            }

            @Override
            protected void updateItem(Gamepiece item, boolean empty) {
                super.updateItem(item, empty);
                setStyle("-fx-background-color: #ececec");
                if (!empty) {
                    Platform.runLater(() -> setGraphic(gamepiece));
                    if (item.equals(game.getGamefield().getPlayer1().getCurrGamepiece()))
                        setStyle("-fx-background-color: #04a5a8");
                    switch (item.getRank()) {
                        case 0 -> {
                            item.setImage(new Image("files/pictures/gamepieces/PawnPlayer1.png"));
                            gamepiece.setImage(item.getImage().get());
                        }
                        case 1 -> {
                            item.setImage(new Image("files/pictures/gamepieces/TowerPlayer1.png"));
                            gamepiece.setImage(item.getImage().get());
                        }
                        case 2 -> {
                            item.setImage(new Image("files/pictures/gamepieces/QueenPlayer1.png"));
                            gamepiece.setImage(item.getImage().get());
                        }
                    }
                } else Platform.runLater(() -> setGraphic(null));
            }
        });
        view.player2Gamepieces.setCellFactory(e -> new ListCell<>() {
            ImageView gamepiece = new ImageView();

            {
                gamepiece.setFitWidth(40);
                gamepiece.setFitHeight(40);
            }

            @Override
            protected void updateItem(Gamepiece item, boolean empty) {
                super.updateItem(item, empty);
                setStyle("-fx-background-color: #ececec");
                if (!empty) {
                    if (item.equals(game.getGamefield().getPlayer2().getCurrGamepiece()))
                        setStyle("-fx-background-color: #04a5a8");
                    Platform.runLater(() -> setGraphic(gamepiece));
                    switch (item.getRank()) {
                        case 0 -> {
                            item.setImage(new Image("files/pictures/gamepieces/PawnPlayer2.png"));
                            gamepiece.setImage(item.getImage().get());
                        }
                        case 1 -> {
                            item.setImage(new Image("files/pictures/gamepieces/TowerPlayer2.png"));
                            gamepiece.setImage(item.getImage().get());
                        }
                        case 2 -> {
                            item.setImage(new Image("files/pictures/gamepieces/QueenPlayer2.png"));
                            gamepiece.setImage(item.getImage().get());
                        }
                    }
                } else Platform.runLater(() -> setGraphic(null));
            }
        });

        view.player1Gamepieces.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue == null) return;
            view.player1ItemDesc.clear();
            view.selPiece1.setImage(newValue.getImage().get());
            if (newValue.getInventory() != null) {
                view.selPiece1Item.setImage(newValue.getInventory().getImage());
                view.player1ItemDesc.setText(newValue.getInventory().getDescription());
            } else view.selPiece1Item.setImage(new Image("files/pictures/white_placeholder.png"));
        }));
        view.player2Gamepieces.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue == null) return;
            view.player2ItemDesc.clear();
            view.selPiece2.setImage(newValue.getImage().get());
            if (newValue.getInventory() != null) {
                view.selPiece2Item.setImage(newValue.getInventory().getImage());
                view.player2ItemDesc.setText(newValue.getInventory().getDescription());
            } else view.selPiece2Item.setImage(new Image("files/pictures/white_placeholder.png"));
        }));
        game.getGamefield().getPlayer1().getOwnGamepieces().addListener((ListChangeListener<Gamepiece>) change ->
                {
                        view.player1Gamepieces.setItems(game.getGamefield().getPlayer1().getOwnGamepieces());
                }
        );
        game.getGamefield().getPlayer2().getOwnGamepieces().addListener((ListChangeListener<Gamepiece>) change ->
                {
                        view.player2Gamepieces.setItems(game.getGamefield().getPlayer2().getOwnGamepieces());
                }
        );
        game.getGamefield().getPlayer1().currGamepieceProperty().addListener((observable, oldValue, newValue) ->
                {
                    //if (newValue != null) {
                    view.player1Gamepieces.getSelectionModel().select(newValue);
                    //}
                }
        );
        game.getGamefield().getPlayer2().currGamepieceProperty().addListener((observable, oldValue, newValue) ->
                {
                    //  if (newValue != null) {
                    view.player2Gamepieces.getSelectionModel().select(newValue);
                    //  }
                }
        );
    }
}
