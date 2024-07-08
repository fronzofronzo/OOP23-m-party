

package it.unibo.mparty.model.minigames.secretCode.api;

import java.util.List;

import it.unibo.mparty.model.minigames.secretCode.util.SecretCodeColors;
import it.unibo.mparty.model.minigames.secretCode.util.SecretCodeResults;

public interface SecretCodeGame {

    void guess();

    void addColor(SecretCodeColors color);

    void removeColor();

    List<SecretCodeResults> resultGuess();

    String getMessage();     
    
    String getCurrentPlayer();

    boolean isOver();
}
