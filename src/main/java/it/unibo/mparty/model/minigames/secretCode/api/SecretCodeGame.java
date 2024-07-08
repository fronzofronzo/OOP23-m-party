

package it.unibo.mparty.model.minigames.secretCode.api;

import java.util.List;

import it.unibo.mparty.model.minigames.secretCode.util.SecretCodeColors;
import it.unibo.mparty.model.minigames.secretCode.util.SecretCodeResults;

public interface SecretCodeGame {

    List<SecretCodeResults> guess();

    void addColor(SecretCodeColors color);

    void removeColor();

    String getMessage();     
    
    String getCurrentPlayer();

    boolean isOver();

    String getWinner();
}
