package it.unibo.mparty.model.minigames.memoryCard.impl;

import it.unibo.mparty.model.minigames.memoryCard.api.MemoryCardModel;
import it.unibo.mparty.model.player.Position;

import java.util.*;

public class MemoryCardModelImpl implements MemoryCardModel {

    private final Map<Integer, CardType> cards;
    private final Set<CardType> guessed;
    private int mistakesNumber;

    public MemoryCardModelImpl(){
        this.cards = new HashMap<>();
        this.guessed = new HashSet<>();
        this.mistakesNumber = 0;
        final int size = CardType.values().length;
        final Random random = new Random();
        for(var type : CardType.values()){
            var i = random.nextInt(size);
            while (cards.containsKey(i)){
                i = random.nextInt(size);
            }
            cards.put(i, type);
            var j = random.nextInt(size);
            while (cards.containsKey(j)){
                j = random.nextInt(size);
            }
            cards.put(j,type);
        }
    }

    @Override
    public void firstClick(int card) {

    }

    @Override
    public void secondClick(int card) {

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
