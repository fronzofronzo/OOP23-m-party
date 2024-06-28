package it.unibo.samplejavafx.model.minigames.memorysweep;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import it.unibo.mparty.model.minigames.memorysweep.api.MemorySweep;
import it.unibo.mparty.model.minigames.memorysweep.api.MemorySweep.HitType;
import it.unibo.mparty.model.minigames.memorysweep.impl.MemorySweepImpl;
import it.unibo.mparty.utilities.*;


public class TestMemorySweep {

    private static MemorySweep model;

    @BeforeAll
    public void initial(){
        model = new MemorySweepImpl(4);
        assertTrue(model.getTurn());
        assertEquals(3, model.getCounter());
        assertNotNull(model.getRandomList());
    }

    @Test
    public void testGetRandomList(){

        model.setRandomList();
        assertEquals(4, model.getCounter());
        var set = model.getRandomList();
        assertEquals(set, model.getRandomList());

    }

    @Test
    public void testHit(){
        model.setRandomList();
        var p1 = model.getRandomList().iterator().next();
        assertEquals(HitType.RIGHT_CHOICE, model.hit(p1));
        var p2 = new Position(1, 1);
        assertEquals(HitType.LOSS, model.hit(p2));
    }
}