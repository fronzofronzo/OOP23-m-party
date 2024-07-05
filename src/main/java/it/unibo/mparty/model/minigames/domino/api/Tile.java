package it.unibo.mparty.model.minigames.domino.api;

public interface Tile {

    Side getSideA();

    Side getSideB();

    boolean match(Tile tile);

    boolean canMatch(Tile tile);

    boolean isDoubleSide();
}
