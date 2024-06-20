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

    }
}
