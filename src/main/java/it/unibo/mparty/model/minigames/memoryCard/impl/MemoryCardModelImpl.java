package it.unibo.mparty.model.minigames.memoryCard.impl;

import it.unibo.mparty.model.minigames.MinigameType;
import it.unibo.mparty.model.minigames.memoryCard.api.MemoryCardModel;
import it.unibo.mparty.utilities.Pair;
import it.unibo.mparty.utilities.Position;
import java.util.*;

public class MemoryCardModelImpl implements MemoryCardModel {

    private static final int MAX_MISTAKES = 3;
    private static final double SCORE_MULTIPLIER = 1.5;
    private static final int NOT_SELECTED = -1;
    private static final int FIRST = 0;
    private static final String NAME = "memoryCard";

    private final Map<Integer, CardType> cards;
    private final Set<CardType> guessed;
    private String player = null;
    private int selected = NOT_SELECTED ;
    private int mistakesNumber = 0;

    public MemoryCardModelImpl(){
        this.cards = new HashMap<>();
        this.guessed = new HashSet<>();
        final int size = CardType.values().length * 2;
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
    public boolean flip(int card) {
        if(selected == NOT_SELECTED){
            selected = card;
            return true;
        } else {
            if(cards.get(selected) == cards.get(card)){
                guessed.add(cards.get(card));
            } else {
                this.mistakesNumber++;
            }
            selected = NOT_SELECTED;
            return false;
        }
    }

    @Override
    public Map<Integer, CardType> getCards() {
        return Map.copyOf(cards);
    }

    @Override
    public Set<CardType> guessedCardsType() {
        return Set.copyOf(guessed);
    }

    @Override
    public int getMistakes() {
        return this.mistakesNumber;
    }

    @Override
    public Pair<String, Integer> getResult() {
        return new Pair<>(player, (int)(guessed.size() * SCORE_MULTIPLIER));
    }

    @Override
    public void setUpPlayers(List<String> players) {
        player = players.get(FIRST);
    }

    @Override
    public boolean isOver() {
        return mistakesNumber == MAX_MISTAKES || guessed.size() == CardType.values().length;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public MinigameType getType() {
        return MinigameType.SINGLE_PLAYER;
    }
}
