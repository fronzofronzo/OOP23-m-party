package it.unibo.mparty.model.minigames.nanogram.impl;

import it.unibo.mparty.model.minigames.nanogram.board.api.SimpleBoard;
import it.unibo.mparty.model.minigames.nanogram.board.impl.SimpleBoardImpl;
import it.unibo.mparty.utilities.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Test class for {@link SimpleBoardImpl}.
 */
class SimpleBoardImplTest {

    private static final int SIZE = 10;
    private static final double FILL_PERCENTAGE = 0.6;
    private static final int NUM_TEST = 10;
    private SimpleBoard simpleBoard;

    /**
     * Sets up the test environment before each test.
     */
    @BeforeEach
    void setUp() {
        this.simpleBoard = new SimpleBoardImpl(SIZE, FILL_PERCENTAGE);
    }

    /**
     * Tests the creation and correct filling of the simple board.
     */
    @Test
    void testSimpleBoard() {
        Random random = new Random();
        random.doubles(NUM_TEST).forEach(p -> {
            this.simpleBoard = new SimpleBoardImpl(SIZE, p);
            long expectedCount = Math.round(SIZE * SIZE * p);

            long actualCount = IntStream.range(0, SIZE * SIZE)
                    .mapToObj(i -> new Position(i / SIZE, i % SIZE))
                    .filter(pos -> simpleBoard.getState(pos))
                    .count();

            assertEquals(expectedCount, actualCount);
        });
    }

    /**
     * Tests the generation of hints for the rows and columns of the board.
     */
    @Test
    void testGenerateHints() {
        List<List<Integer>> rowHints = simpleBoard.generateHints(true);
        List<List<Integer>> columnHints = simpleBoard.generateHints(false);

        assertFalse(rowHints.isEmpty());
        assertFalse(columnHints.isEmpty());

        assertEquals(SIZE, rowHints.size());
        assertEquals(SIZE, columnHints.size());

        for (List<Integer> hints : rowHints) {
            assertFalse(hints.isEmpty());
        }
        for (List<Integer> hints : columnHints) {
            assertFalse(hints.isEmpty());
        }
    }
}
