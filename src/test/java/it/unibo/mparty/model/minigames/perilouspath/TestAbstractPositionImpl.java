package it.unibo.mparty.model.minigames.perilouspath;


import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;
import it.unibo.mparty.model.minigames.perilouspath.api.AbstractPosition;
import it.unibo.mparty.model.minigames.perilouspath.impl.BallPosition;
import it.unibo.mparty.model.minigames.perilouspath.impl.BombPosition;
import it.unibo.mparty.model.minigames.perilouspath.impl.PathPosition;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


/**
 * testing class for {@link AbstractPosition} implementations.
 */
public class TestAbstractPositionImpl {

    private final Random random = new Random();
    private static final int SIDE = 8;
    private static final int FIFTH = 5;
    private static final int SIXTH = 6;


    /**
     * test for {@link BallPosition}.
     */
    @Test
    public void testBallPosition() {

        AbstractPosition position1 = new BallPosition(random.nextInt(1, SIDE), 0, SIDE);
        assertTrue(position1.isSafe(List.of(), List.of()));
        assertEquals(0, position1.getY());
        AbstractPosition position2 = new BallPosition(random.nextInt(SIDE), SIDE - 1, SIDE);
        assertTrue(position2.isSafe(List.of(), List.of()));
        assertEquals(SIDE - 1, position2.getY());
    }

    /**
     * test class for {@link BombPosition}.
     */
    @Test
    public void testBombsPosition() {
        List<AbstractPosition> list = new LinkedList<>();

        list.add(new BombPosition(2, 2, SIDE));
        assertTrue(list.get(0).isSafe(list, List.of()));
        list.add(new BombPosition(2, 3, SIDE));
        assertTrue(list.get(1).isSafe(list, List.of()));
        list.add(new BombPosition(4, 2, SIDE));
        assertTrue(list.get(2).isSafe(list, List.of()));

    }

    /**
     * test for {@link PathPosition}.
     */
    @Test
    public void testPathPosition() {
        List<AbstractPosition> list1 = new LinkedList<>();
        List<AbstractPosition> list2 = new LinkedList<>();

        list2.add(new BallPosition(3, 0, SIDE));
        list2.add(new BallPosition(SIXTH, SIDE - 1, SIDE));

        assertFalse(new PathPosition(FIFTH, 3, SIDE).isSafe(list1, list2));
        assertTrue(new PathPosition(3, 1, SIDE).isSafe(list1, list2));
        list1.add(new PathPosition(3, 1, SIDE));
        assertFalse(new PathPosition(2, FIFTH, SIDE).isSafe(list1, list2));
        assertTrue(new PathPosition(4, 1, SIDE).isSafe(list1, list2));
    }

}