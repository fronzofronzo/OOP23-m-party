package it.unibo.mparty.model.minigames.secretCode.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.unibo.mparty.model.minigames.secretCode.api.SecretCodeGame;
import it.unibo.mparty.model.minigames.secretCode.api.SecretCodePlayer;
import it.unibo.mparty.model.minigames.secretCode.util.SecretCodeColors;
import it.unibo.mparty.model.minigames.secretCode.util.SecretCodeResults;
import it.unibo.mparty.utilities.RandomFromSet;

public class SecretCodeGameImpl implements SecretCodeGame{

    private static final int DIM_SOLUCTION = 4;
    private static final int TURNS = 8;

    private List<SecretCodePlayer> players;
    private List<SecretCodeColors> soluction;
    private int actualPlayerIndex = 0;
    private int turn = 1 ;
    private boolean someoneHasGuessd = false;


    public SecretCodeGameImpl(List<String> players)  {
        generateSoluction();
        players.stream()
            .forEach(p -> this.players.add(new SecretCodePlayerImpl(p, this.soluction.size())));
    }

    private void generateSoluction() {
        do {
            SecretCodeColors tmp = RandomFromSet.get(Set.of(SecretCodeColors.values()));
            if (!this.soluction.contains(tmp)) {
                this.soluction.add(tmp);
            }
        } while (this.soluction.size() < DIM_SOLUCTION);
    }

    @Override
    public List<SecretCodeResults> getGuessResult() {
        List<SecretCodeResults> res = computeResult(this.players.get(actualPlayerIndex).getGuess());
        if (!res.isEmpty()) {
            this.nextPlayer();
        }
        return res;
    } 

    private List<SecretCodeResults> computeResult(List<SecretCodeColors> guess) {
        if (guess.size() != this.soluction.size()) {
            return Collections.emptyList();
        }
        List<SecretCodeResults> results = new ArrayList<>();
        for (int i = 0; i < guess.size(); i++) {
            if (this.soluction.contains(guess.get(i)) &&
                i == this.soluction.indexOf(guess.get(i))) {
                results.add(SecretCodeResults.CORRECT_COLOR_AND_POSITION);
            } else if (this.soluction.contains(guess.get(i))) {
                results.add(SecretCodeResults.CORRECT_COLOR);
            } else {
                results.add(SecretCodeResults.WRONG_COLOR);
            }
        }
        if (hasGuessed(results)) {
            this.someoneHasGuessd = true;
        }
        return Collections.unmodifiableList(results);
    }

    private boolean hasGuessed(List<SecretCodeResults> results) {
        return !results.contains(SecretCodeResults.CORRECT_COLOR) &&
               !results.contains(SecretCodeResults.WRONG_COLOR); 
    }

    @Override
    public void addColor(SecretCodeColors color) {
        this.players.get(actualPlayerIndex).addColor(color);
    }

    @Override
    public void removeColor() {
        this.players.get(actualPlayerIndex).removeColor();
    }

    @Override
    public String getGameMessage() {
        return this.players.get(actualPlayerIndex).getMessage();
    }

    @Override
    public String getCurrentPlayer() {
        return this.players.get(actualPlayerIndex).getNamePlayer();
    }

    @Override
    public boolean isOver() {
        return this.turn == TURNS || this.someoneHasGuessd;
    }

    private void nextPlayer() {
        this.actualPlayerIndex = (this.actualPlayerIndex + 1) % players.size();
        if(this.actualPlayerIndex == 0){
            this.turn++;
        }
    }

    @Override
    public String getWinner() {
        int maxPoints = 0;
        Set<String> winners = new HashSet<>();
        for (SecretCodePlayer p : this.players) {
            if (p.g)
        }
    }
    
}
