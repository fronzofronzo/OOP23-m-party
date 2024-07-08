package it.unibo.mparty.model.minigames.secretCode.impl;

import java.util.List;
import java.util.Set;

import it.unibo.mparty.model.minigames.secretCode.api.SecretCodeGame;
import it.unibo.mparty.model.minigames.secretCode.api.SecreteCodePlayer;
import it.unibo.mparty.utilities.RandomFromSet;

public class SecretCodeGameImpl implements SecretCodeGame{

    private static final int DIM_SOLUCTION = 4;

    private List<SecreteCodePlayer> players;
    private List<SecretCodeColors> soluction;


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
    public void guess() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'guess'");
    }

    @Override
    public void addColor(SecretCodeColors color) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addColor'");
    }

    @Override
    public void removeColor() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeColor'");
    }

    @Override
    public List<SecretCodeResults> resultGuess() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'resultGuess'");
    }

    @Override
    public String getMessage() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMessage'");
    }

    @Override
    public String getCurrentPlayer() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCurrentPlayer'");
    }

    @Override
    public boolean isOver() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isOver'");
    }
    
}
