package it.unibo.mparty.view.minigames.memorySweep;

import it.unibo.mparty.controller.minigames.memorySweep.MemorySweepController;
import it.unibo.mparty.model.minigames.memorysweep.api.MemorySweep;
import it.unibo.mparty.utilities.Position;
import javafx.event.ActionEvent;

import java.util.Set;

/**
 * interface that model the view that handles the events that happen when a button is clicked for the Memory Sweep mini-game
 */
public interface MemorySweepView {

    /**
     * method that sets up the view by marking the buttons randomly chosen
     * @param randoms the group of randomly chosen positions
     */
    public void setUp(Set<Position> randoms);

    /**
     * method that manages the aftermath of the clicked button,
     * based on the parameter passed in input the view will be updated accordingly
     * @param type the parameter on which depends the view update
     */
    public void hit(MemorySweep.HitType type);

    /**
     * method that handles the click of a button in the grid
     * @param e the event that needs to be handled
     */
    public void buttonClicked(ActionEvent e);

    /**
     * links the controller of the mini-game to this view
     * @param controller the controller that needs to be linked
     */
    public void setObserver(MemorySweepController controller);
}