package it.unibo.mparty.view;

import it.unibo.mparty.controller.GameControllerImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import it.unibo.mparty.controller.GameController;
import java.io.IOException;

public class GameViewImpl extends Application implements GameView{

    private final static double DEFAULT_DIMENSION_VALUE = -1;
    private final static String PATH = "/layouts/";

    private Stage stage;
    private final GameController controller = new GameControllerImpl(this);

    @Override
    public void setScene(String path) throws IOException {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource(PATH + path));
        final Parent root = loader.load(getClass().getResourceAsStream(PATH + path));
        final Scene scene = new Scene(root, this.stage.getWidth(), this.stage.getHeight());
        final SceneView sceneView = loader.<SceneView>getController();
        sceneView.init(this,this.controller);
        this.stage.setScene(scene);
        this.stage.show();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        this.setStageSize();
        this.stage.show();
        this.setScene("minigames/connect4.fxml");
    }

    private void setStageSize(){
        this.stage.setHeight(Screen.getPrimary().getBounds().getHeight()/1.4);
        this.stage.setWidth(Screen.getPrimary().getBounds().getWidth()/1.5);
    }
}
