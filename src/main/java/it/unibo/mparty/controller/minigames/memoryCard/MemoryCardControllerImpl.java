package it.unibo.mparty.controller.minigames.memoryCard;

import it.unibo.mparty.model.minigames.memoryCard.api.MemoryCardModel;
import it.unibo.mparty.model.minigames.memoryCard.impl.CardType;
import it.unibo.mparty.model.minigames.memoryCard.impl.MemoryCardModelImpl;
import it.unibo.mparty.view.minigames.memoryCard.MemoryCardView;

import java.util.Map;

public class MemoryCardControllerImpl implements MemoryCardController{

    private final MemoryCardModel model;
    private final MemoryCardView view;

    public MemoryCardControllerImpl(MemoryCardView view){
        this.model = new MemoryCardModelImpl();
        this.view = view;
    }


    @Override
    public void selectCard(int index) {
        if(this.model.flip(index)){
            this.view.disableButton(index);
            this.view.setTextButton(index, this.model.getCards().get(index).getName());
        } else {
            if (this.model.isDone()) {
                this.view.showResult();
            } else {
                this.updateGameView();
            }
        }
    }

    @Override
    public int getCardsNumber() {
        return this.model.getCards().size();
    }

    @Override
    public String getCardType(int i) {
        return this.model.getCards().get(i).getName();
    }

    private void updateGameView(){
        final var guessed  = this.model.guessedCardsType();
        for(var e : this.model.getCards().entrySet()){
            var type = e.getValue();
            var i = e.getKey();
            if(guessed.contains(type)){
                this.view.setTextButton(i,type.getName());
                this.view.disableButton(i);
            } else {
                this.view.setTextButton(i, "");
            }
        }
    }
}
