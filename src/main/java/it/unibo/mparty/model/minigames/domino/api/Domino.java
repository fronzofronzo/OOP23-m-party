package it.unibo.mparty.model.minigames.domino.api;

public interface Domino {
    int getSideA();

    int getSideB();

    boolean canMatch(Domino other);
}
