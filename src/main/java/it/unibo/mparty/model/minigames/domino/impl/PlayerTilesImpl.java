package it.unibo.mparty.model.minigames.domino.impl;

import it.unibo.mparty.model.minigames.domino.api.Tile;
import it.unibo.mparty.model.minigames.domino.api.PlayerTiles;
import it.unibo.mparty.model.player.api.Player;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PlayerTilesImpl implements PlayerTiles {

    private final Map<Player,Set<Tile>> playerTiles = new HashMap<>();

    @Override
    public void addTilesToPlayer(final Player player, final Set<Tile> tiles) {
        if (!playerTiles.containsKey(player) || this.playerTiles.get(player).isEmpty()){
            this.playerTiles.computeIfAbsent(player, k -> new HashSet<>()).addAll(tiles);
        } else {
            Tile tile = tiles.stream().findFirst().orElse(null);
            if (tile != null) {
                this.playerTiles.computeIfAbsent(player, k -> new HashSet<>()).add(tile);
                tiles.remove(tile);
            }
        }
    }

    @Override
    public void removeTilesFromPlayer(final Player player, final Tile tile){
        Set<Tile> tiles = this.playerTiles.get(player);
        if(tiles != null) {
            tiles.remove(tile);
        }
    }

    @Override
    public Set<Tile> getPlayerTiles(final Player player){
        return this.playerTiles.getOrDefault(player, new HashSet<>());
    }
}
