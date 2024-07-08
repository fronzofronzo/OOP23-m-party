package it.unibo.mparty.model.minigames.secretCode.api;

import java.util.List;

import it.unibo.mparty.model.minigames.MinigameModel;
import it.unibo.mparty.model.minigames.secretCode.impl.SecretCodeColors;
import it.unibo.mparty.model.minigames.secretCode.impl.SecretCodeResults;

public interface SecretCodeModel extends MinigameModel{

    void guess();

    void addColor(SecretCodeColors color);

    void removeColor();

    List<SecretCodeResults> resultGuess();

     String getMessage();
    
}
