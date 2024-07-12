package it.unibo.mparty.model.minigames.nanogram.impl;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.mparty.model.minigames.nanogram.game.api.NanogramModel;
import it.unibo.mparty.model.minigames.nanogram.game.impl.NanogramModelImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


/**
 * Test class for {@link NanogramModelImpl}.
 */
class NanogramModelImplTest {

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
     * Tests the {@code isGameComplete} method.
     */
    @Test
    @SuppressFBWarnings(value = "UWF_FIELD_NOT_INITIALIZED_IN_CONSTRUCTOR",
            justification = "The field is initialized in the setUp method, which is run before each test.")
    void isGameComplete() {
        assertFalse(this.model.isGameComplete());

        for (int i = 0; i < this.model.getBoardSize(); i++) {
            for (int j = 0; j < this.model.getBoardSize(); j++) {
                this.model.checkAndSelectCell(i, j, true);
            }
        }

        assertTrue(this.model.isGameComplete());
    }
}
