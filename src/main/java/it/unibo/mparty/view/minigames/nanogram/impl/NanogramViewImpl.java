package it.unibo.mparty.view.minigames.nanogram.impl;

import it.unibo.mparty.controller.minigames.nanogram.api.NanogramController;
import it.unibo.mparty.controller.minigames.nanogram.impl.NanogramControllerImpl;
import it.unibo.mparty.utilities.Pair;
import it.unibo.mparty.view.AbstractSceneView;
import it.unibo.mparty.view.minigames.nanogram.NanogramMessage;
import it.unibo.mparty.view.minigames.nanogram.api.NanogramView;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Implementation of the {@link NanogramView} interface representing the view for a Nanogram game.
 * This class handles the UI components and interactions for the Nanogram game.
 */
public class NanogramViewImpl extends AbstractSceneView implements NanogramView {

    private static final int PREF_SIZE = 300;
    private static final double MARGIN = 30;
    private static final double SCALE = 2.5;

    @FXML
    private BorderPane pane;

    @FXML
    private GridPane columnHints;

    @FXML
    private RadioButton crossButton;

    @FXML
    private RadioButton filledButton;

    @FXML
    private GridPane boardGrid;

    @FXML
    private Label livesLabel;

    @FXML
    private Label messageLabel;

    @FXML
    private GridPane rowHints;

    private NanogramController controller;

    private Button hitButton;

    private final Set<Button> boardButtons = new HashSet<>();

    private final EventHandler<MouseEvent> handleCellClicked = event -> {
        this.hitButton = (Button) event.getSource();
        this.controller.checkCell(GridPane.getRowIndex(hitButton), GridPane.getColumnIndex(hitButton));
        this.hitButton.setDisable(true);
        this.boardButtons.remove(hitButton);
    };

    /**
     * Initializes the view components and sets up the game.
     */
    @FXML
    public void initialize() {
        this.controller = new NanogramControllerImpl(this);
        this.boardGrid.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        GridPane.setMargin(this.boardGrid, new Insets(MARGIN));
        this.pane.setCenter(this.boardGrid);

        final ToggleGroup toggleGroup = new ToggleGroup();
        this.filledButton.setToggleGroup(toggleGroup);
        this.crossButton.setToggleGroup(toggleGroup);

        this.filledButton.setSelected(true);

        this.filledButton.setOnAction(event -> this.controller.setFillState(true));
        this.crossButton.setOnAction(event -> this.controller.setFillState(false));
    }

