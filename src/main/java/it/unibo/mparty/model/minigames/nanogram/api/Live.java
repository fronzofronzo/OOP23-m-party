package it.unibo.mparty.model.minigames.nanogram.api;

public interface Live {
    void reset();
    void decrease();
    boolean isDeath();
}
