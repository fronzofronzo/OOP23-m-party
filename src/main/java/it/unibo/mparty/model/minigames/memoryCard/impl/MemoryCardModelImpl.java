package it.unibo.mparty.model.minigames.memoryCard.impl;

import it.unibo.mparty.model.minigames.memoryCard.api.MemoryCardModel;
import it.unibo.mparty.model.player.Position;

import java.util.Map;
import java.util.Set;

public class MemoryCardModelImpl implements MemoryCardModel {

    private final Map<Position, CardType> cards;
    private final Set<CardType> guessed;
    private int mistakesNumber;

    @Override
    public void firstClick(Position card) {

    }

    @Override
    public void secondClick(Position card) {

    }

    @Override
    public void addMistake() {

    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public int getResults() {
        return 0;
    }
}
