package it.unibo.mparty.view.minigames.memorySweep;

import it.unibo.mparty.controller.minigames.memorySweep.MemorySweepController;
import it.unibo.mparty.controller.minigames.memorySweep.MemorySweepControllerImpl;
import it.unibo.mparty.model.minigames.memorysweep.api.MemorySweep;
import it.unibo.mparty.utilities.Pair;
import it.unibo.mparty.utilities.Position;
import it.unibo.mparty.view.AbstractSceneView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

public class MemorySweepViewImpl extends AbstractSceneView implements MemorySweepView, Initializable {

    private final MemorySweepController controller = new MemorySweepControllerImpl(this);
    private final static int SIZE = 8;

    @FXML
    private Button startButton;

    @FXML
    private Label label;

    @FXML
    private GridPane memorySweepGrid;

    public void handleStartButton(ActionEvent e){
        this.memorySweepGrid.setDisable(false);
        this.startButton.setDisable(true);
        this.controller.setUp();
    }

    @Override
    public void setUp(Set<Position> randoms) {
        for(var child : this.memorySweepGrid.getChildren()){
            var position = this.buttonPos((Button) child);
            if(randoms.contains(position)){
                child.setStyle("-fx-background-color: #35e608;");
            }
        }
    }

    @Override
    public void hit(MemorySweep.HitType type) {

    }

    @Override
    public void hideRandoms(Set<Position> randoms) {
        for(var child : this.memorySweepGrid.getChildren()){
            var position = this.buttonPos((Button) child);
            if(randoms.contains(position)){
                child.setStyle(" ");
            }
        }
    }

    private final EventHandler<MouseEvent> click = event -> {
        var button = (Button) event.getSource();
        this.controller.hit(this.buttonPos(button));
    };

    private Position buttonPos(Button button) {
        var x = GridPane.getRowIndex(button);
        var y = GridPane.getColumnIndex(button);
        return new Position(x,y);
    }

    @Override
    public void showResult(Pair<String, Integer> result) {

    }

    @Override
    public void startMinigame(List<String> players) {
        this.controller.initGame(players);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.gridCreation();
    }

    private void gridCreation(){
        for(int i = 0; i < SIZE; i++){
            for(int j = 0; j < SIZE; j++){
                final Button button = new Button();
                button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                button.setOnMouseClicked(this.click);
                this.memorySweepGrid.add(button,i,j);
            }
        }
        this.memorySweepGrid.setDisable(false);
    }
}