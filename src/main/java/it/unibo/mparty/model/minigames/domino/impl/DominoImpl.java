package it.unibo.mparty.model.minigames.domino.impl;

import it.unibo.mparty.model.minigames.domino.api.Domino;

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
    public boolean canMatch(Domino other) {
        return this.sideA == other.getSideA() || this.sideA == other.getSideB() ||
                this.sideB == other.getSideA() || this.sideB == other.getSideB();
    }
}
