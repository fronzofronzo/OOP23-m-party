package it.unibo.mparty.view.minigames.nanogram.api;

import it.unibo.mparty.view.minigames.nanogram.NanogramMessage;
import it.unibo.mparty.view.minigames.MinigameView;

import java.util.List;

/**
 * Interface representing the view for a Nanogram game.
 * This interface defines methods to interact with the Nanogram game's UI components
 * and display game state.
 */
public interface NanogramView extends MinigameView {

    /**
     * Initializes the grid of the Nanogram game with the specified size.
     *
     * @param size the size of the grid.
     */
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
     * Displays a status message to the user in the UI.
     *
     * @param message the status message to display, encapsulated in a {@link NanogramMessage} object.
     */
    void displayStatusMessage(NanogramMessage message);

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

    /**
     * Fills or crosses a specific cell in the Nanogram grid based on correctness.
     *
     * @param isCorrect a boolean indicating whether the cell was filled or crossed correctly.
     */
    void fillCell(boolean isCorrect);

    /**
     * Crosses or fills a specific cell in the Nanogram grid based on correctness.
     *
     * @param isCorrect a boolean indicating whether the cell was crossed or filled correctly.
     */
    void crossCell(boolean isCorrect);
}
