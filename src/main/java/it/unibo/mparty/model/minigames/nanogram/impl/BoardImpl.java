package it.unibo.mparty.model.minigames.nanogram.impl;

import it.unibo.mparty.model.minigames.nanogram.api.Board;
import it.unibo.mparty.model.minigames.nanogram.util.CellState;
import it.unibo.mparty.utilities.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BoardImpl implements Board {
    private final Map<Position, CellState> grid;
    private final Map<Position, CellState> showGrid;
    private final int size;

    public BoardImpl(Map<Position, CellState> grid, Map<Position, CellState> showGrid, int size) {
        this.grid = grid;
        this.showGrid = showGrid;
        this.size = size;
    }

    @Override
    public Map<Position, CellState> getGrid() {
        return grid;
    }

    @Override
    public Map<Position, CellState> getShowGrid() {
        return showGrid;
    }

    @Override
    public List<Integer> getHints() {
        return calculateHints(true).get(0); // Placeholder implementation, return specific hints based on game logic
    }

    @Override
    public CellState getCellState(int x, int y) {
        return grid.get(new Position(x, y));
    }

    @Override
    public List<List<Integer>> calculateHints(boolean isRow) {
        List<List<Integer>> hintsList = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            List<Integer> hints = new ArrayList<>();
            int count = 0;

            for (int j = 0; j < size; j++) {
                Position pos = isRow ? new Position(i, j) : new Position(j, i);
                CellState currentState = grid.get(pos);

                if (currentState == CellState.FILLED) {
                    count++;
                } else if (count > 0) {
                    hints.add(count);
                    count = 0;
                }
            }

            if (count > 0) {
                hints.add(count);
            }

            hintsList.add(hints);
        }

        return hintsList;
    }
}
