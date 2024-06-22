package it.unibo.mparty.model.minigames.nanogram.impl;

import it.unibo.mparty.model.minigames.nanogram.api.Board;
import it.unibo.mparty.model.minigames.nanogram.util.CellState;
import it.unibo.mparty.model.minigames.nanogram.util.Pair;
import javafx.scene.control.Cell;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardImpl implements Board {
    private final Map<Pair<Integer, Integer>, CellState> grid;
    private final Map<Pair<Integer,Integer>, CellState> showGrid;

    public BoardImpl(Map<Pair<Integer, Integer>, CellState> grid, Map<Pair<Integer, Integer>, CellState> showGrid) {
        this.grid = grid;
        this.showGrid = showGrid;
    }

    @Override
    public Map<Pair<Integer, Integer>, CellState> getGrid() {
        return grid;
    }

    @Override
    public Map<Pair<Integer, Integer>, CellState> getShowGrid() {
        return showGrid;
    }

    @Override
    public List<Integer> getHints() {
        return List.of();
    }

    @Override
    public CellState getCellState(final int x, final int y) {
        return grid.get(new Pair<>(x,y));
    }
}
