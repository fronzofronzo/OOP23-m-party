package it.unibo.mparty.view.minigames.perilouspath;

import it.unibo.mparty.model.minigames.perilouspath.api.AbstractPosition;
import it.unibo.mparty.model.minigames.perilouspath.api.PerilousPath;
import it.unibo.mparty.view.minigames.MinigameView;
import javafx.event.ActionEvent;
import java.util.List;

/**
 * interface that models the view for Perilous Path mini-game
 */
public interface PerilousPathView extends MinigameView {

    /**
     * method that manages the setting up of the view when called.
     *
     * @param balls the list of balls in the grid that needs to be marked
     * @param bombs the list of bombs in the grid that needs to be marked
     */
    void setUpView(List<AbstractPosition> balls, List<AbstractPosition> bombs);

    /**
     * method that manages the hiding of the marked button that are a part of the bomb list.
     *
     * @param bombs the list of bombs that needs to be hid
     */
    void hideBombs(List<AbstractPosition> bombs);

    /**
     * method that manages the click of a button in the grid and links it to the model of Perilous Path though the controller.
     *
     * @param type the type of click that can happen
     */
    void hitTile(PerilousPath.Type type);

    /**
     * method that handles the click of the start button.
     *
     * @param e the click of the button
     */
    void handleStartButton(ActionEvent e) throws InterruptedException;

    /**
     * method that manages the show of the marked button that are a part of the bomb list.
     *
     * @param bombs the list of bombs that needs to be shown
     */
    void showBombs(List<AbstractPosition> bombs);

}
