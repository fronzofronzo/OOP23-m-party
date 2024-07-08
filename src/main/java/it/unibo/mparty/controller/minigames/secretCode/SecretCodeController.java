package it.unibo.mparty.controller.minigames.secretCode;

import it.unibo.mparty.controller.minigames.MinigameController;
import it.unibo.mparty.model.minigames.secretCode.util.SecretCodeColors;

public interface SecretCodeController extends MinigameController{

    void addColor(SecretCodeColors verde);
    
}
