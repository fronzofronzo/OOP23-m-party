package it.unibo.mparty.model.minigames.perilouspath;

import static org.junit.jupiter.api.Assertions.*;

import it.unibo.mparty.model.minigames.perilouspath.impl.BallPosition;
import org.junit.jupiter.api.Test;

import it.unibo.mparty.model.minigames.perilouspath.api.AbstractPosition;
import it.unibo.mparty.model.minigames.perilouspath.api.PerilousPath;
import it.unibo.mparty.model.minigames.perilouspath.impl.PathPosition;
import it.unibo.mparty.model.minigames.perilouspath.impl.PerilousPathImpl;


public class TestPerilousPath{

    private static final int SIDE = 8;
    private static final int NUM_BALLS = 2;
    private static final int NUM_BOMBS = 8;
    private static final int FIRST_COLUMN = 0;
    private static final int LAST_COLUMN = 7;

    private final PerilousPath model = new PerilousPathImpl(SIDE);

    @Test
    public void TestPerilousPathConstructor(){
        assertEquals(FIRST_COLUMN, this.model.getBalls().size());
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
        this.model.setBalls();
        this.model.setBombs();
    }

    
}