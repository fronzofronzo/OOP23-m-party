package it.unibo.mparty.view;

import it.unibo.mparty.controller.GameControllerImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import it.unibo.mparty.controller.GameController;
import java.io.IOException;

public class GameViewImpl extends Application implements GameView{

    private final static double DEFAULT_DIMENSION_VALUE = -1;
    private final static String PATH = "/layouts/";

    private Stage stage;
    private final GameController controller = new GameControllerImpl();

    @Override
    public void setScene(String path) throws IOException {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource(PATH+path)) ;
        final Parent root = loader.load(getClass().getResourceAsStream(PATH+path));
        final Scene scene = new Scene(root, root.prefWidth(DEFAULT_DIMENSION_VALUE), root.prefHeight(DEFAULT_DIMENSION_VALUE));
        final SceneView sceneView = loader.<SceneView>getController();
        sceneView.init(this,this.controller);
        this.stage.setScene(scene);
        this.stage.show();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        this.stage.show();
        this.setScene("");
    }
}
