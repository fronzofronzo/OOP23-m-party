package it.unibo.mparty.model.minigames.domino.tile.impl;

import it.unibo.mparty.model.minigames.domino.tile.api.Side;
import it.unibo.mparty.model.minigames.domino.tile.api.Tile;
import it.unibo.mparty.utilities.Pair;

import java.util.Objects;
import java.util.Optional;

/**
 * Implementation of the {@link Tile} interface. Represents a tile in the domino game.
 */
public class TileImpl implements Tile {

    private Pair<SideType, Side> sideA;
    private Pair<SideType, Side> sideB;

    /**
     * Constructs a new {@link TileImpl} with the specified values for the sides.
     *
     * @param sideA the value of side A
     * @param sideB the value of side B
     */
    public TileImpl(final int sideA, final int sideB) {
        this.sideA = new Pair<>(SideType.SIDE_A, new SideImpl(sideA));
        this.sideB = new Pair<>(SideType.SIDE_B, new SideImpl(sideB));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean match(final Tile tile) {
        return canMatchSide(this.sideA, tile) || canMatchSide(this.sideB, tile);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMatched(final SideType side) {
        switch (side) {
            case SIDE_A -> this.sideA.getSecond().setMatched();
            case SIDE_B -> this.sideB.getSecond().setMatched();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reverse() {
        final Side tempA = this.sideA.getSecond();
        this.sideA = new Pair<>(SideType.SIDE_A, this.sideB.getSecond());
        this.sideB = new Pair<>(SideType.SIDE_B, tempA);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canMatch(final Tile tile) {
        return this.matchedSide(this.sideA.getSecond(), tile).isPresent() || this.matchedSide(this.sideB.getSecond(), tile).isPresent();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDoubleSide() {
        return this.sideA.getSecond().getValue() == this.sideB.getSecond().getValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Side getSideA() {
        return this.sideA.getSecond();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Side getSideB() {
        return this.sideB.getSecond();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSideValue(final SideType side) {
        return switch (side) {
            case SIDE_A -> this.sideA.getSecond().getValue();
            case SIDE_B -> this.sideB.getSecond().getValue();
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSideMatched(final SideType side) {
        return switch (side) {
            case SIDE_A -> this.sideA.getSecond().isMatched();
            case SIDE_B -> this.sideB.getSecond().isMatched();
        };
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
        final boolean directEquality = Objects.equals(sideA, tile.sideA) && Objects.equals(sideB, tile.sideB);
        final boolean reversedEquality = Objects.equals(sideA, tile.sideB) && Objects.equals(sideB, tile.sideA);
        final boolean valueEquality = this.sideA.getSecond().getValue() == tile.sideA.getSecond().getValue()
                && this.sideB.getSecond().getValue() == tile.sideB.getSecond().getValue()
                || this.sideA.getSecond().getValue() == tile.sideB.getSecond().getValue()
                && this.sideB.getSecond().getValue() == tile.sideA.getSecond().getValue();
        return directEquality || reversedEquality || valueEquality;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int valueA = this.sideA.getSecond().getValue();
        final int valueB = this.sideB.getSecond().getValue();
        return valueA <= valueB ? Objects.hash(valueA, valueB) : Objects.hash(valueB, valueA);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "TileImpl{"
                + this.sideA
                + ", " + this.sideB
                + '}';
    }

    private Optional<SideType> matchedSide(final Side side, final Tile tile) {
        if (!side.isMatched()) {
            if (side.getValue() == tile.getSideValue(SideType.SIDE_A)) {
                return Optional.of(SideType.SIDE_A);
            } else if (side.getValue() == tile.getSideValue(SideType.SIDE_B)) {
                return Optional.of(SideType.SIDE_B);
            }
        }
        return Optional.empty();
    }

    private boolean canMatchSide(final Pair<SideType, Side> side, final Tile tile) {
        final Optional<SideType> matchedSide = this.matchedSide(side.getSecond(), tile);
        if (matchedSide.isPresent()) {
            side.getSecond().setMatched();
            tile.setMatched(matchedSide.get());
            if (side.getFirst() == matchedSide.get()) {
                tile.reverse();
            }
            return true;
        }
        return false;
    }
}
