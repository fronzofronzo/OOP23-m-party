package it.unibo.mparty.model.minigames.domino.impl;

import it.unibo.mparty.model.minigames.domino.api.Tile;
import it.unibo.mparty.model.minigames.domino.api.PlayerTiles;
import it.unibo.mparty.model.player.api.Player;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PlayerTilesImpl implements PlayerTiles {
    private Map<Player,Set<Tile>> playerDomino = new HashMap<>();

    @Override
    public void addTilesToPlayer(final Player player, final Set<Tile> tiles) {
        this.playerDomino.computeIfAbsent(player, k -> new HashSet<>()).addAll(tiles);
    }

    @Override
    public boolean removeTilesFromPlayer(final Player player, final Tile tile){
        Set<Tile> tiles = this.playerDomino.get(player);
        if(tiles == null) return false;
        return tiles.remove(tile);
    }

    @Override
    public Set<Tile> getPlayerTiles(final Player player){
        return this.playerDomino.getOrDefault(player, new HashSet<>());
    }

//    @Override
//    public boolean canPlayerMakeMove(Player player){
//        return true;
//    }
}
