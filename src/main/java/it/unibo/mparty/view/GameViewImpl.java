package it.unibo.mparty.view;

import it.unibo.mparty.controller.GameControllerImpl;
import it.unibo.mparty.model.GameModelImpl;
import it.unibo.mparty.utilities.Pair;
import it.unibo.mparty.utilities.Position;
import it.unibo.mparty.utilities.SlotType;
import it.unibo.mparty.view.GameBoardView.GameBoardView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import it.unibo.mparty.controller.GameController;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class GameViewImpl extends Application implements GameView{

    private final static double DEFAULT_DIMENSION_VALUE = -1;
    private final static String PATH = "/layouts/";
    private Pair<Scene,GameBoardView> board;

    private Stage stage;
    private final GameController controller = new GameControllerImpl(this);

    @Override
    public void setScene(String path) throws IOException {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource(PATH+path)) ;
        final Parent root = loader.load(getClass().getResourceAsStream(PATH+path));
        final Scene scene = new Scene(root, root.prefWidth(DEFAULT_DIMENSION_VALUE), root.prefHeight(DEFAULT_DIMENSION_VALUE));
        //final SceneView sceneView = loader.<SceneView>getController();
        //sceneView.init(this,this.controller);
        this.boardView = loader.<GameBoardView>getController();
        this.stage.setScene(scene);
<<<<<<< HEAD
        final Pair<Scene, SceneView> scene = this.loadScene(path);
        scene.getY().init(this,this.controller);
        this.stage.setScene(scene.getX());
=======
        this.stage.setMinWidth(1000);
        this.stage.setMinHeight(700);
>>>>>>> feature/gameBorad
        this.stage.show();
    }



    @Override
    public void switchToBoard() {
        this.stage.setScene(board.getX());
    }

    @Override
    public void setUpBoard() throws IOException {
        final Pair<Scene,SceneView> scene =  this.loadScene("GameBoard.fxml");
        this.board = new Pair<>(scene.getX(),(GameBoardView) scene.getY());
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        this.setScene("GameBoard.fxml");
        this.controller.startGame(new GameModelImpl(null, ""));
        this.stage.setMaximized(true);
        this.stage.show();
    }



    @Override
    public void setUpBoard(Pair<Integer,Integer> dimension, Map<Position, SlotType> board, List<String> nicknames) {
        this.boardView.setUpBoard(dimension, board, nicknames);
    }    
}