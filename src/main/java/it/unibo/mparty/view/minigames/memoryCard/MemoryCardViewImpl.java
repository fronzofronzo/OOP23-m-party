package it.unibo.mparty.view.minigames.memoryCard;

import it.unibo.mparty.controller.minigames.memoryCard.MemoryCardController;
import it.unibo.mparty.controller.minigames.memoryCard.MemoryCardControllerImpl;
import it.unibo.mparty.utilities.Pair;
import it.unibo.mparty.view.AbstractSceneView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import java.util.List;

/**
 * This class provides a graphic implementation for {@link MemoryCardView}.
 * This class uses the graphic library of JavaFX to implement the GUI.
 */
public class MemoryCardViewImpl extends AbstractSceneView implements MemoryCardView{

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
        bt.setPrefSize(100,100);
        bt.setDisable(true);
        bt.setStyle("-fx-opacity: 1.0; ");
        bt.setFont(new Font("Segoe UI Light", 15));
        this.cardsPane.getChildren().add(bt);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMistakesNumber(int n) {
        this.textLabel.setText("Errori: " + String.valueOf(n));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showResult(Pair<String, Integer> result) {
        this.textLabel.setText(result.getFirst() + " ha guadagnato " +  String.valueOf(result.getSecond()) + " monete.");
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
    }

    @FXML
    private void startGame(final ActionEvent event) {
        final Button bt = (Button)event.getSource();
        bt.setText("Pronto !");
        bt.setOnAction(this::hideCards);
        this.textLabel.setText("Quando si e' pronti, spingere il pulsante 'Pronto'");
    }

    private void hideCards(final ActionEvent event) {
        this.cardsPane.getChildren().stream().map(e -> (Button)e).forEach(b -> {
            b.setText("");
            b.setDisable(false);
        });
        ((Button) event.getSource()).setDisable(true);
        this.textLabel.setText("Errori: 0");
    }

    private void tryCard(final ActionEvent e) {
        this.controller.selectCard(this.cardsPane.getChildren().indexOf((Button) e.getSource()));
    }

}
