package it.unibo.mparty.view.minigames.memoryCard;

import it.unibo.mparty.controller.minigames.memoryCard.MemoryCardController;
import it.unibo.mparty.controller.minigames.memoryCard.MemoryCardControllerImpl;
import it.unibo.mparty.view.AbstractSceneView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

public class MemoryCardViewImpl extends AbstractSceneView implements MemoryCardView{

    private final MemoryCardController controller = new MemoryCardControllerImpl(this);

    @FXML
    private FlowPane cardsPane;

    @FXML
    private Button controlButton;

    @FXML
    private Label textLabel;

    @Override
    public void setCardStatus(int index, boolean status) {
        ((Button)this.cardsPane.getChildren().get(index)).setDisable(!status);
    }

    @Override
    public void setCardType(int index, String type) {
        final Button bt = (Button)this.cardsPane.getChildren().get(index);
        bt.setText(type);
    }

    @Override
    public void showResult(int n) {
        this.textLabel.setText("Il giocatore ha guadagnato " + String.valueOf(n) + " monete");
        this.controlButton.setDisable(false);
        // this.getController.endMinigame(n)
        this.controlButton.setText("Lascia il minigioco ");
        this.controlButton.setOnAction(e -> {
            // this.getView.setScene(mainScene.xml)
        });
    }

    @Override
    public void addCard(String text) {
        final Button bt = new Button(text);
        bt.setOnAction(this::tryCard);
        bt.setPrefSize(100,100);
        bt.setDisable(true);
        bt.setStyle("-fx-opacity: 1.0; ");
        this.cardsPane.getChildren().add(bt);
    }

    @Override
    public void setMistakesNumber(int n) {
        this.textLabel.setText("Errori: " + String.valueOf(n));
    }

    @FXML
    private void startGame(ActionEvent event){
        final Button bt = (Button)event.getSource();
        this.controller.setUpGame();
        bt.setText("Pronto !");
        bt.setOnAction(this::hideCards);
        this.textLabel.setText("Quando si e' pronti, spingere il pulsante 'Pronto' ");
    }

    private void hideCards(ActionEvent event){
        this.cardsPane.getChildren().stream().map(e -> (Button)e).forEach(b -> {
            b.setText("");
            b.setDisable(false);
        });
        ((Button)event.getSource()).setDisable(true);
        this.textLabel.setText("Errori: 0");
    }

    private void tryCard(ActionEvent e){
        this.controller.selectCard(this.cardsPane.getChildren().indexOf((Button)e.getSource()));
    }

}
