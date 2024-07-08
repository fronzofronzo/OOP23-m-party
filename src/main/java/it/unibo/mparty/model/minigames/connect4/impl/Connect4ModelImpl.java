package it.unibo.mparty.model.minigames.connect4.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import it.unibo.mparty.model.minigames.connect4.api.Connect4Model;
import it.unibo.mparty.utilities.Connect4Directions;
import it.unibo.mparty.utilities.Pair;
import it.unibo.mparty.utilities.Position;

/**
 * This class implements {@link Connect4Model} interface.
 */
public class Connect4ModelImpl implements Connect4Model {

    private static final int ROW_NUMBER = 6;
    private static final int COLUMN_NUMBER = 8;
    private static final int COINS_FROM_DRAW = 5;
    private static final int COINS_FROM_WIN = 10;
    private static final int CHECK_START = -3;
    private String player1;
    private String player2;
    private String turnPlayer;
    private Position lastSelected;
    private int coinsWon = 0;
    private Map<Position, String> selectedMap;
    private List<Position> checkList;

    /**
     * Construct a new istance of {@link Connect4ModelImpl}.
     */
    public Connect4ModelImpl() {
        selectedMap = new HashMap<>();
        lastSelected = new Position(-1, -1);
        checkList = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isOver() {
        if (isADraw()) {
            turnPlayer = player2;
            coinsWon = COINS_FROM_DRAW;
            return true;
        }
        if (hasWon(lastSelected.getX(), lastSelected.getY())) {
            coinsWon = COINS_FROM_WIN;
            return true;
        }
        manageTurn();
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pair<String, Integer> getResult() {
        return new Pair<String, Integer>(getTurnPlayer(), coinsWon);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUpPlayers(final List<String> players) {
        player1 = players.get(0);
        player2 = players.get(1);
        turnPlayer = player1;
    }

    private boolean isADraw() {
        return selectedMap.size() >= (ROW_NUMBER * COLUMN_NUMBER);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTurnPlayer() {
        return this.turnPlayer;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addDisc(final int column) {
        int row = getAvailableRow(column);
        if (row == -1) {
            return false;
        }
        lastSelected = new Position(row, column);
        selectedMap.put(lastSelected, getTurnPlayer());
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getAvailableRow(final int column) {
        return IntStream.range(0, ROW_NUMBER)
        .map(it -> ROW_NUMBER - 1 - it)
        .filter(i -> !selectedMap.containsKey(new Position(i, column)))
        .findFirst()
        .orElse(-1);
    }

    private void manageTurn() {
        if (getTurnPlayer() == player1) {
            turnPlayer = player2;
        } else {
            turnPlayer = player1;
        }
        checkList.clear();
    }

    private boolean hasWon(final int i, final int j) {
        return Stream.of(Connect4Directions.values())
        .anyMatch(dir -> checkDirections(i, j, dir));
    }

    private boolean checkDirections(final int i, final int j, final Connect4Directions direction) {
        return IntStream.rangeClosed(CHECK_START, 3)
        .map(off -> countMatches(i, j, direction, off))
        .max().getAsInt() >= 4;
    }

    private int countMatches(final int i, final int j, final Connect4Directions direction, final int offset) {
        Position check = new Position(i + offset * direction.getPosition().getX(), j + offset * direction.getPosition().getY());
        if (selectedMap.containsKey(check) && selectedMap.get(check).equals(getTurnPlayer()) && isAvailable(check)) {
            checkList.add(check);
        } else {
            checkList.clear();
        }
        return checkList.size();
    }

    private boolean isAvailable(final Position pos) {
        return pos.getX() >= 0 && pos.getX() < ROW_NUMBER && pos.getY() >= 0 && pos.getY() < COLUMN_NUMBER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPlayer1() {
        return this.player1;
    }

}
