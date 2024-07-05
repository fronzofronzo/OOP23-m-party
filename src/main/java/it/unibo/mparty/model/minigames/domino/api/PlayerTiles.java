package it.unibo.mparty.model.minigames.domino.api;

import java.util.Set;

public interface PlayerTiles {
    void initializePlayerTiles(String player, Set<Tile> tiles);

    void addTileToPlayer(String player, Tile tile);

    boolean canPlayerPlace(String player, BoardTile boardTile);

    void removeTilesFromPlayer(String player, Tile tile);

    Set<Tile> getPlayerTiles(String player);
}
