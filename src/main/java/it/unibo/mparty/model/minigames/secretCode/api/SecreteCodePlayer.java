package it.unibo.mparty.model.minigames.secretCode.api;

import java.util.List;

import it.unibo.mparty.model.minigames.secretCode.impl.SecretCodeColors;
import it.unibo.mparty.model.minigames.secretCode.impl.SecretCodeResults;

public interface SecreteCodePlayer {

    void addColor(SecretCodeColors color);

    void removeColor();
    
    List<SecretCodeResults> guess();

    String getMessage();

    String getNamePlayer();

    int getPoints();
}
