package it.unibo.mparty.view;

import it.unibo.mparty.controller.GameControllerImpl;
import it.unibo.mparty.model.GameModelImpl;
import it.unibo.mparty.model.player.api.Player;
import it.unibo.mparty.model.player.api.PlayerBuilder;
import it.unibo.mparty.model.player.impl.PlayerBuilderImplementation;
import it.unibo.mparty.utilities.Direction;
import it.unibo.mparty.utilities.Pair;
import it.unibo.mparty.utilities.Position;
import it.unibo.mparty.utilities.SlotType;
import it.unibo.mparty.view.GameBoardView.GameBoardView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import it.unibo.mparty.controller.GameController;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GameViewImpl extends Application implements GameView{

    private final static double DEFAULT_DIMENSION_VALUE = -1;
    private final static String PATH = "/layouts/";
    private final static String PATH_MINIGAMES = "/layouts/minigames/";
    private final static String EXTENSION = ".fxml";
    private GameBoardView boardView;

    private Stage stage;
    private final GameController controller = new GameControllerImpl(this);

    @Override
    public void setScene(String path) throws IOException {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource(PATH + path + EXTENSION)); ;
        final Parent root = loader.load(getClass().getResourceAsStream(PATH + path + EXTENSION));
        final Scene scene = new Scene(root, root.prefWidth(DEFAULT_DIMENSION_VALUE), root.prefHeight(DEFAULT_DIMENSION_VALUE));
        final  SceneView sceneView = loader.<SceneView>getController();
        sceneView.init(this,this.controller);
        this.boardView = loader.<GameBoardView>getController();
        this.stage.setScene(scene);
        this.stage.setMinWidth(1000);
        this.stage.setMinHeight(700);
        this.stage.show();
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        this.setScene("GameBoard");
        PlayerBuilder pb = new PlayerBuilderImplementation();
        Player p1 = pb.username("M").character("Mario").buildPlayer();
        Player p2 = pb.username("L").character("Luigi").buildPlayer();
        this.controller.startGame(new GameModelImpl(List.of(p1,p2), "MEDIUM"));
        this.stage.setMaximized(true);
        this.stage.show();
    }



    @Override
    public void setUpBoard(Pair<Integer,Integer> dimension, Map<Position, SlotType> board, List<String> nicknames, Position startingPosition) {
        this.boardView.setUpBoard(dimension, board, nicknames, startingPosition);
    }

    @Override
    public void setMinigameScene(String name) throws IOException {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource(PATH_MINIGAMES + EXTENSION)); ;
        final Parent root = loader.load(getClass().getResourceAsStream( PATH_MINIGAMES + EXTENSION));
        final Scene scene = new Scene(root, root.prefWidth(DEFAULT_DIMENSION_VALUE), root.prefHeight(DEFAULT_DIMENSION_VALUE));
        final  SceneView sceneView = loader.<SceneView>getController();
        sceneView.init(this,this.controller);
    }

    @Override
    public void setShopScene() {

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
}