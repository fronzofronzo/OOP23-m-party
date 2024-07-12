package it.unibo.mparty.view.minigames.ciao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.unibo.mparty.controller.minigames.secretcode.SecretCodeController;
import it.unibo.mparty.controller.minigames.secretcode.SecretCodeControllerImpl;
import it.unibo.mparty.model.minigames.secretcode.util.SecretCodeColors;
import it.unibo.mparty.model.minigames.secretcode.util.SecretCodeResults;
import it.unibo.mparty.utilities.Pair;
import it.unibo.mparty.view.AbstractSceneView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * This class implements {@link SecretCodeView} and extends
 * {@link AbstractSceneView}.
 */
public class SecretCodeViewImpl extends AbstractSceneView implements SecretCodeView {

    private static final Map<SecretCodeResults, Color> COLORS_RES = Map.of(SecretCodeResults.CORRECT_COLOR_AND_POSITION,
            Color.GREEN,
            SecretCodeResults.CORRECT_COLOR, Color.RED,
            SecretCodeResults.WRONG_COLOR, Color.BLACK);
    private static final Map<SecretCodeColors, Color> COLORS_GUESS = Map.of(SecretCodeColors.ARANCIONE, Color.ORANGE,
            SecretCodeColors.BLU, Color.BLUE,
            SecretCodeColors.ROSA, Color.PINK,
            SecretCodeColors.ROSSO, Color.RED,
            SecretCodeColors.VERDE, Color.GREEN,
            SecretCodeColors.VIOLA, Color.PURPLE);
    private static final String TEXT_LABEL_GUESS = "Tentativi di ";
    private static final String TEXT_LABEL_RESULTS = "Risultati di ";
    private static final int RADIUS = 10;
    private List<GridPane> gridPaneGuesses;
    private List<GridPane> gridPaneResults;
    private List<Circle> solutions;
    private List<String> playersNames;
    private final SecretCodeController controller = new SecretCodeControllerImpl(this);

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
    @FXML
    private Label labelResult;
    @FXML
    private Button buttonBackToBoard;
    @FXML
    private Circle sol1;
    @FXML
    private Circle sol2;
    @FXML
    private Circle sol3;
    @FXML
    private Circle sol4;

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateGuess(final String player,
            final Pair<Integer, Integer> pos,
            final SecretCodeColors guess) {
        for (int i = 0; i < this.playersNames.size(); i++) {
            if (this.playersNames.get(i).equals(player)) {
                final StackPane stack = this.createStackPaneWithCircle(COLORS_GUESS.get(guess));
                this.gridPaneGuesses.get(i).add(stack, pos.getFirst(), pos.getSecond() - 1);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateResults(final String player,
            final int turn,
            final List<SecretCodeResults> results) {
        for (int i = 0; i < this.playersNames.size(); i++) {
            if (this.playersNames.get(i).equals(player)) {
                for (int j = 0; j < results.size(); j++) {
                    final StackPane stack = this.createStackPaneWithCircle(COLORS_RES.get(results.get(j)));
                    this.gridPaneResults.get(i).add(stack, j, turn - 1);
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showSolution(final List<SecretCodeColors> solution) {
        for (int i = 0; i < solution.size(); i++) {
            this.solutions.get(i).setFill(COLORS_GUESS.get(solution.get(i)));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startMinigame(final List<String> players) {
        this.controller.initGame(players);
        this.createData(players);
        this.setUpLabels();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showResult(final Pair<String, Integer> result) {
        this.labelResult.setText(result.getFirst() + " HA VINTO " + result.getSecond() + " MONETE");
    }

    @FXML
    @SuppressWarnings("PMD.UnusedPrivateMethod")
    private void addColor(final ActionEvent e) {
        final Button bt = (Button) e.getSource();
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

    @FXML
    @SuppressWarnings("PMD.UnusedPrivateMethod")
    private void guess() {
        this.controller.guess();
    }

    @FXML
    @SuppressWarnings("PMD.UnusedPrivateMethod")
    private void backToBoard() {
        this.controller.endGame();
    }

    private void createData(final List<String> players) {
        this.playersNames = new ArrayList<>();
        this.gridPaneGuesses = new ArrayList<>();
        this.gridPaneResults = new ArrayList<>();
        this.solutions = new ArrayList<>();
        for (final String p : players) {
            this.playersNames.add(p);
        }
        this.gridPaneGuesses.add(gridPaneGuessP1);
        this.gridPaneGuesses.add(gridPaneGuessP2);
        this.gridPaneResults = List.of(gridPaneResP1, gridPaneResP2);
        this.solutions = List.of(sol1, sol2, sol3, sol4);
    }

    private void setUpLabels() {
        this.labelGuessP1.setText(TEXT_LABEL_GUESS + this.playersNames.get(0));
        this.labelGuessP2.setText(TEXT_LABEL_GUESS + this.playersNames.get(1));
        this.labelResultP1.setText(TEXT_LABEL_RESULTS + this.playersNames.get(0));
        this.labelResultP2.setText(TEXT_LABEL_RESULTS + this.playersNames.get(1));
    }

    private StackPane createStackPaneWithCircle(final Color colore) {
        final Circle c = new Circle(RADIUS, colore);
        final StackPane stackPane = new StackPane();
        stackPane.setAlignment(Pos.CENTER);
        stackPane.getChildren().add(c);
        return stackPane;
    }
}
