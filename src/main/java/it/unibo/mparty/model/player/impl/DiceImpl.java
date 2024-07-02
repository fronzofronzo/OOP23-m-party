package it.unibo.mparty.model.player.impl;

import it.unibo.mparty.model.player.api.Dice;

import java.util.Random;

/**
 * This class models the implementation of {@link Dice}. Each dice at first
 * is initialized with standard parameters and can be modified
 */
public class DiceImpl implements Dice {

    private static final int ATTEMPTS = 1;
    private static final int MAX_NUMBER = 6;
    private static final int MIN_NUMBER = 1;

    private int numberOfAttempts = ATTEMPTS;
    private int maxNumber = MAX_NUMBER;
    private int minNumber = MIN_NUMBER;
    private Random random;

    /**
     * Constructor of a new dice. It initialises the random variable
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
    public int generateNumber() {
        int number = 0;
        for(int i = 0; i < numberOfAttempts; i++){
            number += random.nextInt(minNumber,maxNumber+1);
        }
        return number;
    }
}
