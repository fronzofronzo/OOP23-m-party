package it.unibo.mparty.view;

import it.unibo.mparty.controller.GameControllerImpl;
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
import javafx.scene.image.Image;
import javafx.stage.Stage;
import it.unibo.mparty.controller.GameController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * This class implements the {@link GameView} interface using JavaFX as graphic
 * library. It extends the {@link Application} class and implements the method
 * {@code start(Stage primaryStage} to start correctly ad JavaFX application. It
 * handles the switching and the loading of different game scenes.
 */
public class GameViewImpl extends Application implements GameView {

    private static final double DEFAULT_DIMENSION_VALUE = -1;
    private static final String PATH = "/layouts/";
    private static final String PATH_MINIGAMES = "minigames/";
    private static final String EXTENSION = ".fxml";
    private static final String SHOP_NAME = "Shop";
    private static final int PREF_HEIGHT = 700;
    private static final int PREF_WIDTH = 1000;

    private final GameController controller = new GameControllerImpl(this);
    private GameBoardView boardView;
    private Scene boardScene;
    private Stage stage;

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(final Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        this.stage.getIcons().add(new Image("/images/marioParty.png"));
        this.setBoardView();
        /*
        //FAKE START
        PlayerBuilder pb = new PlayerBuilderImplementation();
        Player p1 = pb.username("Mario").character("Mario").buildPlayer();
        Player p2 = pb.username("Luigi").character("Luigi").buildPlayer();
        Player p3 = pb.username("Daisy").character("Luigi").buildPlayer();
        Player p4 = pb.username("Peach").character("Luigi").buildPlayer();
        this.controller.startGame(new GameModelImpl(List.of(p1,p2,p3,p4), "MEDIUM"));
        */
        //final Pair<Scene, SceneView> scenePair = this.loadScene("initialScreen");
        //this.stage.setScene(scenePair.getFirst());
        this.setMinigameScene("connect4", List.of("player2", "player1"));
        this.stage.setMinWidth(PREF_WIDTH);
        this.stage.setMinHeight(PREF_HEIGHT);
        this.stage.setMaximized(true);
        this.stage.show();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBoardScene() throws IOException {
        this.stage.setScene(boardScene);
        this.setStageSize();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMinigameScene(final String name, final List<String> players) throws IOException {
        final Pair<Scene, SceneView> pair = this.loadScene(PATH_MINIGAMES + name);
        final Scene minigameScene = pair.getFirst();
        final MinigameView minigameView = (MinigameView) pair.getSecond();
        minigameView.startMinigame(players);
        this.stage.setScene(minigameScene);
        this.setStageSize();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setShopScene() throws IOException {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource(PATH + SHOP_NAME + EXTENSION));
        final Parent root = loader.load(getClass().getResourceAsStream(PATH + SHOP_NAME + EXTENSION));
        final Scene scene = new Scene(root, root.prefWidth(DEFAULT_DIMENSION_VALUE), root.prefHeight(DEFAULT_DIMENSION_VALUE));
        final ShopView shopView = loader.<ShopView>getController();
        shopView.init(this, this.controller);
        this.stage.setScene(scene);
        shopView.initShopView();
        this.setStageSize();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUpBoard(final Pair<Integer, Integer> dimension, final Map<Position, SlotType> board, final List<String> usernames) {
        this.boardView.setUpBoard(dimension, board, usernames);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showResultDice(final int result) {
        this.boardView.showResultDice(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updatePlayer(final String player, final int coins, final int stars, final List<String> items, final Position position) {
        this.boardView.updatePlayer(player, coins, stars, items, position);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateCommands(final List<String> items, final String message, final Pair<String, String> turn) {
        this.boardView.updateCommands(items, message, turn);
    }

    @Override
    public void showResults(final Map<String, Pair<Integer, Integer>> result) throws IOException {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource(PATH + "endGame" + EXTENSION));
        ;
        final Parent root = loader.load(getClass().getResourceAsStream(PATH + "endGame" + EXTENSION));
        final Scene scene = new Scene(root, root.prefWidth(DEFAULT_DIMENSION_VALUE), root.prefHeight(DEFAULT_DIMENSION_VALUE));
        final EndGameView endGameView = ((EndGameView) loader.<SceneView>getController());
        endGameView.showResults(result);
        this.stage.setScene(scene);
        this.setStageSize();
    }

    @Override
    public void updateBoard(Map<Position, SlotType> boardUpdates) {
        this.boardView.updateBoard(boardUpdates);
    }

    private void setBoardView() throws IOException {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource(PATH + "GameBoard" + EXTENSION));
        ;
        final Parent root = loader.load(getClass().getResourceAsStream(PATH + "GameBoard" + EXTENSION));
        this.boardScene = new Scene(root, root.prefWidth(DEFAULT_DIMENSION_VALUE), root.prefHeight(DEFAULT_DIMENSION_VALUE));
        this.boardView = loader.<GameBoardView>getController();
        this.boardView.init(this, this.controller);
    }


    private Pair<Scene, SceneView> loadScene(String name) throws IOException {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource(PATH + name + EXTENSION));
        final Parent root = loader.load(getClass().getResourceAsStream(PATH + name + EXTENSION));
        final Scene scene = new Scene(root, root.prefWidth(DEFAULT_DIMENSION_VALUE), root.prefHeight(DEFAULT_DIMENSION_VALUE));
        final SceneView sceneView = loader.<SceneView>getController();
        sceneView.init(this, this.controller);
        return new Pair<>(scene, sceneView);
    }

    private void setStageSize() {
        this.stage.setMinWidth(PREF_WIDTH);
        this.stage.setMinHeight(PREF_HEIGHT);
        this.stage.setFullScreen(true);
        this.stage.show();
    }

}