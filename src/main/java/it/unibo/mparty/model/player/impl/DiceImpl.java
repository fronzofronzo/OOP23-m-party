package it.unibo.mparty.model.player.impl;

import it.unibo.mparty.model.player.api.Dice;

public class DiceImpl implements Dice {

    private static final int ATTEMPTS = 1;
    private static final int MAX_NUMBER = 6;
    private static final int MIN_NUMBER = 1;

    private int numberOfAttempts = 1;
    private int maxNumber = 6;
    private int minNumber = 1;

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
    public int generateNumber() {
        return 0;
    }
}
