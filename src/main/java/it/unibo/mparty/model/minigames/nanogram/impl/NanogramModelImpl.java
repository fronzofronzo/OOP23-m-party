package it.unibo.mparty.model.minigames.nanogram.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.unibo.mparty.model.minigames.nanogram.api.BoardFactory;
import it.unibo.mparty.model.minigames.nanogram.api.Live;
import it.unibo.mparty.model.minigames.nanogram.api.NanogramModel;
import it.unibo.mparty.model.minigames.nanogram.util.CellState;
import it.unibo.mparty.model.minigames.nanogram.util.Difficulty;
import it.unibo.mparty.model.minigames.nanogram.util.Pair;

public class NanogramModelImpl implements NanogramModel {

    private Map<Pair<Integer, Integer>, CellState> grid = new HashMap<>();
    private List<List<Integer>> rowHints = new LinkedList<>();
    private List<List<Integer>> columnHints = new LinkedList<>();


    private BoardFactory boardFactory = new BoardFactoryImpl();
    private Live lives = new LiveImpl();

    @Override
    public void initializeGame(Difficulty difficulty) {
        if (difficulty == Difficulty.SIMPLE){
            boardFactory.createSimpleBoard(5, 30);
        } else {
            boardFactory.createHardBoard(10,40, 20);
        }
        setRowHints();
        setColumnHints();
        lives.reset();
    }

    @Override
    public CellState getCellState(int row, int column) {
        return grid.get(new Pair<>(row, column));
    }

    @Override
    public void updateCellState(int row, int column, CellState state) {
        if (isMoveValid()) {
            grid.put(new Pair<>(row, column), state);
            autoMarkRemainingCells(row, column);
        } else {
            lives.decrease();

            CellState correctState = grid.get(new Pair<>(row, column));
            grid.put(new Pair<>(row, column), correctState);
        }
    }

    private void autoMarkRemainingCells(int row, int column) {
        boolean rowComplete = true;
        for (int j = 0; j < grid.size(); j++) {
            if (grid.get(new Pair<>(row, j)) == CellState.EMPTY) {
                rowComplete = false;
                break;
            }
        }
        if (rowComplete) {
            for (int j = 0; j < grid.size(); j++) {
                if (grid.get(new Pair<>(row, j)) == CellState.EMPTY) {
                    grid.put(new Pair<>(row, j), CellState.MARKED);
                }
            }
        }

        boolean columnComplete = true;
        for (int i = 0; i < grid.size(); i++) {
            if (grid.get(new Pair<>(i, column)) == CellState.EMPTY) {
                columnComplete = false;
                break;
            }
        }
        if (columnComplete) {
            for (int i = 0; i < grid.size(); i++) {
                if (grid.get(new Pair<>(i, column)) == CellState.EMPTY) {
                    grid.put(new Pair<>(i, column), CellState.MARKED);
                }
            }
        }
    }

    private void setRowHints() {
        rowHints.clear();
        for (int i = 0; i < grid.size(); i++) {
            List<Integer> hints = new LinkedList<>();
            int count = 0;
            for (int j = 0; j < grid.size(); j++) {
                if (grid.get(new Pair<>(i, j)) == CellState.FILLED) {
                    count++;
                } else if (count > 0) {
                    hints.add(count);
                    count = 0;
                }
            }

            if (count > 0) {
                hints.add(count);
            }
            rowHints.add(hints);
        }
    }

    private void setColumnHints() {
        columnHints.clear();
        for (int j = 0; j < grid.size(); j++) {
            List<Integer> hints = new LinkedList<>();
            int count = 0;
            for (int i = 0; i < grid.size(); i++) {
                if (grid.get(new Pair<>(i, j)) == CellState.FILLED) {
                    count++;
                } else if (count > 0) {
                    hints.add(count);
                    count = 0;
                }
            }
            if (count > 0) {
                hints.add(count);
            }
            columnHints.add(hints);
        }
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
    public boolean isMoveValid() {
        return false;
    }

//    @Override
//    public boolean isMoveValid(int row, int column, CellState state) {
//        CellState originalState = grid.get(new Pair<>(row, column));
//        grid.put(new Pair<Integer, Integer>(row, column), state);
//
//        if (!checkHints(row, true)) {
//            grid.put(new Pair<Integer, Integer>(row, column), originalState);
//            return false;
//        }
//
//        if (!checkHints(column, false)) {
//            grid.put(new Pair<Integer, Integer>(row, column), originalState);
//            return false;
//        }
//
//        grid.put(new Pair<Integer, Integer>(row, column), originalState);
//        return true;
//    }

    private boolean checkHints(int index, boolean isRow) {
        List<Integer> hints = isRow ? rowHints.get(index) : columnHints.get(index);
        int size = isRow ? columnHints.size() : rowHints.size();

        List<Integer> currentSequence = new LinkedList<>();
        int count = 0;

        for (int i = 0; i < size; i++) {
            CellState state = isRow ? grid.get(new Pair<>(index, i)) : grid.get(new Pair<>(i, index));
            if (state == CellState.FILLED) {
                count++;
            } else if (count > 0) {
                currentSequence.add(count);
                count = 0;
            }
        }

        if (count > 0) {
            currentSequence.add(count);
        }

        return currentSequence.equals(hints);
    }

    @Override
    public boolean isGameComplete() {
        for (Map.Entry<Pair<Integer, Integer>, CellState> entry : grid.entrySet()) {
            if (entry.getValue() == CellState.EMPTY || entry.getValue() == CellState.MARKED) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isGameOver() {
        return lives.isDeath();
    }

}