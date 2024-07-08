package it.unibo.mparty.model.minigames.secretCode.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import it.unibo.mparty.model.minigames.secretCode.api.SecretCodePlayer;
import it.unibo.mparty.model.minigames.secretCode.util.SecretCodeColors;

public class SecretCodePlayerImpl implements SecretCodePlayer{

    private static final  int INITIAL_POINTS = 0;

    private final String player;
    private final int soluctionSize;
    private List<SecretCodeColors> myGuess;
    private int points;


    public SecretCodePlayerImpl(String player, int soluctionSize){
        this.player = player;
        this.soluctionSize = soluctionSize;
        this.myGuess = new ArrayList<>();
        this.points = INITIAL_POINTS;
    }

    @Override
    public boolean addColor(SecretCodeColors color) {
        if (this.myGuess.size() < this.soluctionSize) {
            this.myGuess.add(color);
            return true;
        }
        return false;
    }

    @Override
    public List<SecretCodeColors> getGuess() {
        return Collections.unmodifiableList(this.myGuess);
    }

    @Override
    public String getNamePlayer() {
        return this.player;
    }

    @Override
    public int getPoints() {
        return this.points;
    }

    @Override
    public void addPoints(int points) {
        this.points += points;
    }    
}
