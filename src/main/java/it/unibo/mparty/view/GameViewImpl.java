package it.unibo.mparty.view;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.mparty.controller.GameControllerImpl;
import it.unibo.mparty.utilities.Pair;
import it.unibo.mparty.utilities.Position;
import it.unibo.mparty.utilities.SlotType;
import it.unibo.mparty.view.GameBoardView.GameBoardView;
import it.unibo.mparty.view.endgame.api.EndGameView;
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
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Needed to save primary stage in stage variable"
            + " to modify it while the application is running ")
    public void start(final Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        this.stage.getIcons().add(new Image("/images/marioParty.png"));
        this.setBoardView();
        final Pair<Scene, SceneView> scenePair = this.loadScene("InitialScreen");
        this.stage.setScene(scenePair.getFirst());
        // this.setMinigameScene("memorycard", List.of("pl1", "pl2"));
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
        final Pair<Scene, SceneView> pair = this.loadScene(SHOP_NAME);
        final Scene shopScene = pair.getFirst();
        final ShopView shopView = (ShopView) pair.getSecond();
        this.stage.setScene(shopScene);
        shopView.initShopView();
        this.setStageSize();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUpBoard(final Pair<Integer, Integer> dimension,
            final Map<Position, SlotType> board,
            final List<String> usernames) {
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
    public void updatePlayer(final String player, final int coins, final int stars, final List<String> items,
            final Position position) {
        this.boardView.updatePlayer(player, coins, stars, items, position);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateCommands(final List<String> items, final String message, final Pair<String, String> turn) {
        this.boardView.updateCommands(items, message, turn);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showResults(final Map<String, Pair<Integer, Integer>> result) throws IOException {
        final Pair<Scene, SceneView> pair = this.loadScene("endGame");
        final Scene scene = pair.getFirst();
        final EndGameView endGameView = (EndGameView) pair.getSecond();
        endGameView.showResults(result);
        this.stage.setScene(scene);
        this.setStageSize();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateBoard(final Map<Position, SlotType> boardUpdates) {
        this.boardView.updateBoard(boardUpdates);
    }

    private void setBoardView() throws IOException {
        final Pair<Scene, SceneView> pair = this.loadScene("GameBoard");
        this.boardScene = pair.getFirst();
        this.boardView = (GameBoardView) pair.getSecond();
    }

    @SuppressFBWarnings(value = "UI_UNSAFE_GETRESOURCE", justification = "GetResources is already used safely. ")
    private Pair<Scene, SceneView> loadScene(final String name) throws IOException {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource(PATH + name + EXTENSION));
        final Parent root = loader.load();
        final Scene scene = new Scene(root, root.prefWidth(DEFAULT_DIMENSION_VALUE),
                root.prefHeight(DEFAULT_DIMENSION_VALUE));
        final SceneView sceneView = loader.getController();
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
