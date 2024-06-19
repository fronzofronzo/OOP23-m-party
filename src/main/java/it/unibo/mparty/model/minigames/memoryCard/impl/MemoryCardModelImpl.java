package it.unibo.mparty.model.minigames.memoryCard.impl;

import it.unibo.mparty.model.minigames.memoryCard.api.MemoryCardModel;
import it.unibo.mparty.model.player.Position;

import java.util.*;

public class MemoryCardModelImpl implements MemoryCardModel {

    private final Map<Position, CardType> cards;
    private final Set<CardType> guessed;
    private int mistakesNumber;

    public MemoryCardModelImpl(){
        this.cards = new HashMap<>();
        this.guessed = new HashSet<>();
        this.mistakesNumber = 0;
        final Random random = new Random();
        for(var type : CardType.values()){

        }
    }

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
