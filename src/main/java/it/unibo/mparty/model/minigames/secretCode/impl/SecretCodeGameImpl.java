package it.unibo.mparty.model.minigames.secretCode.impl;

import java.util.List;
import java.util.Set;

import it.unibo.mparty.model.minigames.secretCode.api.SecretCodeGame;
import it.unibo.mparty.model.minigames.secretCode.api.SecreteCodePlayer;
import it.unibo.mparty.model.minigames.secretCode.util.SecretCodeColors;
import it.unibo.mparty.model.minigames.secretCode.util.SecretCodeResults;
import it.unibo.mparty.utilities.RandomFromSet;

public class SecretCodeGameImpl implements SecretCodeGame{

    private static final int DIM_SOLUCTION = 4;
    private static final int TURNS = 8;

    private List<SecreteCodePlayer> players;
    private List<SecretCodeColors> soluction;
    private int actualPlayerIndex = 0;
    private int turn = 1 ;
    private boolean someoneHasGuessd = false;


    public SecretCodeGameImpl(List<String> players)  {
        generateSoluction();
        players.stream().forEach(p -> this.players.add(new SecreteCodePlayerImpl(p, soluction)));
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
    public List<SecretCodeResults> guess() {
        List<SecretCodeResults> res = this.players.get(actualPlayerIndex).guess();
        if (!res.isEmpty()) {
            
            this.nextPlayer();
        }
        return res;
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
    public String getMessage() {
        return this.players.get(actualPlayerIndex).getMessage();
    }

    @Override
    public String getCurrentPlayer() {
        return this.players.get(actualPlayerIndex).getNamePlayer();
    }

    @Override
    public boolean isOver() {
        return this.turn == TURNS;
    }

    private void nextPlayer() {
        this.actualPlayerIndex = (this.actualPlayerIndex + 1) % players.size();
        if(this.actualPlayerIndex == 0){
            this.turn++;
        }
    }

    @Override
    public String getWinner() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getWinner'");
    }
    
}
