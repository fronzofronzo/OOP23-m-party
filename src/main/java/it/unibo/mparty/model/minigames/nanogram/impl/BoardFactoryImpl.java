package it.unibo.mparty.model.minigames.nanogram.impl;

import it.unibo.mparty.model.minigames.nanogram.api.Board;
import it.unibo.mparty.model.minigames.nanogram.api.BoardFactory;
import it.unibo.mparty.model.minigames.nanogram.util.CellState;
import it.unibo.mparty.utilities.Position;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Implementation of the {@link BoardFactory} interface for creating Nanogram boards.
 * This class provides methods to generate simple and hard boards based on specified parameters.
 */
public class BoardFactoryImpl implements BoardFactory {

    private final Random random = new Random();

    /**
     * {@inheritDoc}
     */
    @Override
    public Board createSimpleBoard(final int size, final int fillPercentage) {
        final Map<Position, CellState> grid = new HashMap<>();

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                Position position = new Position(row, col);
                CellState state = random.nextInt(100) < fillPercentage ? CellState.FILLED : CellState.CROSSED;
                grid.put(position, state);
            }
        }
        return new BoardImpl(grid, size);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Board createHardBoard(final int size, final int fillPercentage, final int showPercentage) {
        final Board simpleBoard = createSimpleBoard(size, fillPercentage);
        final Map<Position, CellState> grid = new HashMap<>(simpleBoard.getGrid());
        final Map<Position, CellState> showGrid = new HashMap<>(simpleBoard.getShowGrid());

        final int totalCells = size * size;
        int showCellsCount = (int) Math.round(totalCells * (showPercentage / 100.0));

        for (Map.Entry<Position, CellState> entry : grid.entrySet()) {
            if (entry.getValue() == CellState.FILLED && showCellsCount > 0) {
                showGrid.put(entry.getKey(), CellState.FILLED);
                showCellsCount--;
            }
        }

        return new BoardImpl(grid, showGrid, size);
    }
}
