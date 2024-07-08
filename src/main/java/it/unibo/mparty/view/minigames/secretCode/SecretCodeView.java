package it.unibo.mparty.view.minigames.secretCode;

import java.util.List;

import it.unibo.mparty.model.minigames.secretCode.util.SecretCodeColors;
import it.unibo.mparty.model.minigames.secretCode.util.SecretCodeResults;
import it.unibo.mparty.utilities.Position;
import it.unibo.mparty.view.minigames.MinigameView;

public interface SecretCodeView extends MinigameView{

    void updateGuesses(String player, Position pos, SecretCodeColors guess);

    void updateResults(String player, int turn, List<SecretCodeResults> results);
    
}
