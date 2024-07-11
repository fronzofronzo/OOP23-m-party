package it.unibo.mparty.model.minigames.memoryCard.impl;

import it.unibo.mparty.model.minigames.MinigameType;
import it.unibo.mparty.model.minigames.memoryCard.api.MemoryCardModel;
import it.unibo.mparty.utilities.Pair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.Random;

/**
 * This class provides an implementation of {@link MemoryCardModel} interface.
 */
public class MemoryCardModelImpl implements MemoryCardModel {

    private static final int MAX_MISTAKES = 3;
    private static final double SCORE_MULTIPLIER = 1.5;
    private static final int NOT_SELECTED = -1;
    private static final int FIRST = 0;
    private static final String NAME = "memorycard";
    private static final Random RANDOM = new Random();

    private final Map<Integer, CardType> cards;
    private final Set<CardType> guessed;
    private String player;
    private int selected = NOT_SELECTED;
    private int mistakesNumber;

    /**
     * Constructor of a new instance of {@link MemoryCardModel}.
     */
    public MemoryCardModelImpl() {
        this.cards = new HashMap<>();
        this.guessed = new HashSet<>();
        final int size = CardType.values().length * 2;
        for (final var type : CardType.values()) {
            var i = RANDOM.nextInt(size);
            while (cards.containsKey(i)) {
                i = RANDOM.nextInt(size);
            }
            cards.put(i, type);
            var j = RANDOM.nextInt(size);
            while (cards.containsKey(j)) {
                j = RANDOM.nextInt(size);
            }
            cards.put(j, type);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean flip(final int card) {
        if (selected == NOT_SELECTED) {
            selected = card;
            return true;
        } else {
            if (cards.get(selected) == cards.get(card)) {
                guessed.add(cards.get(card));
            } else {
                this.mistakesNumber++;
            }
            selected = NOT_SELECTED;
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Integer, CardType> getCards() {
        return Map.copyOf(cards);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<CardType> guessedCardsType() {
        return Set.copyOf(guessed);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMistakes() {
        return this.mistakesNumber;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pair<String, Integer> getResult() {
        return new Pair<>(player, (int) (guessed.size() * SCORE_MULTIPLIER));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUpPlayers(final List<String> players) {
        player = players.get(FIRST);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isOver() {
        return mistakesNumber == MAX_MISTAKES || guessed.size() == CardType.values().length;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MinigameType getType() {
        return MinigameType.SINGLE_PLAYER;
    }
}
