package it.unibo.mparty.model.minigames.nanogram.impl;

import it.unibo.mparty.model.minigames.nanogram.api.Board;
import it.unibo.mparty.model.minigames.nanogram.api.BoardFactory;
import it.unibo.mparty.model.minigames.nanogram.api.Live;
import it.unibo.mparty.model.minigames.nanogram.api.NanogramModel;
import it.unibo.mparty.model.minigames.nanogram.util.CellState;
import it.unibo.mparty.model.minigames.nanogram.util.Difficulty;
import it.unibo.mparty.utilities.Position;

import java.util.*;

/**
 * Implementation of the {@link NanogramModel} interface representing the model for a Nanogram game.
 */
public class NanogramModelImpl implements NanogramModel {

    private Map<Position, CellState> grid = new HashMap<>();
    private List<List<Integer>> rowHints = new ArrayList<>();
    private List<List<Integer>> columnHints = new ArrayList<>();

    private final BoardFactory boardFactory = new BoardFactoryImpl();
    private final Live lives = new LiveImpl();
    private Board board;

    @Override
    public void initializeGame(Difficulty difficulty) {
        if (difficulty == Difficulty.SIMPLE) {
            this.board = this.boardFactory.createSimpleBoard(5, 30);
        } else {
            this.board = this.boardFactory.createHardBoard(10, 40, 20);
        }
        this.rowHints = this.board.calculateHints(true);
        this.columnHints = this.board.calculateHints(false);
        this.lives.reset();
    }

    @Override
    public CellState getCellState(int row, int column) {
        return this.board.getGrid().get(new Position(row, column));
    }

    @Override
    public Board getBoard() {
        return this.board;
    }

    @Override
    public int getLives() {
        return this.lives.getLive();
    }

    @Override
    public void updateCellState(int row, int column, CellState state) {
        if (isMoveValid(row, column, state)) {
            this.grid.put(new Position(row, column), state);
        } else {
            updateLives(lives.getLive() - 1);
            this.grid.compute(new Position(row, column), (k, correctState) -> correctState);
        }
    }

    @Override
    public void updateLives(int lives) {
        this.lives.update(lives);
    }

    @Override
    public boolean isMoveValid(int row, int column, CellState state) {
        return state.equals(this.board.getGrid().get(new Position(row, column)));
    }

    @Override
    public List<List<Integer>> getRowHints() {
        return this.rowHints;
    }

    @Override
    public List<List<Integer>> getColumnHints() {
        return this.columnHints;
    }

    @Override
    public boolean isGameComplete() {
        return board.getGrid().entrySet().stream()
                .filter(e -> e.getValue().equals(CellState.FILLED))
                .allMatch(e -> CellState.FILLED.equals(this.grid.get(e.getKey())));
    }

    @Override
    public boolean isGameOver() {
        return this.lives.isDeath();
    }
}
