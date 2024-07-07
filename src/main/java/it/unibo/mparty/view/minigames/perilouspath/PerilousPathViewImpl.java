package it.unibo.mparty.view.minigames.perilouspath;

import it.unibo.mparty.controller.GameController;
import it.unibo.mparty.controller.minigames.perilousPath.PerilousPathController;
import it.unibo.mparty.controller.minigames.perilousPath.PerilousPathControllerImpl;
import it.unibo.mparty.model.minigames.perilouspath.api.AbstractPosition;
import it.unibo.mparty.model.minigames.perilouspath.api.PerilousPath;
import it.unibo.mparty.model.minigames.perilouspath.impl.BallPosition;
import it.unibo.mparty.model.minigames.perilouspath.impl.BombPosition;
import it.unibo.mparty.model.minigames.perilouspath.impl.PathPosition;
import it.unibo.mparty.utilities.Pair;
import it.unibo.mparty.view.AbstractSceneView;
import it.unibo.mparty.view.GameView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class PerilousPathViewImpl extends AbstractSceneView implements PerilousPathView{

    @FXML
    private GridPane myGridPane;

    @FXML
    private Button startButton;

    @FXML
    private Label gameLabel;

    private Button button;
    private final static int SIZE = 8;
    private final Image bombImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/BombImage.png")));
    private final Image ballImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/pallaCalcio.jpg")));
    //private final ImageView ballImageView = new ImageView(ballImage);



    private final PerilousPathController observer = new PerilousPathControllerImpl(this);

    @Override
    public void setUpView(List<AbstractPosition> balls, List<AbstractPosition> bombs) {
        for(var child: this.myGridPane.getChildren()){
            var ballPos = this.ballPosition(child);
            var bombPos = this.bombPosition(child);
            if(balls.stream().anyMatch(b -> b.getX() == ballPos.getX() && b.getY() == ballPos.getY())){
                if (child instanceof Button) {
                    this.setImage(((Button) child),new ImageView(ballImage));
                }
            }
            if(bombs.stream().anyMatch(b -> b.getX() == bombPos.getX() && b.getY() == bombPos.getY())){
                if (child instanceof Button) {
                    this.setImage(((Button) child),new ImageView(bombImage));
                }
            }
        }
        this.myGridPane.setDisable(true);
    }

    @Override
    public void hideBombs(List<AbstractPosition> bombs) {
        for(var child: this.myGridPane.getChildren()){
            var bombPos = this.bombPosition(child);
            if(bombs.stream().anyMatch(b -> b.getX() == bombPos.getX() && b.getY() == bombPos.getY())){
                if (child instanceof Button) {
                    ((Button) child).setGraphic(null);
                }
            }
        }
        this.myGridPane.setDisable(false);
    }

    @Override
    public void showBombs(List<AbstractPosition> bombs) {
        for(var child: this.myGridPane.getChildren()){
            var bombPos = this.bombPosition(child);
            if(bombs.stream().anyMatch(b -> b.getX() == bombPos.getX() && b.getY() == bombPos.getY())){
                if (child instanceof Button) {
                    this.setImage(((Button) child),new ImageView(bombImage));
                }
            }
        }
        this.myGridPane.setDisable(true);
    }



    @Override
    public void hitTile(PerilousPath.Type type) {
        switch(type){
            case PATH -> {
                button.setStyle("-fx-background-color: #f3f5f8;");
                this.gameLabel.setText("MOSSA VALIDA");
            }
            case WRONG ->
                    this.gameLabel.setText("MOSSA NON VALIDA");
        }
    }



    private final EventHandler<MouseEvent> buttonClicked = e -> {
        this.button = (Button) e.getSource();
        var pos = this.buttonPosition(this.button);
        this.observer.hit(pos);
    };

    @Override
    public void handleStartButton(ActionEvent e) throws InterruptedException {
        this.gridCreation();
        this.observer.setUp();
        this.startButton.setDisable(true);
    }


    @Override
    public void init(GameView view, GameController controller) {
        super.init(view, controller);
    }

    @Override
    public GameController getMainController() {
        return super.getMainController();
    }

    @Override
    public GameView getMainView() {
        return super.getMainView();
    }


    private AbstractPosition buttonPosition(Node child){
        var x = GridPane.getRowIndex(child) == null ? 0 : GridPane.getRowIndex(child);
        var y = GridPane.getColumnIndex(child) == null ? 0 : GridPane.getColumnIndex(child);
        return new PathPosition(x,y,SIZE);
    }

    private BallPosition ballPosition(Node child){
        var x = GridPane.getRowIndex(child) == null ? 0 : GridPane.getRowIndex(child);
        var y = GridPane.getColumnIndex(child) == null ? 0 : GridPane.getColumnIndex(child);
        return new BallPosition(x,y,SIZE);
    }

    private BombPosition bombPosition(Node child){
        var x = GridPane.getRowIndex(child) == null ? 0 : GridPane.getRowIndex(child);
        var y = GridPane.getColumnIndex(child) == null ? 0 : GridPane.getColumnIndex(child);
        return new BombPosition(x,y,SIZE);
    }


    @Override
    public void showResult(Pair<String, Integer> result) {
        this.gameLabel.setText("il giocatore : " + result.getFirst() + " ha vinto " + result.getSecond() + " coins");
    }

    @Override
    public void startMinigame(List<String> players) {
        this.observer.initGame(players);
    }

    private void gridCreation(){
        for(int i = 0; i < SIZE; i++){
            for(int j = 0; j < SIZE; j++){
                final Button button = new Button();
                button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                button.setOnMouseClicked(buttonClicked);
                this.myGridPane.add(button,i,j);
            }
        }
    }

    private void setImage(Button button,ImageView imageView){
        button.setGraphic(imageView);
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);
        imageView.setPreserveRatio(false);
    }
}