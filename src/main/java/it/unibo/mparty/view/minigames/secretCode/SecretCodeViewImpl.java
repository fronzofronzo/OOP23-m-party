package it.unibo.mparty.view.minigames.secretCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.unibo.mparty.controller.minigames.secretCode.SecretCodeController;
import it.unibo.mparty.controller.minigames.secretCode.SecretCodeControllerImpl;
import it.unibo.mparty.model.minigames.secretCode.util.SecretCodeColors;
import it.unibo.mparty.utilities.Pair;
import it.unibo.mparty.utilities.Position;
import it.unibo.mparty.view.AbstractSceneView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;

public class SecretCodeViewImpl extends AbstractSceneView implements SecretCodeView {

    private final SecretCodeController controller = new SecretCodeControllerImpl(this);

    private static final String TEXT_LABEL_GUESS = "Tentativi di ";
    private static final String TEXT_LABEL_RESULTS = "Risultati di ";
    private static final int RADIUS = 10;

    @FXML
    private GridPane gridPaneGuessP1;
    @FXML
    private GridPane gridPaneGuessP2;
    @FXML
    private GridPane gridPaneResP1;
    @FXML
    private GridPane gridPaneResP2;
    @FXML
    private Label labelGuessP1;
    @FXML
    private Label labelGuessP2;
    @FXML
    private Label labelResultP1;
    @FXML
    private Label labelResultP2;
    @FXML
    private Button buttonGreen;
    @FXML
    private Button buttonBlu;
    @FXML
    private Button buttonPurple;
    @FXML
    private Button buttonPink;
    @FXML
    private Button buttonRed;
    @FXML
    private Button buttonOrange;


    private List<Map<Position, Circle>> mapGuesses = new ArrayList<>();
    private List<Map<Position, Circle>> mapResults = new ArrayList<>();
    private List<String> playersNames = new ArrayList<>();

    @FXML
    void addColor(ActionEvent e) {
        Button bt = (Button)e.getSource();
        if (bt.equals(this.buttonGreen)) {
            this.controller.addColor(SecretCodeColors.VERDE);
        } else if (bt.equals(buttonBlu)) {
            this.controller.addColor(SecretCodeColors.BLU);
        } else if (bt.equals(buttonPurple)) {
            this.controller.addColor(SecretCodeColors.VIOLA);
        } else if (bt.equals(buttonPink)) {
            this.controller.addColor(SecretCodeColors.ROSA);
        } else if (bt.equals(buttonRed)) {
            this.controller.addColor(SecretCodeColors.ROSSO);
        } else if (bt.equals(buttonOrange)) {
            this.controller.addColor(SecretCodeColors.ARANCIONE);
        }
    }

    @Override
    public void showResult(Pair<String, Integer> result) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'showResult'");
    }

    @Override
    public void startMinigame(List<String> players) {
        this.controller.initGame(players);
        this.createData(players);
        this.setUpLabels();
    }

    private void createData(List<String> players) {
        for (int i = 0; i < players.size(); i++) {
            this.playersNames.add(players.get(i));
            this.mapGuesses.add(new HashMap<>());
            this.mapResults.add(new HashMap<>());
        }
    }

    private void setUpLabels() {
        this.labelGuessP1.setText(TEXT_LABEL_GUESS + this.playersNames.getFirst());
        this.labelGuessP2.setText(TEXT_LABEL_GUESS + this.playersNames.getLast());
        this.labelResultP1.setText(TEXT_LABEL_RESULTS + this.playersNames.getFirst());
        this.labelResultP2.setText(TEXT_LABEL_RESULTS + this.playersNames.getLast());
    }

}
