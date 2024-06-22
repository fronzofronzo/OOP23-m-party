package it.unibo.mparty.model.minigames.nanogram.api;

public interface BoardFactory {
    Board createSimpleBoard(int size, int fillPercentage);
    Board createHardBoard(int size, int fillPercentage, int showPercentage);
}
