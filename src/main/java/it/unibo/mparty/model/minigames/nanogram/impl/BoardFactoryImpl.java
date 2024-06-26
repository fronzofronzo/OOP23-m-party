package it.unibo.mparty.model.minigames.nanogram.impl;

import it.unibo.mparty.model.minigames.nanogram.api.Board;
import it.unibo.mparty.model.minigames.nanogram.api.BoardFactory;
import it.unibo.mparty.model.minigames.nanogram.util.CellState;
import it.unibo.mparty.utilities.Pair;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BoardFactoryImpl implements BoardFactory {

    @Override
    public Board createSimpleBoard(final int size, final int fillPercentage) {
        final Map<Pair<Integer, Integer>, CellState> grid = new HashMap<>();
        final Map<Pair<Integer, Integer>, CellState> showGrid = new HashMap<>();

        int totalCells = size * size;
        int filledCellsCount = (int) Math.round(totalCells * (fillPercentage / 100.0));

        List<Pair<Integer, Integer>> positions = IntStream.range(0, size)
                .boxed()
                .flatMap(i -> IntStream.range(0, size).mapToObj(j -> new Pair<>(i, j)))
                .collect(Collectors.toList());

        Collections.shuffle(positions);
        positions.stream().limit(filledCellsCount).forEach(pos -> grid.put(pos, CellState.FILLED));
        positions.stream().skip(filledCellsCount).forEach(pos -> grid.put(pos, CellState.MARKED));

        positions.forEach(pos -> showGrid.put(pos, CellState.MARKED));

        return new BoardImpl(grid, showGrid);
    }

    @Override
    public Board createHardBoard(final int size, final int fillPercentage, final int showPercentage) {
        final Board grid = createSimpleBoard(size, fillPercentage);
        final Map<Pair<Integer, Integer>, CellState> showGrid = new HashMap<>(grid.getShowGrid());

        int filledCellsCount = (int) grid.getGrid().values().stream().filter(state -> state == CellState.FILLED).count();
        int showCellsCount = (int) Math.round(filledCellsCount * (showPercentage / 100.0));

        List<Pair<Integer, Integer>> filledPositions = grid.getGrid().entrySet().stream()
                .filter(entry -> entry.getValue() == CellState.FILLED)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        Collections.shuffle(filledPositions);
        filledPositions.stream().limit(showCellsCount).forEach(pos -> showGrid.put(pos, CellState.FILLED));

        return new BoardImpl(grid.getGrid(), showGrid);
    }
}
