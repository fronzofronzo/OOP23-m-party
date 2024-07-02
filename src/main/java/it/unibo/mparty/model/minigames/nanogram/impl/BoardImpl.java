package it.unibo.mparty.model.minigames.nanogram.impl;

import it.unibo.mparty.model.minigames.nanogram.api.Board;
import it.unibo.mparty.utilities.Position;

import java.util.HashMap;
import java.util.Map;

public class BoardImpl implements Board {

    protected final Map<Position, Boolean> board;
    private final int size;

    public BoardImpl(final int size) {
        this.board = new HashMap<>();
        this.size = size;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public void setCellState(Position position, boolean state) {
        this.board.put(position, state);
    }

    @Override
    public boolean getCellState(Position position) {
        return this.board.get(position);
    }
}
