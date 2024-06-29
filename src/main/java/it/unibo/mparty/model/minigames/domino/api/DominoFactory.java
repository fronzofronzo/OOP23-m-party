package it.unibo.mparty.model.minigames.domino.api;

import java.util.Set;

public interface DominoFactory {
    Domino createDomino(int sideA, int sideB);

    Set<Domino> createFullSet();
}
