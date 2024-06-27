package it.unibo.mparty.controller.minigames.memoryCard;

import it.unibo.mparty.model.minigames.memoryCard.api.MemoryCardModel;
import it.unibo.mparty.model.minigames.memoryCard.impl.MemoryCardModelImpl;
import it.unibo.mparty.view.minigames.memoryCard.MemoryCardView;

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
            this.view.setCardStatus(index,false);
            this.view.setCardType(index, this.model.getCards().get(index).getName());
        } else {
            if (this.model.isDone()) {
                this.view.showResult(this.model.getResults());
            } else {
                this.updateGameView();
            }
        }
    }

    @Override
    public void setUpGame() {
        final int n = this.model.getCards().size();
        for(int i = 0; i < n; i++ ){
            this.view.addCard(this.model.getCards().get(i).getName());
        }
    }

    private void updateGameView(){
        final var guessed  = this.model.guessedCardsType();
        for(var e : this.model.getCards().entrySet()){
            var type = e.getValue();
            var i = e.getKey();
            if(guessed.contains(type)){
                this.view.setCardType(i,type.getName());
                this.view.setCardStatus(i,false);
            } else {
                this.view.setCardType(i, "");
                this.view.setCardStatus(i,true);
            }
        }
    }
}
