package it.unibo.mparty.model.minigames.domino.impl;

import it.unibo.mparty.model.minigames.domino.api.BoardTile;
import it.unibo.mparty.model.minigames.domino.api.Tile;
import it.unibo.mparty.model.minigames.domino.api.PlayerTiles;
import it.unibo.mparty.model.player.api.Player;

import java.util.*;

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
        for (final Tile tile : this.playersTiles.get(player)) {
            if (boardTile.canMatchBoardTile(tile)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void removeTilesFromPlayer(final Player player, final Tile tile) {
        this.playersTiles.get(player).remove(tile);
    }

    @Override
    public Set<Tile> getPlayerTiles(final Player player) {
        return this.playersTiles.getOrDefault(player, new HashSet<>());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerTilesImpl that = (PlayerTilesImpl) o;
        return Objects.equals(playersTiles, that.playersTiles);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(playersTiles);
    }

    @Override
    public String toString() {
        return "PlayerTilesImpl{" +
                "playersTiles=" + playersTiles +
                '}';
    }
}
