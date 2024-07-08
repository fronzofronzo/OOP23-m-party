package it.unibo.mparty.model.minigames.secretCode.impl;

import java.util.ArrayList;
import java.util.List;

import it.unibo.mparty.model.minigames.secretCode.api.SecreteCodePlayer;

public class SecreteCodePlayerImpl implements SecreteCodePlayer{

    private static final int N_COLORS = 4;

    private final String player;
    private List<SecretCodeColors> myGuess = new ArrayList<>();

    public SecreteCodePlayerImpl(String player){
        this.player = player;
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
    
}
