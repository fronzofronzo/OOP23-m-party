package it.unibo.mparty.model.minigames.secretCode.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import it.unibo.mparty.model.minigames.secretCode.api.SecretCodePlayer;
import it.unibo.mparty.model.minigames.secretCode.util.SecretCodeColors;

public class SecretCodePlayerImpl implements SecretCodePlayer{

    private static final  int INITIAL_POINTS = 0;

    private final String player;
    private List<SecretCodeColors> myGuess;
    private String message = "";
    private int points;
    private final int soluctionSize;


    public SecretCodePlayerImpl(String player, int soluctionSize){
        this.player = player;
        this.myGuess = new ArrayList<>();
        this.points = INITIAL_POINTS;
        this.soluctionSize = soluctionSize;
    }

    @Override
    public void addColor(SecretCodeColors color) {
        if (this.myGuess.size() < this.soluctionSize) {
            this.myGuess.add(color);
        }
    }

    @Override
    public void removeColor() {
        if (this.myGuess.isEmpty()) {

        } else {
            this.myGuess.removeLast();
        }
    }

    @Override
    public List<SecretCodeColors> getGuess() {
        if (this.myGuess.size() == this.soluctionSize) {
            return Collections.unmodifiableList(this.myGuess);
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public String getMessage() {
        return this.message;
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

    @Override
    public List<SecretCodeColors> getCurrentGuess() {
        return Collections.unmodifiableList(this.myGuess);
    }
    
}
