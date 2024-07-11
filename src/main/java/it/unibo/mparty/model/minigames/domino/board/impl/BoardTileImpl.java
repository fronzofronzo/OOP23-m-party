package it.unibo.mparty.model.minigames.domino.board.impl;

import it.unibo.mparty.model.minigames.domino.board.api.BoardTile;
import it.unibo.mparty.model.minigames.domino.tile.api.Tile;
import it.unibo.mparty.utilities.Pair;
import it.unibo.mparty.utilities.impl.ESourceImpl;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Implementation of the {@link BoardTile} interface.
 */
public class BoardTileImpl extends ESourceImpl<List<Pair<Integer, Integer>>> implements BoardTile {

    private static final int SINGLE_TILE = 1;
    private final List<Tile> boardTiles;

    /**
     * Constructs a new {@link BoardTileImpl}.
     */
    public BoardTileImpl() {
        this.boardTiles = new LinkedList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canMatchBoardTile(final Tile tile) {
        if (this.boardTiles.isEmpty()) {
            return true;
        }
        return this.boardTiles.get(0).canMatch(tile) || this.boardTiles.get(this.boardTiles.size() - 1).canMatch(tile);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addTileToBoard(final Tile tile) {
        if (this.boardTiles.isEmpty()) {
            this.boardTiles.add(tile);
        } else if (this.boardTiles.size() == SINGLE_TILE) {
            this.boardTiles.get(0).match(tile);
            if (tile.getSideA().isMatched()) {
                this.boardTiles.add(this.boardTiles.size(), tile);
            } else if (tile.getSideB().isMatched()) {
                this.boardTiles.add(0, tile);
            }
        } else if (this.boardTiles.get(0).match(tile)) {
            this.boardTiles.add(0, tile);
        } else if (this.boardTiles.get(this.boardTiles.size() - 1).match(tile)) {
            this.boardTiles.add(this.boardTiles.size(), tile);
        }
        this.notifyObservers(this.boardTiles.stream()
                .map(t -> new Pair<>(t.getSideA().getValue(), t.getSideB().getValue())).toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final BoardTileImpl boardTile = (BoardTileImpl) o;
        return Objects.equals(this.boardTiles, boardTile.boardTiles);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.boardTiles);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "BoardTileImpl{"
                + "boardTiles=" + this.boardTiles
                + '}';
    }
}
