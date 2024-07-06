package it.unibo.mparty.model.minigames.domino.impl;

import it.unibo.mparty.model.minigames.domino.api.Side;
import it.unibo.mparty.model.minigames.domino.api.Tile;

import java.util.Objects;
import java.util.Optional;

public class TileImpl implements Tile {

    private Side sideA;
    private Side sideB;

    public TileImpl(int sideA, int sideB) {
        this.sideA = new SideImpl(sideA);
        this.sideB = new SideImpl(sideB);
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

    @Override
    public boolean match(final Tile tile) {
        Optional<Side> matchedSideA = this.canMatchSide(this.sideA, tile);
        if (matchedSideA.isPresent()) {
            Side matchedTileSide = matchedSideA.get();
            this.sideA.setMatched();
            matchedTileSide.setMatched();

            if (!tile.isDoubleSide() && (matchedTileSide.getValue() == tile.getSideA().getValue())){
                tile.reverse();
            }
            return true;
        }
        Optional<Side> matchedSideB = this.canMatchSide(this.sideB, tile);
        if (matchedSideB.isPresent()) {
            Side matchedTileSide = matchedSideB.get();
            this.sideB.setMatched();
            matchedTileSide.setMatched();

            if (!tile.isDoubleSide() && (matchedTileSide.getValue() == tile.getSideB().getValue())){
                tile.reverse();
            }
            return true;
        }
        return false;
    }

    @Override
    public void reverse() {
        Side tempA = this.sideA.copy();
        this.sideA = this.sideB.copy();
        this.sideB = tempA;
    }

    @Override
    public boolean canMatch(final Tile tile) {
        return this.canMatchSide(this.sideA, tile).isPresent() || this.canMatchSide(this.sideB, tile).isPresent();
    }

    @Override
    public boolean isDoubleSide() {
        return this.sideA.getValue() == this.sideB.getValue();
    }

    @Override
    public Side getSideA() {
        return this.sideA;
    }

    @Override
    public Side getSideB() {
        return this.sideB;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TileImpl tile = (TileImpl) o;
        return Objects.equals(this.sideA.getValue(), tile.sideA.getValue()) && Objects.equals(this.sideB.getValue(), tile.sideB.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.sideA.getValue(), this.sideB.getValue());
    }

    @Override
    public String toString() {
        return "TileImpl{" +
                "sideA=" + this.sideA +
                ", sideB=" + this.sideB +
                '}';
    }
}
