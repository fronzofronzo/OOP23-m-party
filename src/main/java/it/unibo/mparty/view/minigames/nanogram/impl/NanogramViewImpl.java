package it.unibo.mparty.view.minigames.nanogram.impl;

import it.unibo.mparty.controller.minigames.nanogram.api.NanogramController;
import it.unibo.mparty.controller.minigames.nanogram.impl.NanogramControllerImpl;
import it.unibo.mparty.model.minigames.nanogram.util.Difficulty;
import it.unibo.mparty.view.minigames.nanogram.util.StatusMessage;
import it.unibo.mparty.view.AbstractSceneView;
import it.unibo.mparty.view.minigames.nanogram.api.NanogramView;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;

import java.util.*;


/**
 * Implementation of the {@link NanogramView} interface representing the view for a Nanogram game.
 * This class handles the UI components and interactions for the Nanogram game.
 */
public class NanogramViewImpl extends AbstractSceneView implements NanogramView {

    private static final int PREF_SIZE = 300;
    private static final double MARGIN = 30;

    @FXML
    private BorderPane pane;

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

    private NanogramController controller;

    private Set<Button> boardButtons;

    private Button hitButton;
    private final EventHandler<MouseEvent> handleCellClicked = event -> {
        this.hitButton = (Button) event.getSource();
        this.controller.checkCell(GridPane.getRowIndex(hitButton), GridPane.getColumnIndex(hitButton));
        //clicked.setFocusTraversable(false);
        //clicked.setDisable(true);
        boardButtons.remove(hitButton); //todo: check
    };

    @FXML
    public void initialize() {
        this.boardGrid = new GridPane();
        this.boardGrid.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        GridPane.setMargin(boardGrid, new Insets(MARGIN));
        pane.setCenter(this.boardGrid);
        ToggleGroup toggleGroup = new ToggleGroup();
        this.filledButton.setToggleGroup(toggleGroup);
        this.crossButton.setToggleGroup(toggleGroup);
        this.filledButton.setSelected(true);
        this.filledButton.setOnAction(event -> this.controller.setFillState(true));
        this.crossButton.setOnAction(event -> this.controller.setFillState(false));

        this.initializeGame(Difficulty.SIMPLE); //todo: cancellare questa riga quando avete unito tutti i parti, questo deve essere chiamato dal gioco principale, vedi commento sotto
    }

    // todo: questo metodo deve essere chiamato dal gioco principale, passando come argomento la difficoltà per inizializzare il gioco
    public void initializeGame(Difficulty difficulty) {
        this.controller = new NanogramControllerImpl(this, difficulty);
    }

    @Override
    public void initGrid(final int size) {
        this.boardButtons = new HashSet<>();
        this.boardGrid.setHgap(2);
        this.boardGrid.setVgap(2);
        //this.boardGrid.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                final Button button = new Button();
                button.setPrefSize(PREF_SIZE, PREF_SIZE);
                button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                button.setOnMouseClicked(handleCellClicked);
                //button.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
                this.boardButtons.add(button);
                this.boardGrid.add(button, col, row);
            }
        }
    }

    private void setHints(final GridPane grid, final List<List<Integer>> hintsList, final boolean isRowHints) {
        for (List<Integer> line : hintsList) {
            for (int i = 0; i < line.size(); i++) {
                final Label hintLabel = new Label(String.valueOf(line.get(i)));
                hintLabel.setStyle("-fx-font-size: 24pt");
                hintLabel.setAlignment(Pos.CENTER);
                GridPane.setHalignment(hintLabel, javafx.geometry.HPos.CENTER);
                if (isRowHints) {
                    hintLabel.setPrefSize(0, PREF_SIZE);
                    hintLabel.setMaxSize(PREF_SIZE, Double.MAX_VALUE);
                    grid.add(hintLabel, i, hintsList.indexOf(line));
                } else {
                    hintLabel.setPrefSize(PREF_SIZE, 0);
                    hintLabel.setMaxSize(Double.MAX_VALUE, PREF_SIZE);
                    grid.add(hintLabel, hintsList.indexOf(line), i);
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRowHints(final List<List<Integer>> rowHints) {
        System.out.println("ROW HINTS: \n " + rowHints);
        final GridPane rowHintsGrid = new GridPane();
        this.pane.setLeft(rowHintsGrid);
        this.setHints(rowHintsGrid, rowHints, true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setColumnHints(final List<List<Integer>> columnHints) {
        final GridPane columnHintsGrid = new GridPane();
        ((VBox) this.pane.getTop()).getChildren().add(columnHintsGrid);
        this.setHints(columnHintsGrid, columnHints, false);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void updateLives(final int actualLives) {
        final String LIVES = "Vite: ";
        this.livesLabel.setText(LIVES + actualLives);
    }

    /**
     * {@inheritDoc}
     */
//    @Override
//    public void clearMessageLabel() {
//        this.messageLabel.setText(" ");
//    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void displayStatusMessage(final StatusMessage message) {
        this.messageLabel.setText(message.toString());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void disableAllCells() {
        this.boardGrid.getChildren().stream()
                .filter(node -> node instanceof Pane)
                .forEach(node -> node.setDisable(true));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fillRemainingCellsWithCrosses() {
        //todo: check
        boardButtons.forEach(button -> button.setStyle("-fx-background-color: #ff4443"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fillCell(boolean isCorrect) {
        this.hitButton.setStyle(isCorrect ? "-fx-background-color: #38475f" : "-fx-background-color: #ff4443");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void crossCell(boolean isCorrect) {
        this.hitButton.setGraphic(drawCross(Color.valueOf(isCorrect ? "#38475f" : "#ff4443")));
    }

    private SVGPath drawCross(final Color color) {
        final String path = "M18.8,16l5.5-5.5c0.8-0.8,0.8-2,0-2.8l0,0C24,7.3,23.5,7,23,7c-0.5,0-1,0.2-1.4,0.6L16,13.2l-5.5-5.5  c-0.8-0.8-2.1-0.8-2.8,0C7.3,8,7,8.5,7,9.1s0.2,1,0.6,1.4l5.5,5.5l-5.5,5.5C7.3,21.9,7,22.4,7,23c0,0.5,0.2,1,0.6,1.4  C8,24.8,8.5,25,9,25c0.5,0,1-0.2,1.4-0.6l5.5-5.5l5.5,5.5c0.8,0.8,2.1,0.8,2.8,0c0.8-0.8,0.8-2.1,0-2.8L18.8,16z";
        final SVGPath svgPath = new SVGPath();
        svgPath.setScaleX(2.5);
        svgPath.setScaleY(2.5);
        svgPath.setContent(path);
        svgPath.setFill(color);
        return svgPath;
    }

}
