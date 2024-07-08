package it.unibo.mparty.controller.minigames.memorySweep;

import it.unibo.mparty.controller.minigames.MinigameController;
import it.unibo.mparty.utilities.Position;

public interface MemorySweepController extends MinigameController {

    void setUp();

    void hit(Position p);
}