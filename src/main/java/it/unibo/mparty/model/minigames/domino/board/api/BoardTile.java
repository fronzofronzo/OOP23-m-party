package it.unibo.mparty.model.minigames.domino.board.api;

import it.unibo.mparty.model.minigames.domino.tile.api.Tile;
import it.unibo.mparty.utilities.Pair;
import it.unibo.mparty.utilities.api.EObserver;
import it.unibo.mparty.utilities.api.ESource;

import java.util.LinkedList;
import java.util.List;

/**
 * Represents the board in the domino game. Manages the tiles on the board and observers.
 */
public interface BoardTile extends ESource<List<Pair<Integer, Integer>>> {

    /**
     * Returns the list of tiles currently on the board.
     *
     * @return the list of tiles on the board
     */
    LinkedList<Tile> getBoardTiles();

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

    /**
     * Adds an observer to the list of observers.
     *
     * @param obs the observer to add
     */
    void addObserver(EObserver<? super List<Pair<Integer, Integer>>> obs);

    /**
     * Notifies all observers of a change.
     *
     * @param tile the data to pass to the observers
     */
    void notifyObservers(List<Pair<Integer, Integer>> tile);
}
