package it.unibo.mparty.model.minigames.domino.tile.impl;

import it.unibo.mparty.model.minigames.domino.tile.api.Side;
import it.unibo.mparty.model.minigames.domino.tile.api.Tile;

import java.util.Objects;
import java.util.Optional;

/**
 * Implementation of the {@link Tile} interface. Represents a tile in the domino game.
 */
public class TileImpl implements Tile {

    private Side sideA;
    private Side sideB;

    /**
     * Constructs a new {@link TileImpl} with the specified values for the sides.
     *
     * @param sideA the value of side A
     * @param sideB the value of side B
     */
    public TileImpl(final int sideA, final int sideB) {
        this.sideA = new SideImpl(sideA);
        this.sideB = new SideImpl(sideB);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public boolean match(final Tile tile) {
        final Optional<Side> matchedSideA = this.canMatchSide(this.sideA, tile);
        if (matchedSideA.isPresent()) {
            final Side matchedTileSide = matchedSideA.get();
            this.sideA.setMatched();
            matchedTileSide.setMatched();

            if (matchedTileSide == tile.getSideA()) {
                tile.reverse();
            }
            return true;
        }
        final Optional<Side> matchedSideB = this.canMatchSide(this.sideB, tile);
        if (matchedSideB.isPresent()) {
            final Side matchedTileSide = matchedSideB.get();
            this.sideB.setMatched();
            matchedTileSide.setMatched();

            if (matchedTileSide == tile.getSideB()) {
                tile.reverse();
            }
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reverse() {
        final Side tempA = this.sideA;
        this.sideA = this.sideB;
        this.sideB = tempA;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canMatch(final Tile tile) {
        return this.canMatchSide(this.sideA, tile).isPresent() || this.canMatchSide(this.sideB, tile).isPresent();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDoubleSide() {
        return this.sideA.getValue() == this.sideB.getValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Side getSideA() {
        return this.sideA;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Side getSideB() {
        return this.sideB;
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
        final TileImpl tile = (TileImpl) o;
        return (this.sideA.getValue() == tile.sideA.getValue() && this.sideB.getValue() == tile.sideB.getValue())
                || (this.sideA.getValue() == tile.sideB.getValue() && this.sideB.getValue() == tile.sideA.getValue());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int valueA = this.sideA.getValue();
        final int valueB = this.sideB.getValue();
        return valueA <= valueB ? Objects.hash(valueA, valueB) : Objects.hash(valueB, valueA);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "TileImpl{" +
                "sideA=" + this.sideA
                + ", sideB=" + this.sideB
                + '}';
    }

    private Optional<Side> canMatchSide(final Side side, final Tile tile) {
        if (side.isMatched()) {
            return Optional.empty();
        } else if (side.getValue() == tile.getSideA().getValue()) {
            return Optional.of(tile.getSideA());
        } else if (side.getValue() == tile.getSideB().getValue()) {
            return Optional.of(tile.getSideB());
        } else {
            return Optional.empty();
        }
    }
}
