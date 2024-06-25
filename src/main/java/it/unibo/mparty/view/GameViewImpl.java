package it.unibo.mparty.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GameViewImpl extends Application implements GameView{

    private Stage stage;
    private final GameController controller= new GameControllerImpl();

    @Override
    public void setScene(String path) throws IOException {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource(getLayoutPath(path))) ;
        final Scene scene = new Scene(loader.load());
        final SceneView sceneView = loader.<SceneView>getController();
        sceneView.init();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }

    private String getLayoutPath(String layout){
        return "layouts/" + layout;
    }
}
