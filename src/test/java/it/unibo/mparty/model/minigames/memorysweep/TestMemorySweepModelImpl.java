package it.unibo.mparty.model.minigames.memorysweep;


import it.unibo.mparty.model.minigames.memorysweep.api.MemorySweepModel;
import it.unibo.mparty.model.minigames.memorysweep.impl.MemorySweepModelImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * test class for Memory Sweep model.
 */
class TestMemorySweepModelImpl {

    private static final int SIZE = 8;
    private final MemorySweepModel model = new MemorySweepModelImpl(SIZE);

    /**
     * test for setting up the random list of button to be recreated.
     */
    @Test
    void testSetRandoms() {
        this.model.setRandomList();
        assertEquals(2, this.model.getRandomList().size());
        assertNotNull(this.model.getRandomList());
    }

    /**
     * test for checking if the model responses correctly to a determined click of a button.
     */
    @Test
    void testHit() {
        this.model.setRandomList();
        this.model.setUpPlayers(List.of("Dan", "Mat"));
        final var list = this.model.getRandomList();
        final var p1 = list.iterator().next();
        assertEquals(MemorySweepModel.HitType.RIGHT_CHOICE, this.model.hit(p1));

    }

}
