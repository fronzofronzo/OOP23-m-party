package it.unibo.mparty.model.minigames.domino.impl;

import it.unibo.mparty.model.minigames.domino.api.Domino;

import java.util.Objects;

public class DominoImpl implements Domino {
    private final int sideA;
    private final int sideB;

    public DominoImpl(final int sideA, final int sideB) {
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
    public boolean canMatch(final Domino other) {
        return this.sideA == other.getSideA() || this.sideA == other.getSideB() ||
                this.sideB == other.getSideA() || this.sideB == other.getSideB();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (!(o instanceof DominoImpl domino)) {
            return false;
        }
        return sideA == domino.sideA && sideB == domino.sideB;
    }

    @Override
    public String toString() {
        return "DominoImpl{" +
                "sideA=" + sideA +
                ", sideB=" + sideB +
                "}\n";
    }

    @Override
    public int hashCode() {
        return Objects.hash(sideA, sideB);
    }
}
