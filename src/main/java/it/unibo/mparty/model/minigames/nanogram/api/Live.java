package it.unibo.mparty.model.minigames.nanogram.api;

public interface Live {
    int getLive();
    void reset();
    void decrease();
    boolean isDeath();
}
