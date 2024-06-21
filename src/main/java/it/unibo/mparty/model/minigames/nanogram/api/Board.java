package it.unibo.mparty.model.minigames.nanogram.api;

import it.unibo.mparty.model.minigames.nanogram.util.CellState;

import java.util.List;

public interface Board {
    List<Integer> getHints();
    CellState getCellState(int x, int y);
}
