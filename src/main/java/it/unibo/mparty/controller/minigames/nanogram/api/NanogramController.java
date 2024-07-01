package it.unibo.mparty.controller.minigames.nanogram.api;

/**
 * Interface representing the controller for a Nanogram game.
 * This controller handles the interactions between the model and the view.
 */
public interface NanogramController {

    /**
     * Updates the model with the specified cell state at the given position.
     * This method is called when a cell in the view is clicked and needs to be updated in the model.
     *
     * @param row   the row index of the cell to update
     * @param col   the column index of the cell to update
     * @param state the new state of the cell (e.g., FILLED, CROSSED)
     */
    void updateModel(int row, int col, boolean state);

    void checkCell(int row, int column);

    void setFillState(boolean state);
}
