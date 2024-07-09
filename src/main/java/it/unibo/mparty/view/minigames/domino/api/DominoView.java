package it.unibo.mparty.view.minigames.domino.api;

import it.unibo.mparty.utilities.api.EObserver;
import it.unibo.mparty.model.minigames.domino.tile.api.Tile;
import it.unibo.mparty.utilities.Pair;
import it.unibo.mparty.view.minigames.MinigameView;
import it.unibo.mparty.view.minigames.domino.DominoMessage;

import java.util.List;
import java.util.Set;

/**
 * Interface for the Domino game view. Provides methods to update the UI based on game state changes.
 */
public interface DominoView extends MinigameView, EObserver<List<Pair<Integer, Integer>>> {

    /**
     * Sets the tiles for the specified player.
     *
     * @param isPlayer1  true if setting tiles for player 1, false for player 2.
     * @param playerTiles the set of tiles to set for the player.
     */
    void setPlayerTiles(boolean isPlayer1, Set<Tile> playerTiles);

    /**
     * Sets the name of the specified player.
     *
     * @param isPlayer1  true if setting name for player 1, false for player 2.
     * @param playerName the name of the player.
     */
    void setPlayerName(boolean isPlayer1, String playerName);

    /**
     * Sets the turn indicator for the current player.
     *
     * @param isPlayer1Turn true if it is player 1's turn, false otherwise.
     */
    void setTurn(boolean isPlayer1Turn);

    /**
     * Enables the draw button.
     */
    void playerCanDraw();

    /**
     * Disables the draw button.
     */
    void playerCantDraw();

    /**
     * Sets the message to be displayed to the players.
     *
     * @param message the message to display.
     */
    void setMessage(DominoMessage message);

    /**
     * Updates the UI to display the remaining number of tiles in the deck.
     *
     * @param size the number of remaining tiles.
     */
    void updateRemainingTileSize(int size);
}
