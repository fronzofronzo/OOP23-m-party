package it.unibo.mparty.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class GameViewImpl extends Application implements GameView{

    private Stage stage;
    @Override
    public void setScene(String path) {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource(path)) ;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }

    private String getResource
}
