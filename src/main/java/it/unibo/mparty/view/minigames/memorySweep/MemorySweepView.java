package it.unibo.mparty.view.minigames.memorySweep;

import it.unibo.mparty.model.minigames.memorysweep.api.MemorySweep;
import it.unibo.mparty.utilities.Position;
import it.unibo.mparty.view.minigames.MinigameView;
import javafx.event.ActionEvent;

import java.util.Set;

/**
 * interface that model the view that handles the events that happen when a button is clicked for the Memory Sweep mini-game.
 */
public interface MemorySweepView extends MinigameView {

    /**
     * method that sets up the view by marking the buttons randomly chosen.
     *
     * @param randoms the group of randomly chosen positions
     */
    void setUp(Set<Position> randoms);

    /**
     * method that manages the aftermath of the clicked button.
     * based on the parameter passed in input the view will be updated accordingly
     * @param type the type of click
     * @param turn whether is the turn of the first player or the other
     */
    void hit(MemorySweep.HitType type, boolean turn);

    /**
     * method for hiding the random button previously marked.
     *
     * @param randoms the set of the positions marked
     */
    void hideRandoms(Set<Position> randoms);

    /**
     * method for handling the start button.
     *
     * @param e the event that happens when a button is clicked
     */
    void handleStartButton(ActionEvent e);

}
