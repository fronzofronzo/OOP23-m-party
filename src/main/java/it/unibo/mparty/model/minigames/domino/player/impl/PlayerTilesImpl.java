package it.unibo.mparty.model.minigames.domino.player.impl;

import it.unibo.mparty.model.minigames.domino.board.api.BoardTile;
import it.unibo.mparty.model.minigames.domino.tile.api.Tile;
import it.unibo.mparty.model.minigames.domino.player.api.PlayerTiles;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Implementation of the {@link PlayerTiles} interface. Represents the tiles held by players in the domino game.
 */
public class PlayerTilesImpl implements PlayerTiles {

    private final Map<String, Set<Tile>> playersTiles;

    /**
     * Constructs a new {@link PlayerTilesImpl}.
     */
    public PlayerTilesImpl() {
        playersTiles = new HashMap<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initializePlayerTiles(final String player, final Set<Tile> tiles) {
        this.playersTiles.put(player, tiles);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addTileToPlayer(final String player, final Tile tile) {
        this.playersTiles.get(player).add(tile);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canPlayerPlace(final String player, final BoardTile boardTile) {
        return this.playersTiles.get(player).stream().anyMatch(boardTile::canMatchBoardTile);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeTilesFromPlayer(final String player, final Tile tile) {
        this.playersTiles.get(player).remove(tile);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Tile> getPlayerTiles(final String player) {
        return this.playersTiles.getOrDefault(player, new HashSet<>());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final PlayerTilesImpl that = (PlayerTilesImpl) o;
        return Objects.equals(this.playersTiles, that.playersTiles);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.playersTiles);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "PlayerTilesImpl{" +
                "playersTiles=" + this.playersTiles +
                '}';
    }
}
