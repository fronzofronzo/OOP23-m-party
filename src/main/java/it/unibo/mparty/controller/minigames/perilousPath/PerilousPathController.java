package it.unibo.mparty.controller.minigames.perilousPath;

import it.unibo.mparty.controller.minigames.MinigameController;
import it.unibo.mparty.model.minigames.perilouspath.api.AbstractPosition;
import it.unibo.mparty.model.minigames.perilouspath.api.PerilousPath;
import javafx.scene.control.Button;

public interface PerilousPathController extends MinigameController {

    public void setUp() throws InterruptedException;

    public void hit(AbstractPosition p);

}