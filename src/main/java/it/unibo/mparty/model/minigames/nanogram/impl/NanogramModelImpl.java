package it.unibo.mparty.model.minigames.nanogram.impl;

import it.unibo.mparty.model.minigames.nanogram.api.Board;
import it.unibo.mparty.model.minigames.nanogram.api.Live;
import it.unibo.mparty.model.minigames.nanogram.api.NanogramModel;
import it.unibo.mparty.model.minigames.nanogram.api.SimpleBoard;
import it.unibo.mparty.utilities.Position;

import java.util.List;
import java.util.stream.IntStream;

public class NanogramModelImpl implements NanogramModel {

    private static final int SIZE_SIMPLE_BOARD = 5;
    private static final int SIMPLE_FILL_PERCENTAGE = 60;
    private final Live lives;
    private final List<List<Integer>> rowHints;
    private final List<List<Integer>> columnHints;
    private final SimpleBoard solutionBoard;
    private final Board hittedBoard;

    public NanogramModelImpl() {
        this.lives = new LiveImpl();
        this.solutionBoard = new SimpleBoardImpl(SIZE_SIMPLE_BOARD, SIMPLE_FILL_PERCENTAGE);
        this.hittedBoard = new BoardImpl(SIZE_SIMPLE_BOARD);
        this.rowHints = this.solutionBoard.generateHints(true);
        this.columnHints = this.solutionBoard.generateHints(false);
        this.lives.reset();
    }

    @Override
    public boolean getSolutionCellState(final int x, final int y) {
        return this.solutionBoard.getState(new Position(x, y));
    }

    @Override
    public int getLives() {
        return this.lives.getLive();
    }

    @Override
    public void hitCell(final int x, final int y, final boolean state) {
        this.hittedBoard.setCellState(new Position(x, y), state);
    }

    @Override
    public boolean isMoveValid(final int x, final int y, final boolean state) {
        Position position = new Position(x, y);
        return hittedBoard.getCellState(position) == solutionBoard.getState(position);
    }

    @Override
    public void updateLives(final int lives) {
        this.lives.update(lives);
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
        return IntStream.range(0, SIZE_SIMPLE_BOARD)
                .boxed().flatMap(row -> IntStream.range(0, SIZE_SIMPLE_BOARD)
                        .mapToObj(col -> new Position(row, col)))
                .filter(solutionBoard::getState)
                .allMatch(hittedBoard::getCellState);
    }

    @Override
    public boolean isGameOver() {
        return this.lives.isDeath();
    }
}
