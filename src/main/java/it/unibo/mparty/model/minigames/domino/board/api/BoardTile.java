package it.unibo.mparty.model.minigames.domino.board.api;

import it.unibo.mparty.model.minigames.domino.tile.api.Tile;
import it.unibo.mparty.utilities.Pair;
import it.unibo.mparty.utilities.api.ESource;

import java.util.List;

/**
 * Represents the board in the domino game. Manages the tiles on the board and observers.
 */
public interface BoardTile extends ESource<List<Pair<Integer, Integer>>> {

    /**
     * Checks if a tile can be matched with any tile currently on the board.
     *
     * @param tile the tile to check
     * @return true if the tile can match a board tile, false otherwise
     */
    boolean canMatchBoardTile(Tile tile);

    /**
     * Adds a tile to the board.
     *
     * @param tile the tile to add
     */
    void addTileToBoard(Tile tile);
}
