package it.unibo.mparty.model.minigames.nanogram.impl;

import it.unibo.mparty.model.minigames.nanogram.board.api.Board;
import it.unibo.mparty.model.minigames.nanogram.board.api.BoardFactory;
import it.unibo.mparty.model.minigames.nanogram.api.Live;
import it.unibo.mparty.model.minigames.nanogram.api.NanogramModel;
import it.unibo.mparty.model.minigames.nanogram.board.api.SimpleBoard;
import it.unibo.mparty.model.minigames.nanogram.board.impl.BoardFactoryImpl;
import it.unibo.mparty.model.minigames.nanogram.board.impl.BoardImpl;
import it.unibo.mparty.model.minigames.nanogram.util.Difficulty;
import it.unibo.mparty.utilities.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the {@link NanogramModel} interface representing the model for a Nanogram game.
 */
public class NanogramModelImpl implements NanogramModel {

    private static final int SIMPLE_SIZE = 5;
    private static final double FILL = 0.6;
    private static final int HARD_SIZE = 10;
    private static final int SHOW_PERCENTAGE = 30;
    private static final int LIVES = 3;


    private final Board hitBoard; //todo: pensare a come poter utilizzare Board (setPosition, state)
    private final BoardFactory boardFactory;
    private final Live lives;

    private List<List<Integer>> rowHints = new ArrayList<>();
    private List<List<Integer>> columnHints = new ArrayList<>();
    private final Board solutionBoard;

    public NanogramModelImpl(final Difficulty difficulty) {
        boardFactory = new BoardFactoryImpl();
        lives = new LiveImpl(LIVES);

        switch (difficulty) {
            case SIMPLE -> {
                this.solutionBoard = this.boardFactory.createSimpleBoard(SIMPLE_SIZE, FILL);
                this.hitBoard = new BoardImpl(SIMPLE_SIZE);
            }
            case HARD -> {
                this.solutionBoard = this.boardFactory.createHardBoard(HARD_SIZE, FILL, SHOW_PERCENTAGE);
                this.hitBoard = new BoardImpl(SIMPLE_SIZE);
            }
            default -> {
                this.solutionBoard = this.boardFactory.createSimpleBoard(SIMPLE_SIZE, FILL);
                this.hitBoard = new BoardImpl(SIMPLE_SIZE);
            }
        }
        System.out.println("board: \n" + solutionBoard);
        this.rowHints = ((SimpleBoard) this.solutionBoard).getHints(true);
        this.columnHints = ((SimpleBoard) this.solutionBoard).getHints(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getBoardSize() {
        return this.solutionBoard.getSize();
    }

    @Override
    public List<Position> getFilledCells() {
        System.out.println("filled: \n" + this.solutionBoard.getFilledCells() + " \nsize: " + this.solutionBoard.getFilledCells().size() ); //todo: remove
        return this.solutionBoard.getFilledCells();
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
     *
     * @return
     */
    @Override
    public boolean checkAndSelectCell(final int row, final int column, final boolean state) {
        if (state == this.solutionBoard.getCellState(new Position(row, column))) {
            this.hitBoard.setCellState(new Position(row, column), state);
            return true;
        } else {
            lives.decrease();
            this.hitBoard.setCellState(new Position(row, column), !state);
            return false;
        }
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
        return false; //todo
//        return board.getGrid().entrySet().stream()
//                .filter(e -> e.getValue().equals(CellState.FILLED))
//                .allMatch(e -> CellState.FILLED.equals(this.selectedBoard.get(e.getKey())));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isGameOver() {
        return this.lives.isDeath();
    }
}
