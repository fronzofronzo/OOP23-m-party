package it.unibo.mparty.view;

import it.unibo.mparty.controller.GameControllerImpl;
import it.unibo.mparty.model.GameModelImpl;
import it.unibo.mparty.model.player.api.Player;
import it.unibo.mparty.model.player.api.PlayerBuilder;
import it.unibo.mparty.model.player.impl.PlayerBuilderImplementation;
import it.unibo.mparty.utilities.Pair;
import it.unibo.mparty.utilities.Position;
import it.unibo.mparty.utilities.SlotType;
import it.unibo.mparty.view.GameBoardView.GameBoardView;
import it.unibo.mparty.view.endGame.api.EndGameView;
import it.unibo.mparty.view.minigames.MinigameView;
import it.unibo.mparty.view.shop.api.ShopView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import it.unibo.mparty.controller.GameController;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class GameViewImpl extends Application implements GameView{

    private final static double DEFAULT_DIMENSION_VALUE = -1;
    private final static String PATH = "/layouts/";
    private final static String PATH_MINIGAMES = "/layouts/minigames/";
    private final static String EXTENSION = ".fxml";
    private final static String shopName = "Shop";
    private GameBoardView boardView;
    private Scene boardScene;

    private Stage stage;
    private final GameController controller = new GameControllerImpl(this);
    private SceneView sceneView;
    private MinigameView minigameView;


    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        this.setBoardView();
        PlayerBuilder pb = new PlayerBuilderImplementation();
        Player p1 = pb.username("Mario").character("Mario").buildPlayer();
        Player p2 = pb.username("Luigi").character("Luigi").buildPlayer();
        Player p3 = pb.username("Daisy").character("Luigi").buildPlayer();
        Player p4 = pb.username("Peach").character("Luigi").buildPlayer();
        this.controller.startGame(new GameModelImpl(List.of(p1,p2,p3,p4), "MEDIUM"));
        this.stage.setMaximized(true);
        this.stage.show();
    }


    @Override
    public void setScene(SceneType sceneType) throws IOException {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource(PATH + sceneType.getSceneName() + EXTENSION)); ;
        final Parent root = loader.load(getClass().getResourceAsStream(PATH + sceneType.getSceneName() + EXTENSION));
        final Scene scene = new Scene(root, root.prefWidth(DEFAULT_DIMENSION_VALUE), root.prefHeight(DEFAULT_DIMENSION_VALUE));
        this.sceneView = loader.<SceneView>getController();
        this.sceneView.init(this,this.controller);
        this.stage.setScene(scene);
        this.stage.setMinWidth(1000);
        this.stage.setMinHeight(700);
        this.stage.show();
    }

    @Override
    public void setUpBoard(Pair<Integer,Integer> dimension, Map<Position, SlotType> board, List<String> nicknames, Position startingPosition) {
        this.boardView.setUpBoard(dimension, board, nicknames, startingPosition);
    }

    @Override
    public void setMinigameScene(String name) throws IOException {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource(PATH_MINIGAMES + name+ EXTENSION)); ;
        final Parent root = loader.load(getClass().getResourceAsStream( PATH_MINIGAMES + name + EXTENSION));
        final Scene scene = new Scene(root, root.prefWidth(DEFAULT_DIMENSION_VALUE), root.prefHeight(DEFAULT_DIMENSION_VALUE));
        this.minigameView = loader.<MinigameView>getController();
        this.minigameView.init(this,this.controller);
        this.controller.initMinigame();
        this.stage.setScene(scene);
        this.stage.setMinWidth(1000);
        this.stage.setMinHeight(700);
        this.stage.show();
    }



    @Override
    public void setBoardScene() throws IOException {
        this.stage.setScene(boardScene);
    }

    @Override
    public void setShopScene() throws IOException {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource(PATH + shopName + EXTENSION));
        final Parent root = loader.load(getClass().getResourceAsStream(PATH + shopName + EXTENSION));
        final Scene scene = new Scene(root, root.prefWidth(DEFAULT_DIMENSION_VALUE), root.prefHeight(DEFAULT_DIMENSION_VALUE));
        final ShopView shopView = loader.<ShopView>getController();
        shopView.init(this,this.controller);
        this.stage.setScene(scene);
        this.stage.setMinWidth(1000);
        this.stage.setMinHeight(700);
        this.stage.show();
    }


    @Override
    public void showResultDice(int result) {
        this.boardView.showResultDice(result);
    }


    @Override
    public void updatePlayerPos(Pair<String,Position> playerInfo) {
        this.boardView.updatePlayerPos(playerInfo);
    }


    @Override
    public void updatePlayerStats(String player, int coins, int stars, List<String> items) {
        this.updatePlayerStats(player, coins, stars, items);
    }


    @Override
    public void updateCommands(List<String> items, String message) {
        this.boardView.updateCommands(items, message);
    }

    @Override
    public void showResults(List<Player> players) {
        ((EndGameView) this.sceneView).showResults(players);
    }

    private void setBoardView() throws IOException {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource(PATH + "GameBoard" + EXTENSION)); ;
        final Parent root = loader.load(getClass().getResourceAsStream(PATH + "GameBoard" + EXTENSION));
        this.boardScene = new Scene(root,root.prefWidth(DEFAULT_DIMENSION_VALUE), root.prefHeight(DEFAULT_DIMENSION_VALUE));
        this.boardView = loader.<GameBoardView>getController();
        this.boardView.init(this, this.controller);
    }
}