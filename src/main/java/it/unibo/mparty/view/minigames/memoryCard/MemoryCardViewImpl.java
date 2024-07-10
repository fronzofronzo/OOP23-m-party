package it.unibo.mparty.view.minigames.memoryCard;

import it.unibo.mparty.controller.minigames.memoryCard.MemoryCardController;
import it.unibo.mparty.controller.minigames.memoryCard.MemoryCardControllerImpl;
import it.unibo.mparty.utilities.Pair;
import it.unibo.mparty.view.AbstractSceneView;
import it.unibo.mparty.view.minigames.MinigameView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;

/**
 * This class provides a graphic implementation for {@link MemoryCardView}.
 * This class uses the graphic library of JavaFX to implement the GUI.
 */
public class MemoryCardViewImpl extends AbstractSceneView implements MemoryCardView {

    private static final int FONT_SIZE = 15;
    private static final int PREF_BUTTON_SIZE = 100;
    private static final String TUTORIAL_PATH = "src/main/resources/text/memoryCardTutorial.txt";

    private final MemoryCardController controller = new MemoryCardControllerImpl(this);

    @FXML
    private FlowPane cardsPane;

    @FXML
    private Button controlButton;

    @FXML
    private Label textLabel;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCardStatus(final int index, final boolean status) {
        ((Button) this.cardsPane.getChildren().get(index)).setDisable(!status);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCardType(final int index, final String type) {
        final Button bt = (Button) this.cardsPane.getChildren().get(index);
        bt.setText(type);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addCard(final String text) {
        final Button bt = new Button(text);
        bt.setOnAction(this::tryCard);
        bt.setPrefSize(PREF_BUTTON_SIZE, PREF_BUTTON_SIZE);
        bt.setDisable(true);
        bt.setStyle("-fx-opacity: 1.0; ");
        bt.setFont(new Font("Segoe UI Light", FONT_SIZE));
        bt.setVisible(false);
        this.cardsPane.getChildren().add(bt);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMistakesNumber(final int n) {
        this.textLabel.setText("Errori: " + String.valueOf(n));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showResult(final Pair<String, Integer> result) {
        this.textLabel.setText(result.getFirst() + " ha guadagnato " + String.valueOf(result.getSecond()) + " monete.");
        this.controlButton.setOnAction(e -> {
            this.controller.endGame();
        });
        this.controlButton.setText("Torna al gioco principale");
        this.controlButton.setDisable(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startMinigame(final List<String> players) {
        this.controller.initGame(players);
        this.showTutorial(textLabel);
    }

    @FXML
    private void startGame(final ActionEvent event) {
        final Button bt = (Button) event.getSource();
        this.cardsPane.getChildren().forEach(b -> b.setVisible(true));
        bt.setText("Pronto !");
        bt.setOnAction(this::hideCards);
        this.textLabel.setText("Quando si e' pronti, spingere il pulsante 'Pronto'");
    }

    private void hideCards(final ActionEvent event) {
        this.cardsPane.getChildren().stream().map(e -> (Button) e).forEach(b -> {
            b.setText("");
            b.setDisable(false);
        });
        ((Button) event.getSource()).setDisable(true);
        this.textLabel.setText("Errori: 0");
    }

    private void tryCard(final ActionEvent e) {
        this.controller.selectCard(this.cardsPane.getChildren().indexOf((Button) e.getSource()));
    }

    private void showTutorial(final Label label) {
        try {
            this.textLabel.setText(new String(Files.readAllBytes(Paths.get(TUTORIAL_PATH))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
