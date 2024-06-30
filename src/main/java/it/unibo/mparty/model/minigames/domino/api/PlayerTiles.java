package it.unibo.mparty.model.minigames.domino.api;

import it.unibo.mparty.model.player.api.Player;

import java.util.Set;

public interface PlayerTiles {
    void addTilesToPlayer(Player player, Set<Tile> tiles);

    void addTileToPlayer(Player player, Tile tile);

    void removeTilesFromPlayer(Player player, Tile tile);

    Set<Tile> getPlayerTiles(Player player);
}
