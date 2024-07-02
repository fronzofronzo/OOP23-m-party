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
    private static final double SIMPLE_FILL_PERCENTAGE = 0.6;
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
    public boolean checkAndSelectCell(int x, int y, boolean state) {
        if (state == this.solutionBoard.getState(new Position(x,y))) {
            this.hittedBoard.setCellState(new Position(x, y), state);
            return true;
        } else {
            lives.decrease();
            this.hittedBoard.setCellState(new Position(x, y), !state);
            return false;
        }
    }

    @Override
    public int getLives() {
        return this.lives.getLive();
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
    public int getBoardSize() {
        return this.solutionBoard.getSize();
    }

    @Override
    public boolean isGameComplete() {
        return IntStream.range(0, SIZE_SIMPLE_BOARD)
                .boxed().flatMap(row -> IntStream.range(0, SIZE_SIMPLE_BOARD)
                        .mapToObj(col -> new Position(row, col)))
                .filter(solutionBoard::getState)
                .allMatch(hittedBoard::getState);
    }

    @Override
    public boolean isGameOver() {
        return this.lives.isDeath();
    }
}
