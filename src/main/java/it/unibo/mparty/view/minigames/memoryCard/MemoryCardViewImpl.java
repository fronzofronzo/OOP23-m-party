package it.unibo.mparty.view.minigames.memoryCard;

import it.unibo.mparty.controller.minigames.memoryCard.MemoryCardController;
import it.unibo.mparty.controller.minigames.memoryCard.MemoryCardControllerImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

import javax.swing.*;

public class MemoryCardViewImpl implements MemoryCardView{

    private final MemoryCardController controller = new MemoryCardControllerImpl(this);

    @FXML
    private FlowPane cardsPane;

    @FXML
    private Button controlButton;

    @FXML
    private Label textLabel;

    @Override
    public void disableButton(int index) {

    }

    @Override
    public void setTextButton(int index, String text) {
        final Button bt = (Button)this.cardsPane.getChildren().get(index);
        bt.setText(text);
    }

    @Override
    public void showResult() {

    }

    @FXML
    private void startGame(ActionEvent event){
        final Button bt = (Button)event.getSource();
        final int n = this.controller.getCardsNumber();
        for(int i = 0; i < n; i ++){
            final Button newButton = new Button(this.controller.getCardName(i));
            newButton.setOnAction(this::tryCard);
            this.cardsPane.getChildren().add(newButton);
        }
        bt.setText("Pronto !");
        bt.setOnAction(e -> this.hideCards() );
        this.textLabel.setText("Quando si è pronti, spingere il pulsante 'Pronto' ");
    }

    private void hideCards(){
        this.cardsPane.getChildren().stream().map(e -> (Button)e).forEach(b -> b.setText(""));
    }

    private void tryCard(ActionEvent e){
        this.controller.selectCard(this.cardsPane.getChildren().indexOf((Button)e.getSource()));

    }

}
