package it.unibo.samplejavafx.model.minigames.perilouspath;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import it.unibo.mparty.model.minigames.perilouspath.api.AbstractPosition;
import it.unibo.mparty.model.minigames.perilouspath.api.PerilousPath;
import it.unibo.mparty.model.minigames.perilouspath.api.PerilousPath.Type;
import it.unibo.mparty.model.minigames.perilouspath.impl.BombPosition;
import it.unibo.mparty.model.minigames.perilouspath.impl.PathPosition;
import it.unibo.mparty.model.minigames.perilouspath.impl.Pair;
import it.unibo.mparty.model.minigames.perilouspath.impl.BallPosition;
import it.unibo.mparty.model.minigames.perilouspath.impl.PerilousPathImpl;


public class TestPerilousPath{

    private static int SIDE = 8;
    private static int NUM_BALLS = 2;
    private static int NUM_BOMBS = 8;
    private static int FIRST_COLUMN = 0;
    private static int LAST_COLUMN = 8;

    private PerilousPath model = new PerilousPathImpl(SIDE);

    @Test
    public void TestPerilousPathConstructor(){
        assertEquals(FIRST_COLUMN, this.model.getBalls().size());
        assertEquals(FIRST_COLUMN, this.model.getPath().size());
        assertEquals(FIRST_COLUMN, this.model.getBombs().size());

    }

    @Test
    public void TestSetBombs(){
        this.model.setBombs();
        var bombs = this.model.getBombs();
        assertEquals(NUM_BOMBS, bombs.size());
    }

    @Test
    public void TestSetBalls(){
        this.model.setBalls();
        assertEquals(NUM_BALLS,this.model.getBalls().size());
        assertEquals(FIRST_COLUMN, this.model.getBalls().get(0).getY());
        assertEquals(LAST_COLUMN, this.model.getBalls().get(1).getY());
    }

    @Test
    public void TestHit(){
        var bomb = this.model.getBombs().get(FIRST_COLUMN);
        assertEquals(Type.WRONG, this.model.hit(bomb));
    }

}