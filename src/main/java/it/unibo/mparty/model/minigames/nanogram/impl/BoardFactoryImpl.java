package it.unibo.mparty.model.minigames.nanogram.impl;

import it.unibo.mparty.model.minigames.nanogram.api.Board;
import it.unibo.mparty.model.minigames.nanogram.api.BoardFactory;
import it.unibo.mparty.model.minigames.nanogram.util.CellState;
import it.unibo.mparty.utilities.Position;

import java.util.*;
import java.util.stream.Collectors;

public class BoardFactoryImpl implements BoardFactory {

    @Override
    public Board createSimpleBoard(int size, int fillPercentage) {
        Map<Position, CellState> grid = new HashMap<>();
        Map<Position, CellState> showGrid = new HashMap<>();

        List<Position> positions = generateAllPositions(size);
        int totalCells = size * size;
        int filledCellsCount = (int) Math.round(totalCells * (fillPercentage / 100.0));

        positions.subList(0, filledCellsCount).forEach(pos -> grid.put(pos, CellState.FILLED));
        positions.subList(filledCellsCount, totalCells).forEach(pos -> grid.put(pos, CellState.MARKED));

        grid.forEach((pos, state) -> showGrid.put(pos, CellState.MARKED));

        return new BoardImpl(grid, showGrid, size);
    }

    @Override
    public Board createHardBoard(int size, int fillPercentage, int showPercentage) {
        Board simpleBoard = createSimpleBoard(size, fillPercentage);
        Map<Position, CellState> grid = new HashMap<>(simpleBoard.getGrid());
        Map<Position, CellState> showGrid = new HashMap<>(simpleBoard.getShowGrid());

        int filledCellsCount = countFilledCells(grid);
        int showCellsCount = (int) Math.round(filledCellsCount * (showPercentage / 100.0));

        List<Position> filledPositions = grid.entrySet().stream()
                .filter(entry -> entry.getValue() == CellState.FILLED)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        Collections.shuffle(filledPositions);
        filledPositions.subList(0, showCellsCount).forEach(pos -> showGrid.put(pos, CellState.FILLED));

        return new BoardImpl(grid, showGrid, size);
    }

    private List<Position> generateAllPositions(int size) {
        List<Position> positions = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                positions.add(new Position(i, j));
            }
        }
        return positions;
    }

    private int countFilledCells(Map<Position, CellState> grid) {
        return (int) grid.values().stream().filter(state -> state == CellState.FILLED).count();
    }
}
