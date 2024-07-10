package it.unibo.mparty.model.minigames.domino.player.api;

import it.unibo.mparty.model.minigames.domino.board.api.BoardTile;
import it.unibo.mparty.model.minigames.domino.tile.api.Tile;

import java.util.Set;

/**
 * Represents the tiles held by a player in the domino game.
 */
public interface PlayerTiles {

    /**
     * Initializes the player's tiles with a given set of tiles.
     *
     * @param player the player's identifier
     * @param tiles  the set of tiles to initialize
     */
    void initializePlayerTiles(String player, Set<Tile> tiles);

    /**
     * Adds a tile to the player's set of tiles.
     *
     * @param player the player's identifier
     * @param tile   the tile to add
     */
    void addTileToPlayer(String player, Tile tile);

    /**
     * Checks if the player can place any of their tiles on the board.
     *
     * @param player    the player's identifier
     * @param boardTile the board tile to check against
     * @return true if the player can place a tile, false otherwise
     */
    boolean cannotPlayerPlace(String player, BoardTile boardTile);

    /**
     * Removes a tile from the player's set of tiles.
     *
     * @param player the player's identifier
     * @param tile   the tile to remove
     */
    void removeTilesFromPlayer(String player, Tile tile);

    /**
     * Gets the set of tiles held by the player.
     *
     * @param player the player's identifier
     * @return the set of tiles held by the player
     */
    Set<Tile> getPlayerTiles(String player);
}
