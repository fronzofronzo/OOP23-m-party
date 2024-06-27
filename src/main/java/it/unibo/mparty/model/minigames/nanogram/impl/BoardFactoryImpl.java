package it.unibo.mparty.model.minigames.nanogram.impl;

import it.unibo.mparty.model.minigames.nanogram.api.Board;
import it.unibo.mparty.model.minigames.nanogram.api.BoardFactory;
import it.unibo.mparty.model.minigames.nanogram.util.CellState;
import it.unibo.mparty.utilities.Position;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class BoardFactoryImpl implements BoardFactory {

    private final Random random = new Random();

    @Override
    public Board createSimpleBoard(int size, int fillPercentage) {
        Map<Position, CellState> grid = new HashMap<>();
        Map<Position, CellState> showGrid = new HashMap<>();

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                Position position = new Position(row, col);
                CellState state = random.nextInt(100) < fillPercentage ? CellState.FILLED : CellState.CROSSED;
                grid.put(position, state);
                showGrid.put(position, CellState.CROSSED);
            }
        }

        System.out.println("Grid: " + grid);
        System.out.println("Show grid: " + showGrid);
        return new BoardImpl(grid, showGrid, size);
    }

    @Override
    public Board createHardBoard(int size, int fillPercentage, int showPercentage) {
        Board simpleBoard = createSimpleBoard(size, fillPercentage);
        Map<Position, CellState> grid = new HashMap<>(simpleBoard.getGrid());
        Map<Position, CellState> showGrid = new HashMap<>(simpleBoard.getShowGrid());

        int totalCells = size * size;
        int filledCellsCount = countFilledCells(grid);
        int showCellsCount = (int) Math.round(totalCells * (showPercentage / 100.0));

        for (Map.Entry<Position, CellState> entry : grid.entrySet()) {
            if (entry.getValue() == CellState.FILLED && showCellsCount > 0) {
                showGrid.put(entry.getKey(), CellState.FILLED);
                showCellsCount--;
            }
        }

        return new BoardImpl(grid, showGrid, size);
    }

    private int countFilledCells(Map<Position, CellState> grid) {
        return (int) grid.values().stream().filter(state -> state == CellState.FILLED).count();
    }
}
