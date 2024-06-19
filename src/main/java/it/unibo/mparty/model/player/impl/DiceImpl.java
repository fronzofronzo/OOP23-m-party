package it.unibo.mparty.model.player.impl;

import it.unibo.mparty.model.player.api.Dice;

public class DiceImpl implements Dice {

    final int numberOfAttempts = 1;
    final int maxNumber = 6;
    final int minNumber = 1;

    @Override
    public void setNumberOfAttempts(int num) {

    }

    @Override
    public void setMaxNumber(int num) {

    }

    @Override
    public void setMinNumber(int num) {

    }

    @Override
    public int generateNumber() {
        return 0;
    }
}
