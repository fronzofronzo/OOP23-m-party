package it.unibo.mparty.controller.minigames.domino.api;

import it.unibo.mparty.controller.minigames.MinigameController;

/**
 * Interface for the Domino game controller.
 */
public interface DominoController extends MinigameController {

    /**
     * Plays the specified tile for the current player.
     *
     * @param sideA the value of side A of the tile.
     * @param sideB the value of side B of the tile.
     */
    void playTile(int sideA, int sideB);

    /**
     * Draws a tile for the current player.
     */
    void drawTile();
}
