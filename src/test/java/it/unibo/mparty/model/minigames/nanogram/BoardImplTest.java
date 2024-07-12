package it.unibo.mparty.model.minigames.nanogram;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.mparty.model.minigames.nanogram.board.api.Board;
import it.unibo.mparty.model.minigames.nanogram.board.impl.BoardImpl;
import it.unibo.mparty.utilities.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Test class for {@link BoardImpl}.
 */
class BoardImplTest {

    private static final int BOARD_SIZE = 3;
    private static final int ZERO = 0;
    private static final int FIRST = 1;
    private static final int SECOND = 2;
    private static final int THIRD = 3;
    private Board board;

    /**
     * Sets up the test environment before each test.
     */
    @BeforeEach
    void setUp() {
        this.board = new BoardImpl(BOARD_SIZE);
    }

    /**
     * Tests the {@code setCellState} method.
     * Ensures that the state of specific cells can be set and retrieved correctly.
     */
    @Test
    @SuppressFBWarnings(value = "UWF_FIELD_NOT_INITIALIZED_IN_CONSTRUCTOR",
            justification = "The field is initialized in the setUp method, which is run before each test.")
    void setCellState() {
        final Position position1 = new Position(ZERO, ZERO);
        final Position position2 = new Position(ZERO, FIRST);
        final Position position3 = new Position(FIRST, SECOND);
        final Position position4 = new Position(SECOND, THIRD);
        final Position position5 = new Position(THIRD, ZERO);

        this.board.setCellState(position1, true);
        this.board.setCellState(position2, false);
        this.board.setCellState(position3, true);
        this.board.setCellState(position4, false);
        this.board.setCellState(position5, true);

        assertTrue(this.board.getCellState(position1));
        assertFalse(this.board.getCellState(position2));
        assertTrue(this.board.getCellState(position3));
        assertFalse(this.board.getCellState(position4));
        assertTrue(this.board.getCellState(position5));
    }
}
