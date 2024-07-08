package it.unibo.mparty.model.minigames.secretCode.api;

import java.util.List;

import it.unibo.mparty.model.minigames.secretCode.util.SecretCodeColors;

public interface SecreteCodePlayer {

    void addColor(SecretCodeColors color);

    void removeColor();
    
    List<SecretCodeColors> guess();
    
    void addPoints(int points);

    String getMessage();

    String getNamePlayer();

    int getPoints();
}
