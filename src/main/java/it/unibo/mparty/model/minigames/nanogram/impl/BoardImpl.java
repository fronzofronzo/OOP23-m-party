package it.unibo.mparty.model.minigames.nanogram.impl;

import it.unibo.mparty.model.minigames.nanogram.api.Board;
import it.unibo.mparty.utilities.Position;

import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of the {@link Board} interface for managing the state of a Nanogram game board.
 * This class uses a {@link Map} to track the state of each cell on the board.
 */
public class BoardImpl implements Board {

    protected final Map<Position, Boolean> board;
    private final int size;

    /**
     * Constructs a {@code BoardImpl} instance with the specified size.
     *
     * @param size the size of the board (size x size).
     */
    public BoardImpl(final int size) {
        this.board = new HashMap<>();
        this.size = size;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSize() {
        return this.size;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCellState(final Position position, final boolean state) {
        this.board.put(position, state);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean getState(final Position position) {
        return this.board.getOrDefault(position, false);
    }
}
