package it.unibo.mparty.model.minigames.domino.impl;

import it.unibo.mparty.model.minigames.domino.api.Tile;

import java.util.Objects;

public class TileImpl implements Tile {
    private final int sideA;
    private final int sideB;

    public TileImpl(final int sideA, final int sideB) {
        this.sideA = sideA;
        this.sideB = sideB;
    }

    @Override
    public int getSideA() {
        return sideA;
    }

    @Override
    public int getSideB() {
        return sideB;
    }

    @Override
    public boolean canMatchLeft(final int sideA) {
        return this.sideA == sideA || this.sideB == sideA;
    }

    @Override
    public boolean canMatchRight(final int sideB) {
        return this.sideA == sideB || this.sideB == sideB;
    }

    @Override
    public boolean isDoubleSide() {
        return this.sideA.getValue() == this.sideB.getValue();
    }

    @Override
    public boolean equals(Object o) {
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
