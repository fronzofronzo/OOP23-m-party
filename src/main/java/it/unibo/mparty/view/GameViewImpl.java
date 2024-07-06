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
    private final static String SHOP_NAME = "Shop";
    
    private final GameController controller = new GameControllerImpl(this);
    private GameBoardView boardView;
    private Scene boardScene;
    private Stage stage;

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        this.setBoardView();

        //FAKE START
        PlayerBuilder pb = new PlayerBuilderImplementation();
        Player p1 = pb.username("Mario").character("Mario").buildPlayer();
        Player p2 = pb.username("Luigi").character("Luigi").buildPlayer();
        Player p3 = pb.username("Daisy").character("Luigi").buildPlayer();
        Player p4 = pb.username("Peach").character("Luigi").buildPlayer();
        this.controller.startGame(new GameModelImpl(List.of(p1,p2,p3,p4), "MEDIUM"));

        this.stage.setMinWidth(1000);
        this.stage.setMinHeight(700);

        this.stage.setMaximized(true);
        this.stage.show();
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void setBoardScene() throws IOException {
        this.stage.setScene(boardScene);
        this.stage.setMinWidth(1000);
        this.stage.setMinHeight(700);

        this.stage.setMaximized(true);
        this.stage.show();
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void setMinigameScene(String name) throws IOException {
        final Pair<Scene,SceneView> pair = this.loadScene(name);
        final Scene minigameScene = pair.getX();
        final MinigameView minigameView = (MinigameView) pair.getY();
        this.stage.setScene(minigameScene);
        this.stage.setMinWidth(1000);
        this.stage.setMinHeight(700);
        this.stage.setMaximized(true);
        this.stage.show();
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void setShopScene() throws IOException {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource(PATH + SHOP_NAME + EXTENSION));
        final Parent root = loader.load(getClass().getResourceAsStream(PATH + SHOP_NAME + EXTENSION));
        final Scene scene = new Scene(root, root.prefWidth(DEFAULT_DIMENSION_VALUE), root.prefHeight(DEFAULT_DIMENSION_VALUE));
        final ShopView shopView = loader.<ShopView>getController();
        shopView.init(this,this.controller);
        shopView.initShopView();
        this.stage.setScene(scene);
        this.stage.setMinWidth(1000);
        this.stage.setMinHeight(700);
        this.stage.setMaximized(true);
        this.stage.show();
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void setUpBoard(Pair<Integer,Integer> dimension, Map<Position, SlotType> board, List<String> usernames) {
        this.boardView.setUpBoard(dimension, board, usernames);
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void showResultDice(int result) {
        this.boardView.showResultDice(result);
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void updatePlayer(String player, int coins, int stars, List<String> items, Position position) {
        this.boardView.updatePlayer(player, coins, stars, items, position);
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void updateCommands(List<String> items, String message) {
        this.boardView.updateCommands(items, message);
    }

    @Override
    public void switchToBoard() {
        this.stage.setScene(boardScene);
    }

    private void setBoardView() throws IOException {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource(PATH + "GameBoard" + EXTENSION)); ;
        final Parent root = loader.load(getClass().getResourceAsStream(PATH + "GameBoard" + EXTENSION));
        this.boardScene = new Scene(root,root.prefWidth(DEFAULT_DIMENSION_VALUE), root.prefHeight(DEFAULT_DIMENSION_VALUE));
        this.boardView = loader.<GameBoardView>getController();
        this.boardView.init(this, this.controller);
    }

    @Override
    public void showResults(List<String> players, List<Integer> stars, List<Integer> coins) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'showResults'");
    }

    private Pair<Scene,SceneView> loadScene(String name) throws IOException {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource(PATH_MINIGAMES + name + EXTENSION));
        final Parent root = loader.load(getClass().getResourceAsStream( PATH_MINIGAMES + name + EXTENSION));
        final Scene scene = new Scene(root, root.prefWidth(DEFAULT_DIMENSION_VALUE), root.prefHeight(DEFAULT_DIMENSION_VALUE));
        final SceneView sceneView = loader.<SceneView>getController();
        sceneView.init(this,this.controller);
        return new Pair<>(scene,sceneView);
    }

    private void setStageSize(){
        this.stage.setMinWidth(1000);
        this.stage.setMinHeight(700);
        this.stage.setMaximized(true);
    }

    /* 
    @Override
    public void setScene(SceneType sceneType) throws IOException {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource(PATH + sceneType.getSceneName() + EXTENSION)); ;
        final Parent root = loader.load(getClass().getResourceAsStream(PATH + sceneType.getSceneName() + EXTENSION));
        final Scene scene = new Scene(root, root.prefWidth(DEFAULT_DIMENSION_VALUE), root.prefHeight(DEFAULT_DIMENSION_VALUE));
        final SceneView sceneView = loader.<SceneView>getController();
        sceneView.init(this,this.controller);
        this.stage.setScene(scene);
        this.stage.setMinWidth(1000);
        this.stage.setMinHeight(700);
        this.stage.show();
    }*/
}