package it.unibo.mparty.controller.minigames.perilousPath;

import it.unibo.mparty.controller.minigames.MinigameController;
import it.unibo.mparty.model.minigames.perilouspath.api.AbstractPosition;
import it.unibo.mparty.model.minigames.perilouspath.api.PerilousPath;
import javafx.scene.control.Button;

public interface PerilousPathController extends MinigameController {

    void setUp() throws InterruptedException;

    void hit(AbstractPosition p);

}