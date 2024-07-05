package it.unibo.mparty.model.player.impl;

import it.unibo.mparty.model.player.api.Dice;
import it.unibo.mparty.utilities.Pair;

import java.util.Random;

/**
 * This class implements a {@link Dice}. When the player roll the dice, it stores
 * the result.
 */
public class DiceImpl implements Dice {

    private static final int ATTEMPTS = 1;
    private static final int MAX_NUMBER = 6;
    private static final int MIN_NUMBER = 1;

    private int numberOfAttempts = ATTEMPTS;
    private int maxNumber = MAX_NUMBER;
    private int minNumber = MIN_NUMBER;
    private int result;
    private Random random;

    /**
     * Creates a new instance od {@link Dice}
     */
    public DiceImpl(){
        this.random = new Random();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNumberOfAttempts(int num) {
        this.numberOfAttempts = num;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMaxNumber(int num) {
        this.maxNumber = num;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMinNumber(int num) {
        this.minNumber = num;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void rollDice() {
        this.result = random.nextInt(minNumber, maxNumber);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getResult() {
        return this.result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pair<Integer, Integer> getBounds() {
        return new Pair<>(this.minNumber, this.maxNumber);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNumOfAttempts() {
        return this.numberOfAttempts;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resetDice() {
        numberOfAttempts = ATTEMPTS;
        maxNumber = MAX_NUMBER;
        minNumber = MIN_NUMBER;
    }

}
