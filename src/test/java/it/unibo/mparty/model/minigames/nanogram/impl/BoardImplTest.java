package it.unibo.mparty.model.minigames.nanogram.impl;

import it.unibo.mparty.model.minigames.nanogram.api.Board;
import it.unibo.mparty.utilities.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardImplTest {

    private static final int BOARD_SIZE = 3;
    private static final int ZERO = 0;
    private static final int FIRST = 1;
    private static final int SECOND = 2;
    private static final int THIRD = 3;
    private Board board;

    @BeforeEach
    void setUp() {
        this.board = new BoardImpl(BOARD_SIZE);
    }

    @Test
    void setCellState() {
        Position position1 = new Position(ZERO, ZERO);
        Position position2 = new Position(ZERO,FIRST);
        Position position3 = new Position(FIRST,SECOND);
        Position position4 = new Position(SECOND,THIRD);
        Position position5 = new Position(THIRD, ZERO);

        this.board.setCellState(position1, true);
        this.board.setCellState(position2, false);
        this.board.setCellState(position3, true);
        this.board.setCellState(position4, false);
        this.board.setCellState(position5, true);

        assertTrue(this.board.getState(position1));
        assertFalse(this.board.getState(position2));
        assertTrue(this.board.getState(position3));
        assertFalse(this.board.getState(position4));
        assertTrue(this.board.getState(position5));
    }
}