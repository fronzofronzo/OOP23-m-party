package it.unibo.mparty.view.minigames.connect4.impl;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.unibo.mparty.controller.minigames.connect4.api.Connect4Controller;
import it.unibo.mparty.controller.minigames.connect4.impl.Connect4ControllerImpl;
import it.unibo.mparty.utilities.Pair;
import it.unibo.mparty.view.AbstractSceneView;
import it.unibo.mparty.view.minigames.connect4.api.Connect4View;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Connect4ViewImpl extends AbstractSceneView implements Connect4View {

    final private Connect4Controller controller = new Connect4ControllerImpl(this);

    @FXML
    private Label displayLabel;

    @FXML
    private Button exitButton;

    @FXML
    private GridPane gameGrid;

    @FXML
    private GridPane buttonGrid;

    
    @Override
    public void showResult(Pair<String, Integer> result) {
        this.updateDisplayLabel(result.getFirst() + " ha vinto " + result.getSecond() + " monete");
        this.activateExitButton(true);
        buttonGrid.getChildren().listIterator().forEachRemaining(it -> it.setDisable(true));
    }

    @Override
    public void startMinigame(List<String> players) {

    }

    @Override
    public void updateDisplayLabel(String msg) {
        displayLabel.setText(msg);
    }

    @Override
    public void activateExitButton (boolean pred) {
        exitButton.setDisable(!pred);
    }

    @Override
    public void addCircle(int col, int row, boolean color) {
        Circle circle = new Circle(25);
        circle.setFill(color ? Color.RED : Color.YELLOW);
        StackPane stack = new StackPane();
        stack.getChildren().add(circle);
        gameGrid.add(stack, col, row);
    }

    @FXML
    private void getColumn(ActionEvent e) {
        Button but = (Button) (e.getSource());
        var index = GridPane.getColumnIndex(but);
        if (index==null) {
            index=0;
        }
        this.controller.check(index);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.controller.initGame(List.of("filo","dan"));
    }

    @FXML
    private void closeView() {
        //this.controller.endGame();
    }

}
