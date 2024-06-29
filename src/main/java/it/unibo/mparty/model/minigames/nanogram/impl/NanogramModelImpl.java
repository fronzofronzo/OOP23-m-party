package it.unibo.mparty.model.minigames.nanogram.impl;

import it.unibo.mparty.model.minigames.nanogram.board.api.Board;
import it.unibo.mparty.model.minigames.nanogram.board.api.BoardFactory;
import it.unibo.mparty.model.minigames.nanogram.api.Live;
import it.unibo.mparty.model.minigames.nanogram.api.NanogramModel;
import it.unibo.mparty.model.minigames.nanogram.board.impl.BoardFactoryImpl;
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

    private static final int SIMPLE_SIZE = 5;
    private static final int FILL = 60;
    private static final int HARD_SIZE = 10;
    private static final int SHOW_PERCENTAGE = 30;
    private static final int LIVES = 3;


    private final Map<Position, Boolean> selectedBoard; //todo: pensare a come poter utilizzare Board (setPosition, state)
    private final BoardFactory boardFactory;
    private final Live lives;

    private final List<List<Integer>> rowHints = new ArrayList<>();
    private final List<List<Integer>> columnHints = new ArrayList<>();
    private Board board;

    public NanogramModelImpl() {
        selectedBoard = new HashMap<>();
        boardFactory = new BoardFactoryImpl();
        lives = new LiveImpl(LIVES);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initializeGame(final Difficulty difficulty) {
        if (difficulty == Difficulty.SIMPLE) {
            this.board = this.boardFactory.createSimpleBoard(SIMPLE_SIZE, FILL);
        } else {
            this.board = this.boardFactory.createHardBoard(HARD_SIZE, FILL, SHOW_PERCENTAGE);
        }
        System.out.println("board: "+board);
        //this.rowHints = this.board.calculateHints(true); todo
        //this.columnHints = this.board.calculateHints(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getBoardSize() {
        return this.board.getSize();
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
    public void fillSelectedBoard(final int row, final int column, final boolean state) {
        if (isMoveValid(row, column, state)) {
            this.selectedBoard.put(new Position(row, column), state);
        } else {
            lives.decrease();
            this.selectedBoard.put(new Position(row, column), !state);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMoveValid(final int row, final int column, final boolean state) {
        return state == this.board.getCellState(new Position(row, column));
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
