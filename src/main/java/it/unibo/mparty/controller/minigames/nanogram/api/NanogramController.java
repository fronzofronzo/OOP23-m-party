package it.unibo.mparty.controller.minigames.nanogram.api;

/**
 * Interface representing the controller for a Nanogram game.
 * This controller handles the interactions between the model and the view.
 */
public interface NanogramController {

    void checkCell(int x, int y);

    void setFillState(boolean state);
}
