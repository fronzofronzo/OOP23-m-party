package it.unibo.mparty.model.player.impl;

import it.unibo.mparty.model.player.api.Dice;

import java.util.Random;

public class DiceImpl implements Dice {

    private static final int ATTEMPTS = 1;
    private static final int MAX_NUMBER = 6;
    private static final int MIN_NUMBER = 1;

    private int numberOfAttempts = ATTEMPTS;
    private int maxNumber = MAX_NUMBER;
    private int minNumber = MIN_NUMBER;
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
    public int generateNumber() {
        int number = 0;
        for(int i = 0; i < numberOfAttempts; i++){
            number +=  random.nextInt(minNumber,maxNumber+1);
        }
        return number;
    }
}
