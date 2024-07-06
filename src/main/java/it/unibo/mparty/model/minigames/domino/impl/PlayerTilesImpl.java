package it.unibo.mparty.model.minigames.domino.impl;

import it.unibo.mparty.model.minigames.domino.api.BoardTile;
import it.unibo.mparty.model.minigames.domino.api.Tile;
import it.unibo.mparty.model.minigames.domino.api.PlayerTiles;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;


public class PlayerTilesImpl implements PlayerTiles {

    private final Map<String, Set<Tile>> playersTiles = new HashMap<>();

    @Override
    public void initializePlayerTiles(final String player, final Set<Tile> tiles) {
        this.playersTiles.put(player, tiles);
    }

    @Override
    public void addTileToPlayer(final String player, final Tile tile) {
        this.playersTiles.get(player).add(tile);
    }

    @Override
    public boolean canPlayerPlace(final String player, final BoardTile boardTile){
        return this.playersTiles.get(player).stream().anyMatch(boardTile::canMatchBoardTile);
    }

    @Override
    public void removeTilesFromPlayer(final String player, final Tile tile) {
        this.playersTiles.get(player).remove(tile);
    }

    @Override
    public Set<Tile> getPlayerTiles(final String player) {
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
        return Objects.hash(playersTiles);
    }

    @Override
    public String toString() {
        return "PlayerTilesImpl{" +
                "playersTiles=" + playersTiles +
                '}';
    }
}
