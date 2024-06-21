package it.unibo.mparty.model.minigames.nanogram.impl;

import it.unibo.mparty.model.minigames.nanogram.api.Board;
import it.unibo.mparty.model.minigames.nanogram.util.CellState;

import java.util.List;

public class BoardImpl implements Board {
    @Override
    public List<Integer> getHints() {
        return List.of();
    }

    @Override
    public CellState getCellState(int x, int y) {
        return null;
    }
}
