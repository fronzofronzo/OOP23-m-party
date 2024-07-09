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

public class TestSecretCode {

    private static SecretCodeModel model;

    @BeforeEach
    public void initialise() {
        List<String> players = List.of("P1","P2");
        model = new SecretCodeModelImpl(); 
        model.setUpPlayers(players);
    }

    @Test
    public void TestAddColor() {
        assertTrue(model.getGame().addColor(SecretCodeColors.ARANCIONE));
        assertTrue(model.getGame().addColor(SecretCodeColors.ARANCIONE));
        assertTrue(model.getGame().addColor(SecretCodeColors.ARANCIONE));
        assertTrue(model.getGame().addColor(SecretCodeColors.ARANCIONE));
        assertFalse(model.getGame().addColor(SecretCodeColors.ARANCIONE));
    }

    @Test
    public void TestChangeTurn() {
        assertTrue(model.getGame().addColor(SecretCodeColors.ARANCIONE));
        assertTrue(model.getGame().addColor(SecretCodeColors.ARANCIONE));
        assertTrue(model.getGame().addColor(SecretCodeColors.ARANCIONE));
        assertTrue(model.getGame().addColor(SecretCodeColors.ARANCIONE));
        String p1 = model.getGame().getCurrentPlayer();
        List<SecretCodeResults> res = model.getGame().getGuessResult();
        assertFalse(res.isEmpty());
        String p2 = model.getGame().getCurrentPlayer();
        assertNotEquals(p1, p2);
    }

    @Test 
    public void TestEndGame() {
        assertFalse(model.getGame().isOver());
        String p1 = model.getGame().getCurrentPlayer();
        List<SecretCodeColors> sol = model.getGame().getSoluction();
        assertFalse(sol.isEmpty());
        for (SecretCodeColors c : sol) {
            assertTrue(model.getGame().addColor(c));
        }
        List<SecretCodeResults> res = model.getGame().getGuessResult();
        assertTrue(model.getGame().isOver());
        assertEquals(p1, model.getGame().getWinner());
    }   
}