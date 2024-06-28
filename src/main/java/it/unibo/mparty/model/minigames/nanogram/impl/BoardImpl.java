package it.unibo.mparty.model.minigames.nanogram.impl;

import it.unibo.mparty.model.minigames.nanogram.api.Board;
import it.unibo.mparty.model.minigames.nanogram.util.CellState;
import it.unibo.mparty.utilities.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Implementation of the {@link Board} interface for a Nanogram game.
 * This class represents the game board and provides methods to interact with the board state.
 */
public class BoardImpl implements Board {
    private final Map<Position, CellState> grid;
    private final Map<Position, CellState> showGrid;
    private final int size;

    /**
     * Constructs a new BoardImpl instance with the specified grid, showGrid, and size.
     *
     * @param grid     the map representing the complete grid of cell states.
     * @param showGrid the map representing the visible grid of cell states.
     * @param size     the size of the board (assuming it's a square board).
     */
    public BoardImpl(Map<Position, CellState> grid, Map<Position, CellState> showGrid, int size) {
        this.grid = grid;
        this.showGrid = showGrid;
        this.size = size;
    }

    @Override
    public Map<Position, CellState> getGrid() {
        return this.grid;
    }

    @Override
    public Map<Position, CellState> getShowGrid() {
        return this.showGrid;
    }

    @Override
    public CellState getCellState(int x, int y) {
        return this.grid.get(new Position(x, y));
    }

    @Override
    public List<List<Integer>> calculateHints(boolean isRow) {
        List<List<Integer>> hintsList = new ArrayList<>();

        for (int i = 0; i < this.size; i++) {
            List<Integer> hints = new ArrayList<>();
            int count = 0;

            for (int j = 0; j < this.size; j++) {
                Position pos = isRow ? new Position(i, j) : new Position(j, i);
                CellState currentState = this.grid.get(pos);

                if (currentState == CellState.FILLED) {
                    count++;
                } else if (count > 0) {
                    hints.add(count);
                    count = 0;
                }
            }

            if (count > 0) {
                hints.add(count);
            } else if (hints.isEmpty()) {
                hints.add(0);
            }

            hintsList.add(hints);
        }
        return hintsList;
    }
}
