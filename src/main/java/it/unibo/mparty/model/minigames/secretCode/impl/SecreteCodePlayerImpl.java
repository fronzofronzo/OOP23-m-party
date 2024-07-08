package it.unibo.mparty.model.minigames.secretCode.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import it.unibo.mparty.model.minigames.secretCode.api.SecreteCodePlayer;

public class SecreteCodePlayerImpl implements SecreteCodePlayer{

    private final String player;
    private final List<SecretCodeColors> soluction;
    private List<SecretCodeColors> myGuess = new ArrayList<>();
    private String message = "";
    private int points;


    public SecreteCodePlayerImpl(String player, List<SecretCodeColors> soluction){
        this.player = player;
        this.soluction = soluction;
    }

    @Override
    public void addColor(SecretCodeColors color) {
        if (this.myGuess.size() < this.soluction.size()) {
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
    public List<SecretCodeResults> guess() {
        if (this.myGuess.size() == this.soluction.size()) {
            return this.getReultGuess();
        } else {
            return Collections.emptyList();
        }
    }

    private List<SecretCodeResults> getReultGuess() {
        List<SecretCodeResults> results = new ArrayList<>();
        for (int i = 0; i < this.myGuess.size(); i++) {
            if (this.soluction.contains(this.myGuess.get(i)) &&
                i == this.soluction.indexOf(this.myGuess.get(i))) {
                results.add(SecretCodeResults.CORRECT_COLOR_AND_POSITION);
            } else if (this.soluction.contains(this.myGuess.get(i))) {
                results.add(SecretCodeResults.CORRECT_COLOR);
            } else {
                results.add(SecretCodeResults.WRONG_COLOR);
            }
        }
        return results;
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
    
}
