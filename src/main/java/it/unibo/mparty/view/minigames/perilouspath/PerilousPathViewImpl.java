package it.unibo.mparty.view.minigames.perilouspath;

import it.unibo.mparty.controller.GameController;
import it.unibo.mparty.controller.minigames.perilouspath.PerilousPathController;
import it.unibo.mparty.controller.minigames.perilouspath.PerilousPathControllerImpl;
import it.unibo.mparty.model.minigames.perilouspath.api.AbstractPosition;
import it.unibo.mparty.model.minigames.perilouspath.api.PerilousPath;
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

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * implementation of {@link PerilousPathView}.
 */
public final class PerilousPathViewImpl extends AbstractSceneView implements PerilousPathView {

    /**
     * height of the image.
     */
    private static final int FIT_HEIGHT = 50;
    /**
     * width of the image.
     */
    private static final int FIT_WIDTH = 50;

    private static final String PATH = "text/perilousPathTutorial.txt";
    @FXML
    private GridPane myGridPane;

    @FXML
    private Button startButton;

    @FXML
    private Label gameLabel;

    private Button button;
    private static final int SIZE = 8;
    private final Image bombImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/BombImage.png")));
    private final Image ballImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/pallaCalcio.png")));
    private final PerilousPathController observer = new PerilousPathControllerImpl(this);

    private final EventHandler<MouseEvent> buttonClicked = e -> {
        this.button = (Button) e.getSource();
        final var pos = this.buttonPosition(this.button);
        this.observer.hit(pos);
    };

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUpView(final List<AbstractPosition> balls, final List<AbstractPosition> bombs) {
        for (final var child : this.myGridPane.getChildren()) {
            final var pos = this.buttonPosition(child);
            if (balls.stream().anyMatch(b -> b.samePosition(pos)) && child instanceof Button) {
                this.setImage((Button) child, new ImageView(ballImage));
            }
            if (bombs.stream().anyMatch(b -> b.samePosition(pos)) && child instanceof Button) {
                this.setImage((Button) child, new ImageView(bombImage));
            }
        }
        this.myGridPane.setDisable(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void hideBombs(final List<AbstractPosition> bombs) {
        for (final var child : this.myGridPane.getChildren()) {
            final var pos = this.buttonPosition(child);
            if (bombs.stream().anyMatch(b -> b.samePosition(pos)) && child instanceof Button) {
                ((Button) child).setGraphic(null);
            }
        }
        this.myGridPane.setDisable(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showBombs(final List<AbstractPosition> bombs) {
        for (final var child : this.myGridPane.getChildren()) {
            final var pos = this.buttonPosition(child);
            if (bombs.stream().anyMatch(b -> b.samePosition(pos)) && child instanceof Button) {
                this.setImage((Button) child, new ImageView(bombImage));
            }
        }
        this.myGridPane.setDisable(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void hitTile(final PerilousPath.Type type) {
        switch (type) {
            case PATH -> {
                button.setStyle("-fx-background-color: #f3f5f8;");
                this.gameLabel.setText("MOSSA VALIDA");
            }
            case WRONG -> this.gameLabel.setText("MOSSA NON VALIDA");
            default -> button.setText("");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handleStartButton(final ActionEvent e) throws InterruptedException {
        this.gridCreation();
        this.observer.setUpGame();
        this.startButton.setDisable(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(final GameView view, final GameController controller) {
        super.init(view, controller);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showResult(final Pair<String, Integer> result) {
        this.gameLabel.setText("il giocatore : " + result.getFirst() + " ha vinto " + result.getSecond() + " coins");
        this.startButton.setText("RETURN");
        this.startButton.setDisable(false);
        this.startButton.setOnAction(e -> {
            try {
                this.getMainView().setBoardScene();
            } catch (IOException ex) {
                final Logger logger = Logger.getAnonymousLogger();
                logger.log(Level.SEVERE, ex.toString());
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startMinigame(final List<String> players) {
        this.observer.initGame(players);
        this.showTutorial(this.gameLabel);
    }

    /**
     * method that manages the creation of a PathPosition object.
     *
     * @param child the child that is in that position in the grid
     * @return the PathPosition of the child in the grid
     */
    private AbstractPosition buttonPosition(final Node child) {
        final var x = GridPane.getRowIndex(child) == null ? 0 : GridPane.getRowIndex(child);
        final var y = GridPane.getColumnIndex(child) == null ? 0 : GridPane.getColumnIndex(child);
        return new PathPosition(x, y, SIZE);
    }

    /**
     * method for creating a grid dynamically.
     */
    private void gridCreation() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                final Button button = new Button();
                button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                button.setOnMouseClicked(buttonClicked);
                this.myGridPane.add(button, i, j);
            }
        }
    }

    /**
     * method for setting an image in a button.
     *
     * @param button    the button in which the image needs to be set
     * @param imageView the image that will be set in the button
     */
    private void setImage(final Button button, final ImageView imageView) {
        button.setGraphic(imageView);
        imageView.setFitHeight(FIT_HEIGHT);
        imageView.setFitWidth(FIT_WIDTH);
        imageView.setPreserveRatio(false);
    }

    private void showTutorial(final Label label) {
        label.setWrapText(true);
        final InputStream input = getClass().getClassLoader().getResourceAsStream(PATH);
        if (input != null) {
            String text = "";
            try {
                text = new String(input.readAllBytes(), StandardCharsets.UTF_8);
            } catch (IOException e) {
                final Logger logger = Logger.getAnonymousLogger();
                logger.log(Level.SEVERE, e.toString());
            }
            label.setText(text);
        }
    }
}
