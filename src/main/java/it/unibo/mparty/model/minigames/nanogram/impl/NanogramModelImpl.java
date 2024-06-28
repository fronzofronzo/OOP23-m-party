package it.unibo.mparty.model.minigames.nanogram.impl;

import it.unibo.mparty.model.minigames.nanogram.api.Board;
import it.unibo.mparty.model.minigames.nanogram.api.BoardFactory;
import it.unibo.mparty.model.minigames.nanogram.api.Live;
import it.unibo.mparty.model.minigames.nanogram.api.NanogramModel;
import it.unibo.mparty.model.minigames.nanogram.util.CellState;
import it.unibo.mparty.model.minigames.nanogram.util.Difficulty;
import it.unibo.mparty.utilities.Position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of the {@link NanogramModel} interface representing the model for a Nanogram game.
 */
public class NanogramModelImpl implements NanogramModel {

    private final Map<Position, CellState> grid = new HashMap<>();
    private final BoardFactory boardFactory = new BoardFactoryImpl();
    private final Live lives = new LiveImpl();

    private List<List<Integer>> rowHints = new ArrayList<>();
    private List<List<Integer>> columnHints = new ArrayList<>();
    private Board board;

    /**
     * {@inheritDoc}
     */
    @Override
    public void initializeGame(final Difficulty difficulty) {
        if (difficulty == Difficulty.SIMPLE) {
            this.board = this.boardFactory.createSimpleBoard(5, 60);
        } else {
            this.board = this.boardFactory.createHardBoard(10, 40, 20);
        }
        this.rowHints = this.board.calculateHints(true);
        this.columnHints = this.board.calculateHints(false);
        this.lives.reset();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CellState getCellState(final int row, final int column) {
        return this.board.getGrid().get(new Position(row, column));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Board getBoard() {
        return this.board;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getLives() {
        return this.lives.getLive();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateCellState(final int row, final int column, final CellState state) {
        if (isMoveValid(row, column, state)) {
            this.grid.put(new Position(row, column), state);
        } else {
            updateLives(lives.getLive() - 1);
            this.grid.compute(new Position(row, column), (k, correctState) -> correctState);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateLives(final int lives) {
        this.lives.update(lives);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMoveValid(final int row, final int column, final CellState state) {
        return state.equals(this.board.getGrid().get(new Position(row, column)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<List<Integer>> getRowHints() {
        return this.rowHints;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<List<Integer>> getColumnHints() {
        return this.columnHints;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isGameComplete() {
        return board.getGrid().entrySet().stream()
                .filter(e -> e.getValue().equals(CellState.FILLED))
                .allMatch(e -> CellState.FILLED.equals(this.grid.get(e.getKey())));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isGameOver() {
        return this.lives.isDeath();
    }
}
