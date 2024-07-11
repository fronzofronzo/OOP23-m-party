package it.unibo.mparty.model.minigames.secretCode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.mparty.model.minigames.secretCode.api.SecretCodeModel;
import it.unibo.mparty.model.minigames.secretCode.impl.SecretCodeModelImpl;
import it.unibo.mparty.model.minigames.secretCode.util.SecretCodeColors;
import it.unibo.mparty.model.minigames.secretCode.util.SecretCodeResults;

/**
 * This class test {@link SecretCodeModel}.
 */
public class TestSecretCode {

    private static SecretCodeModel model;

    /**
     * This class initialises the model befor each test method.
     */
    @BeforeEach
    public void initialise() {
        List<String> players = List.of("P1", "P2");
        model = new SecretCodeModelImpl();
        model.setUpPlayers(players);
    }

    /**
     * This class test the method addColor().
     */
    @Test
    public void testAddColor() {
        assertTrue(model.addColor(SecretCodeColors.ARANCIONE));
        assertTrue(model.addColor(SecretCodeColors.ARANCIONE));
        assertTrue(model.addColor(SecretCodeColors.ARANCIONE));
        assertTrue(model.addColor(SecretCodeColors.ARANCIONE));
        assertFalse(model.addColor(SecretCodeColors.ARANCIONE));
    }

    /**
     * This class test that the model handles correctly the turns.
     */
    @Test
    public void testChangeTurn() {
        assertTrue(model.addColor(SecretCodeColors.ARANCIONE));
        assertTrue(model.addColor(SecretCodeColors.ARANCIONE));
        assertTrue(model.addColor(SecretCodeColors.ARANCIONE));
        assertTrue(model.addColor(SecretCodeColors.ARANCIONE));
        String p1 = model.getCurrentPlayer();
        List<SecretCodeResults> res = model.getGuessResult();
        assertFalse(res.isEmpty());
        String p2 = model.getCurrentPlayer();
        assertNotEquals(p1, p2);
    }

    /**
     * This class test that the game actually ends when a player guesses the
     * soluction and that the winner is the player that actually guessed it.
     */
    @Test
    public void testEndGame() {
        assertFalse(model.isOver());
        String p1 = model.getCurrentPlayer();
        List<SecretCodeColors> sol = model.getSoluction();
        assertFalse(sol.isEmpty());
        for (SecretCodeColors c : sol) {
            assertTrue(model.addColor(c));
        }
        List<SecretCodeResults> res = model.getGuessResult();
        assertFalse(res.isEmpty());
        assertTrue(model.isOver());
        assertEquals(p1, model.getWinner());
    }
}
