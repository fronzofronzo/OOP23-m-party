package it.unibo.samplejavafx.model.minigames.perilouspath;


import java.util.LinkedList;
import java.util.List;
import java.util.Random;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import it.unibo.mparty.model.minigames.perilouspath.api.AbstractPosition;
import it.unibo.mparty.model.minigames.perilouspath.impl.BallPosition;
import it.unibo.mparty.model.minigames.perilouspath.impl.BombPosition;
import it.unibo.mparty.model.minigames.perilouspath.impl.PathPosition;

public class TestAbstractPositionImpl {

    private Random random = new Random();
    private static int SIDE = 8;

    @Test
    public void TestBallPosition(){

        AbstractPosition position1 = new BallPosition(random.nextInt(SIDE), 0, SIDE);
        assertTrue(position1.isSafe(List.of(), List.of()));
        assertEquals(0, position1.getY());

        AbstractPosition position2 = new BallPosition(random.nextInt(SIDE), SIDE - 1, SIDE);
        assertTrue(position2.isSafe(List.of(), List.of()));
        assertEquals(SIDE - 1, position2.getY());
    }

    @Test
    public void TestBombsPosition(){
        List<AbstractPosition> list = new LinkedList<>();

        list.add(new BombPosition(2, 2, SIDE));
        assertTrue(list.get(0).isSafe(list,List.of()));
        list.add(new BombPosition(2, 3, SIDE));
        assertTrue(list.get(1).isSafe(list,List.of()));
        list.add(new BombPosition(3, 2, SIDE));
        assertFalse(list.get(2).isSafe(list,List.of()));

    }

    @Test
    public void TestPathPosition(){
        List<AbstractPosition> list1 = new LinkedList<>();
        List<AbstractPosition> list2 = new LinkedList<>();

        list2.add(new BallPosition(3, 0, SIDE));
        list2.add(new BallPosition(6, SIDE - 1, SIDE));

        assertFalse(new PathPosition(5, 3, SIDE).isSafe(list1, list2));
        assertTrue(new PathPosition(3,1, SIDE).isSafe(list1, list2));
        list1.add(new PathPosition(3,1, SIDE));
        assertFalse(new PathPosition(2,5, SIDE).isSafe(list1, list2));
        assertTrue(new PathPosition(4, 1, SIDE).isSafe(list1, list2));
    }
}