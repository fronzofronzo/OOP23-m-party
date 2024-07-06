package it.unibo.mparty.model.minigames.domino.api;

public interface Side {

    int getValue();

    Side copy();

    boolean isMatched();

    void setMatched();
}
