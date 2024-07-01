package it.unibo.mparty.model.minigames.domino.impl;

import it.unibo.mparty.model.minigames.domino.api.Side;
import it.unibo.mparty.model.minigames.domino.api.Tile;

import java.util.Objects;

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
        } else
        if (side.getValue() == tile.getSideA().getValue()) {
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
            //tile.getSideA().setMatched();
            return true;
        }
        Optional<Side> matchedSideB = this.canMatchSide(sideB, tile);
        if (matchedSideB.isPresent()) {
            sideB.setMatched();
            matchedSideB.get().setMatched();
            //tile.getSideA().setMatched();
            return true;
        }
        return false;
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
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TileImpl tile)) {
            return false;
        }
        return sideA.getValue() == tile.sideA.getValue() && sideB.getValue() == tile.sideB.getValue();
    }

    @Override
    public String toString() {
        return "TileImpl{" +
                "sideA=" + sideA +
                ", sideB=" + sideB +
                "}\n";
    }

    @Override
    public int hashCode() {
        return Objects.hash(sideA, sideB);
    }
}
