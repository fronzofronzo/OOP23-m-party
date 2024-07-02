package it.unibo.mparty.view.minigames.nanogram.api;

import it.unibo.mparty.model.minigames.nanogram.api.Board;
import it.unibo.mparty.view.minigames.nanogram.StatusMessage;
import it.unibo.mparty.view.minigames.MinigameView;

import java.util.List;

/**
 * Interface representing the view for a Nanogram game.
 * This interface defines methods to interact with the Nanogram game's UI components and display game state.
 */
public interface NanogramView extends MinigameView {

    void initGrid(int size);

    /**
     * Sets the hints for each row in the Nanogram grid.
     *
     * @param rowHints a list of lists of integers representing the hints for each row.
     */
    void setRowHints(List<List<Integer>> rowHints);

    /**
     * Sets the hints for each column in the Nanogram grid.
     *
     * @param columnHints a list of lists of integers representing the hints for each column.
     */
    void setColumnHints(List<List<Integer>> columnHints);

    /**
     * Updates the display of the remaining lives in the game.
     *
     * @param actualLives the number of remaining lives to display.
     */
    void updateLives(int actualLives);

    /**
     * Clears the message label in the UI, removing any displayed text.
     */
    void clearMessageLabel();

    /**
     * Displays a status message to the user in the UI.
     *
     * @param message the status message to display, encapsulated in a {@link StatusMessage} object.
     */
    void displayStatusMessage(StatusMessage message);

    /**
     * Disables interaction with all cells in the Nanogram grid.
     * This method is typically called when the game is over.
     */
    void disableAllCells();

    /**
     * Fills the remaining cells in the Nanogram grid with crossed marks.
     * This method is called when the game is completed successfully.
     */
    void fillRemainingCellsWithCrosses();

    void fillCell(boolean isCorrect);

    void crossCell(boolean isCorrect);
}
