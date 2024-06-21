package it.unibo.mparty.model.minigames.nanogram.impl;

import java.util.ArrayList;
import java.util.List;

import it.unibo.mparty.model.minigames.nanogram.api.NanogramModel;
import it.unibo.mparty.model.minigames.nanogram.util.CellState;

public class NanogramModelImpl implements NanogramModel {

    private static final int INITIAL_LIVES = 3;
    private CellState[][] grid;
    private List<List<Integer>> rowHints = new ArrayList<>();
    private List<List<Integer>> columnHints = new ArrayList<>();
    private int lives;

    @Override
    public void initializeGameGrid(int size) {
        grid = new CellState[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = Math.random() < 0.5 ? CellState.FILLED : CellState.EMPTY;
            }
        }
        setHints();
        lives = INITIAL_LIVES;
    }

    @Override
    public CellState getCellState(int row, int column) {
        return grid[row][column];
    }

    @Override
    public void updateCellState(int row, int column, CellState state) {
        if (isMoveValid(row, column, state)) {
            grid[row][column] = state;
            System.out.println(grid[row][column].toString());
            autoMarkRemainingCells(row, column);
        } else {
            lives--;
            CellState correctState = grid[row][column];
            grid[row][column] = correctState;
        }
    }

    private void autoMarkRemainingCells(int row, int column) {
        if (isRowComplete(row)) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[row][j] == CellState.EMPTY) {
                    grid[row][j] = CellState.MARKED;
                }
            }
        }

        if (isColumnComplete(column)) {
            for (int i = 0; i < grid.length; i++) {
                if (grid[i][column] == CellState.EMPTY) {
                    grid[i][column] = CellState.MARKED;
                }
            }
        }
    }

    private void setHints() {
        rowHints.clear();
        columnHints.clear();
        for (int i = 0; i < grid.length; i++) {
            rowHints.add(generateHints(grid[i]));
            columnHints.add(generateHints(getColumn(i)));
        }
    }

    private List<Integer> generateHints(CellState[] line) {
        List<Integer> hints = new ArrayList<>();
        int count = 0;
        for (CellState cell : line) {
            if (cell == CellState.FILLED) {
                count++;
            } else if (count > 0) {
                hints.add(count);
                count = 0;
            }
        }
        if (count > 0) {
            hints.add(count);
        }
        return hints;
    }

    private CellState[] getColumn(int columnIndex) {
        CellState[] column = new CellState[grid.length];
        for (int i = 0; i < grid.length; i++) {
            column[i] = grid[i][columnIndex];
        }
        return column;
    }

    private boolean isRowComplete(int row) {
        for (int j = 0; j < grid.length; j++) {
            if (grid[row][j] == CellState.EMPTY) {
                return false;
            }
        }
        return true;
    }

    private boolean isColumnComplete(int column) {
        for (int i = 0; i < grid.length; i++) {
            if (grid[i][column] == CellState.EMPTY) {
                return false;
            }
        }
        return true;
    }

    @Override
    public List<List<Integer>> getRowHints() {
        return rowHints;
    }

    @Override
    public List<List<Integer>> getColumnHints() {
        return columnHints;
    }

    @Override
    public boolean isMoveValid(int row, int column, CellState state) {
        CellState originalState = grid[row][column];
        grid[row][column] = state;

        boolean valid = checkHints(row, true) && checkHints(column, false);

        grid[row][column] = originalState;
        return valid;
    }

    private boolean checkHints(int index, boolean isRow) {
        List<Integer> hints = isRow ? rowHints.get(index) : columnHints.get(index);
        CellState[] line = isRow ? grid[index] : getColumn(index);

        List<Integer> currentSequence = generateHints(line);

        return currentSequence.equals(hints);
    }

    @Override
    public boolean isGameComplete() {
        for (CellState[] row : grid) {
            for (CellState cell : row) {
                if (cell == CellState.EMPTY || cell == CellState.MARKED) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int getLives() {
        return lives;
    }

    @Override
    public boolean isGameOver() {
        return lives <= 0;
    }
}
