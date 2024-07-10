package it.unibo.mparty.model.minigames.memorySweep;


import it.unibo.mparty.model.minigames.memorysweep.api.MemorySweep;
import it.unibo.mparty.model.minigames.memorysweep.impl.MemorySweepImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * test class for Memory Sweep model.
 */
public class TestMemorySweepImpl {

    private static final int SIZE = 8;
    private final MemorySweep model = new MemorySweepImpl(SIZE);

    /**
     * test for setting up the random list of button to be recreated.
     */
    @Test
    public void testSetRandoms() {
        this.model.setRandomList();
        assertEquals(2, this.model.getRandomList().size());
        assertNotNull(this.model.getRandomList());
    }

    /**
     * test for checking if the model responses correctly to a determined click of a button.
     */
    @Test
    public void testHit() {
        this.model.setRandomList();
        this.model.setUpPlayers(List.of("Dan", "Mat"));
        var list = this.model.getRandomList();
        var p1 = list.iterator().next();
        assertEquals(MemorySweep.HitType.RIGHT_CHOICE, this.model.hit(p1));

    }

}