    /**
     * Handles the event when the tutorial button is clicked.
     *
     * @throws IOException if the tutorial file cannot be loaded
     */
    @FXML
    public void tutorialClicked() throws IOException {
        final FXMLLoader loader = new FXMLLoader(NanogramViewImpl.class.getResource("/layouts/minigames/nanogramTutorial.fxml"));
        final Parent root = loader.load();

        final Stage stage = new Stage();
        stage.setTitle("Nanogram Tutorial");
        stage.getIcons().add(new Image("/images/idea.png"));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(this.pane.getScene().getWindow());
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startMinigame(final List<String> players) {
        this.controller.initGame(players);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initGrid(final int size) {
        this.boardButtons.clear();
        this.boardGrid.setHgap(2);
        this.boardGrid.setVgap(2);

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                final Button button = new Button();
                button.setPrefSize(PREF_SIZE, PREF_SIZE);
                button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                button.setOnMouseClicked(handleCellClicked);
                this.boardButtons.add(button);
                this.boardGrid.add(button, col, row);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fillCell(final boolean isCorrect) {
        if (isCorrect) {
            this.clearMessageLabel();
            this.hitButton.setStyle("-fx-background-color: #38475f");
        } else {
            this.messageLabel.setText(NanogramMessage.ERROR.toString());
            this.hitButton.setGraphic(drawCross(Color.valueOf("#ff4443")));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void crossCell(final boolean isCorrect) {
        if (isCorrect) {
            this.clearMessageLabel();
            this.hitButton.setGraphic(drawCross(Color.valueOf("#38475f")));
        } else {
            this.messageLabel.setText(NanogramMessage.ERROR.toString());
            this.hitButton.setStyle("-fx-background-color: #ff4443");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRowHints(final List<List<Integer>> rowHints) {
        this.setHints(this.rowHints, rowHints, true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setColumnHints(final List<List<Integer>> columnHints) {
        this.setHints(this.columnHints, columnHints, false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateLives(final int actualLives) {
        final String live = "Vite: ";
        this.livesLabel.setText(live + actualLives);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void disableAllCells() {
        this.boardGrid.getChildren().stream()
                .filter(node -> node instanceof Button)
                .forEach(node -> node.setDisable(true));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fillRemainingCellsWithCrosses() {
        this.boardButtons.forEach(button -> button.setGraphic(drawCross(Color.valueOf("#38475f"))));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showResult(final Pair<String, Integer> result) {
        this.messageLabel.setText(NanogramMessage.END.getFormattedMessage(result.getFirst(), result.getSecond()));

        final Button returnButton = new Button("Torna al gioco principale");
        returnButton.setStyle("-fx-font-size: 18pt;");
        returnButton.setOnAction(e -> {
            try {
                this.getMainView().setBoardScene();
            } catch (IOException ex) {
                final Logger log = Logger.getLogger(NanogramViewImpl.class.getName());
                log.fine(ex.getMessage());
            }
        });

        pane.setBottom(returnButton);
        BorderPane.setMargin(returnButton, new Insets(MARGIN));
        BorderPane.setAlignment(returnButton, Pos.CENTER);
    }

    private void setHints(final GridPane grid, final List<List<Integer>> hintsList, final boolean isRowHints) {
        grid.getChildren().clear();
        final int numLines = hintsList.size();

        for (int line = 0; line < numLines; line++) {
            final List<Integer> hints = hintsList.get(line);
            final int numHints = hints.size();

            for (int i = 0; i < numHints; i++) {
                final Label hintLabel = new Label(String.valueOf(hints.get(i)));
                hintLabel.getStyleClass().add("hint-label");
                hintLabel.setStyle("-fx-font-size: 24pt;");
                hintLabel.setAlignment(Pos.CENTER);
                GridPane.setHalignment(hintLabel, javafx.geometry.HPos.CENTER);

                if (isRowHints) {
                    grid.add(hintLabel, grid.getColumnCount() - numHints + i, line);
                } else {
                    grid.add(hintLabel, line, grid.getRowCount() - numHints + i);
                }
            }
        }
    }

    private void clearMessageLabel() {
        this.messageLabel.setText(" ");
    }

    private SVGPath drawCross(final Color color) {
        final String path = "M18.8,16l5.5-5.5c0.8-0.8,0.8-2,0-2.8l0,0C24,7.3,23.5,7,23,7"
                + "c-0.5,0-1,0.2-1.4,0.6L16,13.2l-5.5-5.5c-0.8-0.8-2.1-0.8-2.8,0C7.3,8,7,8.5,7,"
                + "9.1s0.2,1,0.6,1.4l5.5,5.5l-5.5,5.5C7.3,21.9,7,22.4,7,23c0,0.5,0.2,1,0.6,1.4"
                + "C8,24.8,8.5,25,9,25c0.5,0,1-0.2,1.4-0.6l5.5-5.5l5.5,5.5c0.8,0.8,2.1,0.8,2.8,"
                + "0c0.8-0.8,0.8-2.1,0-2.8L18.8,16z";
        final SVGPath svgPath = new SVGPath();
        svgPath.setScaleX(SCALE);
        svgPath.setScaleY(SCALE);
        svgPath.setContent(path);
        svgPath.setFill(color);
        return svgPath;
    }
}
