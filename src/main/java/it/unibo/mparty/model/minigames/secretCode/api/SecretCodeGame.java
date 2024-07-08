

package it.unibo.mparty.model.minigames.secretCode.api;

import java.util.List;

import it.unibo.mparty.model.minigames.secretCode.util.SecretCodeColors;
import it.unibo.mparty.model.minigames.secretCode.util.SecretCodeResults;

public interface SecretCodeGame {

    List<SecretCodeResults> getGuessResult();

    List<SecretCodeColors> getCurrentGuess();

    void addColor(SecretCodeColors color);

    void removeColor();

    String getGameMessage();     
    
    String getCurrentPlayer();

    boolean isOver();

    String getWinner();
}
