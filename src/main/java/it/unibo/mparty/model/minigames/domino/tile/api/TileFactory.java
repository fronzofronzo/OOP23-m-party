package it.unibo.mparty.model.minigames.domino.tile.api;

import java.util.List;

/**
 * Factory interface for creating sets of domino tiles.
 */
public interface TileFactory {

    /**
     * Creates a set of domino tiles for a double-six domino set.
     *
     * @return a list of tiles representing a double-six domino set
     */
    List<Tile> createDoubleSixSet();
}
