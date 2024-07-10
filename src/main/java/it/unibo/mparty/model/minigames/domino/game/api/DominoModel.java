package it.unibo.mparty.model.minigames.domino.game.api;

import it.unibo.mparty.model.minigames.MinigameModel;
import it.unibo.mparty.model.minigames.domino.board.api.BoardTile;
import it.unibo.mparty.model.minigames.domino.player.api.PlayerTiles;
import it.unibo.mparty.model.minigames.domino.tile.api.Tile;

import java.util.List;

/**
 * Interface for the Domino game model.
 */
public interface DominoModel extends MinigameModel {

    /**
     * Initializes the turn for the players.
     *
     * @param player1 the first player
     * @param player2 the second player
     * @return true if player1 starts, false if player2 starts
     */
    boolean initializeTurn(String player1, String player2);

    /**
     * Checks if the specified tile can be added to the board and adds it if possible.
     *
     * @param player the player attempting to add the tile
     * @param tile the tile to add
     * @return true if the tile was successfully added, false otherwise
     */
    boolean checkAndAddToBoard(String player, Tile tile);

    /**
     * Checks if the specified player can draw a tile from the set.
     *
     * @param player the player to check
     * @return true if the player can draw a tile, false otherwise
     */
    boolean canDrawTile(String player);

    /**
     * Checks if the specified player cannot place any tiles on the board.
     *
     * @param player the player to check
     * @return true if the player cannot place any tiles, false otherwise
     */
    boolean playerCannotMakeMove(String player);

    /**
     * Draws a tile from the set and adds it to the specified player's tiles.
     *
     * @param player the player drawing the tile
     */
    void drawTile(String player);

    /**
     * Gets the set of remaining tiles.
     *
     * @return the list of remaining tiles
     */
    List<Tile> getDominoSet();

    /**
     * Gets the tiles for all players.
     *
     * @return the PlayerTiles object containing players' tiles
     */
    PlayerTiles getAllPlayersTiles();

    /**
     * Gets the current board tile.
     *
     * @return the BoardTile object representing the current state of the board
     */
    BoardTile getBoardTile();
}
