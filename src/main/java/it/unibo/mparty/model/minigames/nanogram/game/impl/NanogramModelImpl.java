package it.unibo.mparty.model.minigames.nanogram.game.impl;

import it.unibo.mparty.model.minigames.MinigameType;
import it.unibo.mparty.model.minigames.nanogram.board.api.Board;
import it.unibo.mparty.model.minigames.nanogram.live.api.Live;
import it.unibo.mparty.model.minigames.nanogram.game.api.NanogramModel;
import it.unibo.mparty.model.minigames.nanogram.board.api.SimpleBoard;
import it.unibo.mparty.model.minigames.nanogram.board.impl.BoardImpl;
import it.unibo.mparty.model.minigames.nanogram.board.impl.SimpleBoardImpl;
import it.unibo.mparty.model.minigames.nanogram.live.impl.LiveImpl;
import it.unibo.mparty.utilities.Pair;
import it.unibo.mparty.utilities.Position;

import java.util.List;
import java.util.stream.IntStream;

/**
 * Implementation of the {@link NanogramModel} interface for managing the Nanogram game logic.
 * This class handles the game state, including the board, hints, lives, and game status.
 */
public class NanogramModelImpl implements NanogramModel {

    private static final double SIMPLE_FILL_PERCENTAGE = 0.6;
    private static final int SIZE_SIMPLE_BOARD = 5;
    private static final int COINS = 10;
    private static final int COIN_CALCULATION_FACTOR = 2;
    private final List<List<Integer>> rowHints;
    private final List<List<Integer>> columnHints;
    private final SimpleBoard solutionBoard;
    private final Board hittedBoard;
    private final Live lives;
    private String player;

    /**
     * Constructs a {@code NanogramModelImpl} instance initializing the game state with default parameters.
     */
    public NanogramModelImpl() {
        this.lives = new LiveImpl();
        this.solutionBoard = new SimpleBoardImpl(SIZE_SIMPLE_BOARD, SIMPLE_FILL_PERCENTAGE);
        this.hittedBoard = new BoardImpl(SIZE_SIMPLE_BOARD);
        this.rowHints = this.solutionBoard.generateHints(true);
        this.columnHints = this.solutionBoard.generateHints(false);
        this.lives.reset();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean checkAndSelectCell(final int x, final int y, final boolean state) {
        if (state == this.solutionBoard.getState(new Position(x, y))) {
            this.hittedBoard.setCellState(new Position(x, y), state);
            return true;
        } else {
            this.lives.decrease();
            this.hittedBoard.setCellState(new Position(x, y), !state);
            return false;
        }
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
    public int getBoardSize() {
        return this.solutionBoard.getSize();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SimpleBoard getSolutionBoard() {
        return solutionBoard;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isGameComplete() {
        return IntStream.range(0, SIZE_SIMPLE_BOARD)
                .boxed().flatMap(row -> IntStream.range(0, SIZE_SIMPLE_BOARD)
                        .mapToObj(col -> new Position(row, col)))
                .filter(this.solutionBoard::getState)
                .allMatch(this.hittedBoard::getState);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pair<String, Integer> getResult() {
        return switch (lives.getLive()) {
            case 1 -> new Pair<>(this.player, COINS / COIN_CALCULATION_FACTOR);
            case 2 -> new Pair<>(this.player, COINS - COIN_CALCULATION_FACTOR);
            case 3 -> new Pair<>(this.player, COINS);
            default -> new Pair<>(this.player, 0);
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUpPlayers(final List<String> players) {
        this.player = players.get(0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isOver() {
        return this.lives.isDeath() || this.isGameComplete();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return "nanogram";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MinigameType getType() {
        return MinigameType.SINGLE_PLAYER;
    }
}
