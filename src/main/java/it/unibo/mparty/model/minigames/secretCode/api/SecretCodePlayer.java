package it.unibo.mparty.model.minigames.secretCode.api;

import java.util.List;

import it.unibo.mparty.model.minigames.secretCode.util.SecretCodeColors;

public interface SecretCodePlayer {

    List<SecretCodeColors> getCurrentGuess = null;

    void addColor(SecretCodeColors color);

    void removeColor();
    
    List<SecretCodeColors> getGuess();
    
    void addPoints(int points);

    String getMessage();

    String getNamePlayer();

    int getPoints();

    List<SecretCodeColors> getCurrentGuess();
}
