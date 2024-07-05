package it.unibo.mparty.model.Player.impl;

import static org.junit.jupiter.api.Assertions.*;

import it.unibo.mparty.model.player.api.Dice;
import it.unibo.mparty.model.player.impl.DiceImpl;
import org.junit.jupiter.api.BeforeEach;

/**
 * Test class for a {@link DiceImpl} class
 */
class DiceImplTest {

    private Dice testDice;

    /**
     * Configuration step: this is performed before each step.
     */
    @BeforeEach
    public void init(){
        testDice = new DiceImpl();
    }

}