package it.unibo.mparty.view.minigames.memorySweep;

import it.unibo.mparty.controller.minigames.memorySweep.MemorySweepController;
import it.unibo.mparty.model.minigames.memorysweep.api.MemorySweep;
import it.unibo.mparty.utilities.Position;
import it.unibo.mparty.view.minigames.MinigameView;
import javafx.event.ActionEvent;

import java.util.Set;

/**
 * interface that model the view that handles the events that happen when a button is clicked for the Memory Sweep mini-game
 */
public interface MemorySweepView extends MinigameView {

    /**
     * method that sets up the view by marking the buttons randomly chosen
     * @param randoms the group of randomly chosen positions
     */
    void setUp(Set<Position> randoms);

    /**
     * method that manages the aftermath of the clicked button,
     * based on the parameter passed in input the view will be updated accordingly
     * @param type the parameter on which depends the view update
     */
    void hit(MemorySweep.HitType type,boolean turn);

    void hideRandoms(Set<Position> randoms);

    void handleStartButton(ActionEvent e);
}