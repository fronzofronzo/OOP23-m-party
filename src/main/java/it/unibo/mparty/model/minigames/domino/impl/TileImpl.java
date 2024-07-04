package it.unibo.mparty.model.minigames.domino.impl;

import it.unibo.mparty.model.minigames.domino.api.Side;
import it.unibo.mparty.model.minigames.domino.api.Tile;

import java.util.Objects;
import java.util.Optional;

public class TileImpl implements Tile {

    private final Side sideA;
    private final Side sideB;

    public TileImpl(int sideA, int sideB) {
        this.sideA = new SideImpl(sideA);
        this.sideB = new SideImpl(sideB);
    }

    private Optional<Side> canMatchSide(final Side side, final Tile tile){
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
    public boolean match(final Tile tile){
        Optional<Side> matchedSideA = this.canMatchSide(sideA, tile);
        if (matchedSideA.isPresent()) {
            sideA.setMatched();
            matchedSideA.get().setMatched();
            return true;
        }
        Optional<Side> matchedSideB = this.canMatchSide(sideB, tile);
        if (matchedSideB.isPresent()) {
            sideB.setMatched();
            matchedSideB.get().setMatched();
            return true;
        }
        return false;
    }

    @Override
    public boolean canMatch(Tile tile){
        return this.canMatchSide(sideA, tile).isPresent() || this.canMatchSide(sideB, tile).isPresent();
    }

    @Override
    public boolean isDoubleSide() {
        return this.sideA.getValue() == this.sideB.getValue();
    }

    @Override
    public Side getSideA() {
        return sideA;
    }

    @Override
    public Side getSideB() {
        return sideB;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TileImpl tile = (TileImpl) o;
        return Objects.equals(sideA.getValue(), tile.sideA.getValue()) && Objects.equals(sideB.getValue(), tile.sideB.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(sideA.getValue(), sideB.getValue());
    }

    @Override
    public String toString() {
        return "TileImpl{" +
                "sideA=" + sideA +
                ", sideB=" + sideB +
                '}';
    }
}
