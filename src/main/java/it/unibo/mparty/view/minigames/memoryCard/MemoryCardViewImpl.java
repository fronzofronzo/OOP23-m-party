package it.unibo.mparty.view.minigames.memoryCard;

import it.unibo.mparty.controller.minigames.memoryCard.MemoryCardController;
import it.unibo.mparty.controller.minigames.memoryCard.MemoryCardControllerImpl;
import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;

public class MemoryCardViewImpl implements MemoryCardView{

    private final MemoryCardController controller = new MemoryCardControllerImpl(this);

    @FXML
    private FlowPane cardsPane;



    @Override
    public void disableButton(int index) {

    }

    @Override
    public void setTextButton(int index, String text) {

    }

    @Override
    public void showResult() {

    }

}
