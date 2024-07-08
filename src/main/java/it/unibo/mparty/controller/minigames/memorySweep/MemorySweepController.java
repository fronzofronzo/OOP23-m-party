package it.unibo.mparty.controller.minigames.memorySweep;

import it.unibo.mparty.controller.minigames.MinigameController;
import it.unibo.mparty.utilities.Position;

public interface MemorySweepController extends MinigameController {

    public void setUp();

    public void hit(Position p);
}