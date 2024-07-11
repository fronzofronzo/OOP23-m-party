package it.unibo.mparty.model.minigames.connect4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.mparty.model.minigames.connect4.api.Connect4Model;
import it.unibo.mparty.model.minigames.connect4.impl.Connect4ModelImpl;

/**
 * This class tests the {@link Connect4ModelImpl} class.
 */
class Connect4Test {
    private Connect4Model testModel;
    private static final int ROWS = 6;
    private static final int COLUMN_5 = 5;
    private static final int COLUMN_4 = 4;
    private static final int COLUMN_3 = 3;
    private static final int COLUMN_2 = 2;


    /**
     * Initialize the game before each test.
     */
    @BeforeEach
    public void initGame() {
        testModel = new Connect4ModelImpl();
        testModel.setUpPlayers(List.of("testPlayer1", "testPlayer2"));
    }

    /**
     * Test adding a disc in the minigame.
     */
    @Test
    void testAddDisc() {
        for (int i = 0; i < ROWS; i++) {
            assertTrue(testModel.addDisc(COLUMN_2));
        }
        assertFalse(testModel.addDisc(COLUMN_2));
    }

    /**
     * Test the horizontal win.
     */
    @Test
    void checkHorizontalWin() {
        testModel.addDisc(COLUMN_3);
        testModel.addDisc(COLUMN_3);
        testModel.addDisc(COLUMN_4);
        testModel.addDisc(COLUMN_3);
        testModel.addDisc(COLUMN_5);
        testModel.addDisc(COLUMN_3);
        testModel.addDisc(COLUMN_2);
        assertTrue(testModel.isOver());
        assertEquals("testPlayer1", testModel.getTurnPlayer());
    }

    /**
     * Test the vertical win.
     */
    @Test
    void checkVerticalWin() {
        testModel.addDisc(COLUMN_3);
        testModel.addDisc(COLUMN_2);
        testModel.addDisc(COLUMN_3);
        testModel.addDisc(COLUMN_2);
        testModel.addDisc(COLUMN_3);
        testModel.addDisc(COLUMN_2);
        testModel.addDisc(COLUMN_3);
        assertTrue(testModel.isOver());
    }

    /**
     * Test the diagonal left win.
     */
    @Test
    void checkDiagonalLeftWin() {
        testModel.addDisc(COLUMN_2);
        testModel.addDisc(COLUMN_3);
        testModel.addDisc(COLUMN_3);
        testModel.addDisc(COLUMN_4);
        testModel.addDisc(COLUMN_4);
        testModel.addDisc(COLUMN_5);
        testModel.addDisc(COLUMN_4);
        testModel.addDisc(COLUMN_5);
        testModel.addDisc(COLUMN_5);
        testModel.addDisc(COLUMN_2);
        testModel.addDisc(COLUMN_5);
        assertTrue(testModel.isOver());
    }

    /**
     * Test the diagonal right win.
     */
    @Test
    void testDiagonalRightWin() {
        testModel.addDisc(COLUMN_5);
        testModel.addDisc(COLUMN_4);
        testModel.addDisc(COLUMN_4);
        testModel.addDisc(COLUMN_3);
        testModel.addDisc(COLUMN_3);
        testModel.addDisc(COLUMN_2);
        testModel.addDisc(COLUMN_3);
        testModel.addDisc(COLUMN_2);
        testModel.addDisc(COLUMN_2);
        testModel.addDisc(COLUMN_5);
        testModel.addDisc(COLUMN_2);
        assertTrue(testModel.isOver());
    }
}
