package it.unibo.mparty.model.minigames.domino.impl;

import it.unibo.mparty.model.minigames.domino.api.BoardTile;
import it.unibo.mparty.model.minigames.domino.api.Tile;
import it.unibo.mparty.model.minigames.domino.api.PlayerTiles;
import it.unibo.mparty.model.player.api.Player;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PlayerTilesImpl implements PlayerTiles {

    private final Map<Player, Set<Tile>> playersTiles = new HashMap<>();


    @Override
    public void initializePlayerTiles(final Player player, final Set<Tile> tiles) {
        playersTiles.put(player, tiles);
    }

    @Override
    public void addTileToPlayer(final Player player, final Tile tile) {
        playersTiles.get(player).add(tile);
    }

    @Override
    public boolean canPlayerPlace(Player player, BoardTile boardTile){
        for (final Tile tile : playersTiles.get(player)) {
           if (!boardTile.canPlaceTile(tile)){
               return false;
           }
        }
        return true;
        //return false;
    }

    @Override
    public void removeTilesFromPlayer(final Player player, final Tile tile) {
        Set<Tile> tiles = this.playersTiles.get(player);
        if (tiles != null) {
            tiles.remove(tile);
        }
    }

    @Override
    public Set<Tile> getPlayerTiles(final Player player) {
        return this.playersTiles.getOrDefault(player, new HashSet<>());
    }
}
