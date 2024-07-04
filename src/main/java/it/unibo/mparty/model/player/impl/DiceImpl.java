package it.unibo.mparty.model.player.impl;

import it.unibo.mparty.model.player.api.Dice;
import it.unibo.mparty.utilities.Pair;

import java.util.Random;

public class DiceImpl implements Dice {

    private static final int ATTEMPTS = 1;
    private static final int MAX_NUMBER = 6;
    private static final int MIN_NUMBER = 1;

    private int numberOfAttempts = ATTEMPTS;
    private int maxNumber = MAX_NUMBER;
    private int minNumber = MIN_NUMBER;
    private int result;
    private Random random;

    public DiceImpl(){
        this.random = new Random();
    }

    @Override
    public void setNumberOfAttempts(int num) {
        this.numberOfAttempts = num;
    }

    @Override
    public void setMaxNumber(int num) {
        this.maxNumber = num;
    }

    @Override
    public void setMinNumber(int num) {
        this.minNumber = num;
    }

    @Override
    public void rollDice() {
        this.result = random.nextInt(minNumber, maxNumber);
    }

    @Override
    public int getResult() {
        return this.result;
    }

    @Override
    public Pair<Integer, Integer> getBounds() {
        return new Pair<>(this.minNumber, this.maxNumber);
    }

    @Override
    public int getNumOfAttempts() {
        return this.numberOfAttempts;
    }

}
