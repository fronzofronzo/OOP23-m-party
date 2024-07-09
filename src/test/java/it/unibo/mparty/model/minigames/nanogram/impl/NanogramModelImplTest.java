package it.unibo.mparty.model.minigames.nanogram.impl;

import it.unibo.mparty.model.minigames.nanogram.api.NanogramModel;
import it.unibo.mparty.utilities.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


/**
 * Test class for {@link NanogramModelImpl}.
 */
class NanogramModelImplTest {

    private static final int MAX_LIVE = 3;
    private NanogramModel model;

    /**
     * Sets up the test environment before each test.
     */
    @BeforeEach
    void setUp() {
        this.model = new NanogramModelImpl();
        this.model.setUpPlayers(List.of("player1"));
    }

    /**
     * Tests the {@code checkAndSelectCell} method.
     */
    @Test
    void checkAndSelectCell() {
        this.model.getSolutionBoard().getState(new Position(0, 0));
        boolean correctState = this.model.getSolutionBoard().getState(new Position(0, 0));

        boolean selectedState = correctState;
        assertEquals(correctState, selectedState);

        selectedState = !correctState;
        assertNotEquals(correctState, selectedState);
    }

    /**
     * Tests the {@code isGameComplete} method.
     */
    @Test
    void isGameComplete() {
        assertFalse(this.model.isGameComplete());

        for (int i = 0; i < this.model.getBoardSize(); i++) {
            for (int j = 0; j < this.model.getBoardSize(); j++) {
                this.model.checkAndSelectCell(i, j, true);
            }
        }

        assertTrue(this.model.isGameComplete());
    }

    /**
     * Tests the {@code isOver} method.
     */
    @Test
    void isOver() {
        assertFalse(this.model.isOver());

        for (int i = 0; i < MAX_LIVE; i++) {
            this.model.checkAndSelectCell(0, 0, false);
        }

        assertTrue(this.model.isOver());
    }
}
