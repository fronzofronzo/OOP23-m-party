package it.unibo.mparty.model.minigames.domino.api;

public interface Tile {
    int getSideA();

    int getSideB();

    boolean canMatchLeft(int sideA);

    boolean canMatchRight(int sideB);
}
