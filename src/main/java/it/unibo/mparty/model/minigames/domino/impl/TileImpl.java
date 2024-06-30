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

    private boolean canMatchSide(final Side side, final Tile tile){
        return !side.isMatched() && (side.getValue() == tile.getSideA().getValue() || side.getValue() == tile.getSideB().getValue());
    }

    @Override
    public boolean match(final Tile tile){
        if (canMatchSide(sideA, tile)) {
            sideA.setMatched();
            return true;
        } else if (canMatchSide(sideB, tile)) {
            sideB.setMatched();
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
