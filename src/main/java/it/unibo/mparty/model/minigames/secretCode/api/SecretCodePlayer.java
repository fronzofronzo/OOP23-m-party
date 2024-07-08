package it.unibo.mparty.model.minigames.secretCode.api;

import java.util.List;

import it.unibo.mparty.model.minigames.secretCode.util.SecretCodeColors;

public interface SecretCodePlayer {

    boolean addColor(SecretCodeColors color);
    
    List<SecretCodeColors> getGuess();
    
    void addPoints(int points);

    String getNamePlayer();

    int getPoints();
}
