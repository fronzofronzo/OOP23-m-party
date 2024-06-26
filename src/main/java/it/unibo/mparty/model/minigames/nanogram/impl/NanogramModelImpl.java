package it.unibo.mparty.model.minigames.nanogram.impl;

import it.unibo.mparty.model.minigames.nanogram.api.Board;
import it.unibo.mparty.model.minigames.nanogram.api.BoardFactory;
import it.unibo.mparty.model.minigames.nanogram.api.Live;
import it.unibo.mparty.model.minigames.nanogram.api.NanogramModel;
import it.unibo.mparty.model.minigames.nanogram.util.CellState;
import it.unibo.mparty.model.minigames.nanogram.util.Difficulty;
import it.unibo.mparty.utilities.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NanogramModelImpl implements NanogramModel {

    private Map<Position, CellState> grid;
    private List<List<Integer>> rowHints = new ArrayList<>();
    private List<List<Integer>> columnHints = new ArrayList<>();

    private final BoardFactory boardFactory = new BoardFactoryImpl();
    private final Live lives = new LiveImpl();

    @Override
    public void initializeGame(Difficulty difficulty) {
        Board board;
        if (difficulty == Difficulty.SIMPLE) {
            board = boardFactory.createSimpleBoard(5, 30);
        } else {
            board = boardFactory.createHardBoard(10, 40, 20);
        }
        this.grid = board.getGrid();
        this.rowHints = board.calculateHints(true);
        this.columnHints = board.calculateHints(false);
        lives.reset();
    }

    @Override
    public CellState getCellState(int row, int column) {
        return grid.get(new Position(row, column));
    }

    @Override
    public void updateCellState(int row, int column, CellState state) {
        if (isMoveValid(row, column, state)) {
            grid.put(new Position(row, column), state);
        } else {
            lives.decrease();
            grid.compute(new Position(row, column), (k, correctState) -> correctState);
        }
    }

    private boolean isMoveValid(int row, int column, CellState state) {
        CellState currentCellState = grid.get(new Position(row, column));
        // Check if the cell is currently empty or marked, and if the new state is filled
        return (currentCellState == CellState.EMPTY || currentCellState == CellState.MARKED) && state == CellState.FILLED;
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
    public boolean isGameComplete() {
        for (Map.Entry<Position, CellState> entry : grid.entrySet()) {
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
