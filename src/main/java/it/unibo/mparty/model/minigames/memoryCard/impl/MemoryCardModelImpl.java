package it.unibo.mparty.model.minigames.memoryCard.impl;

import it.unibo.mparty.model.minigames.memoryCard.api.MemoryCardModel;
import it.unibo.mparty.model.player.Position;
import java.util.*;

public class MemoryCardModelImpl implements MemoryCardModel {

    private static final int MAX_MISTAKES = 3;
    private static final double SCORE_MULTIPLIER = 1.5;
    private static final int NOT_SELECTED = -1;

    private final Map<Integer, CardType> cards;
    private final Set<CardType> guessed;
    private int selected = NOT_SELECTED ;
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
    public void flip(int card) {
        if(selected == NOT_SELECTED){
            selected = card;
        } else {
            if(cards.get(selected) == cards.get(card)){
                guessed.add(cards.get(card));
            }
            selected = NOT_SELECTED;
        }
    }

    @Override
    public void addMistake() {
        this.mistakesNumber++;
    }

    @Override
    public boolean isDone() {
        return mistakesNumber == MAX_MISTAKES || guessed.size() == CardType.values().length;
    }

    @Override
    public int getResults() {
        return (int)(guessed.size() * SCORE_MULTIPLIER);
    }

    @Override
    public Map<Integer, CardType> getCards() {
        return Map.copyOf(cards);
    }

    @Override
    public Set<CardType> guessedCardsType() {
        return Set.copyOf(guessed);
    }

}
