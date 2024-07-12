package it.unibo.mparty.model.minigames.secretcode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.mparty.model.minigames.secretcode.api.SecretCodeModel;
import it.unibo.mparty.model.minigames.secretcode.impl.SecretCodeModelImpl;
import it.unibo.mparty.model.minigames.secretcode.util.SecretCodeColors;
import it.unibo.mparty.model.minigames.secretcode.util.SecretCodeResults;

/**
 * This class test {@link SecretCodeModel}.
 */
class TestSecretCode {

    private SecretCodeModel model;

    /**
     * This class initialises the model befor each test method.
     */
    @BeforeEach
    public void initialise() {
        final List<String> players = List.of("P1", "P2");
        model = new SecretCodeModelImpl();
        model.setUpPlayers(players);
    }

    /**
     * This class test the method addColor().
     */
    @Test
    @SuppressFBWarnings(value = "UWF_FIELD_NOT_INITIALIZED_IN_CONSTRUCTOR",
            justification = "The field is initialized in the setUp method, which is run before each test.")
    void testAddColor() {
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
    @SuppressFBWarnings(value = "UWF_FIELD_NOT_INITIALIZED_IN_CONSTRUCTOR",
            justification = "The field is initialized in the setUp method, which is run before each test.")
    void testChangeTurn() {
        assertTrue(model.addColor(SecretCodeColors.ARANCIONE));
        assertTrue(model.addColor(SecretCodeColors.ARANCIONE));
        assertTrue(model.addColor(SecretCodeColors.ARANCIONE));
        assertTrue(model.addColor(SecretCodeColors.ARANCIONE));
        final String p1 = model.getCurrentPlayer();
        final List<SecretCodeResults> res = model.getGuessResult();
        assertFalse(res.isEmpty());
        final String p2 = model.getCurrentPlayer();
        assertNotEquals(p1, p2);
    }

    /**
     * This class test that the game actually ends when a player guesses the
     * soluction and that the winner is the player that actually guessed it.
     */
    @Test
    @SuppressFBWarnings(value = "UWF_FIELD_NOT_INITIALIZED_IN_CONSTRUCTOR",
            justification = "The field is initialized in the setUp method, which is run before each test.")
    void testEndGame() {
        assertFalse(model.isOver());
        final String p1 = model.getCurrentPlayer();
        final List<SecretCodeColors> sol = model.getSoluction();
        assertFalse(sol.isEmpty());
        for (final SecretCodeColors c : sol) {
            assertTrue(model.addColor(c));
        }
        final List<SecretCodeResults> res = model.getGuessResult();
        assertFalse(res.isEmpty());
        assertTrue(model.isOver());
        assertEquals(p1, model.getWinner());
    }
}
