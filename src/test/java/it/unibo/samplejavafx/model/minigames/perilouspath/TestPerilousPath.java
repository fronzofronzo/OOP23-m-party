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

    private PerilousPath model;

    @BeforeAll
    public void TestPerilousPathConstructor(){
        this.model = new PerilousPathImpl(8);
        assertEquals(0, this.model.getBalls().size());
        assertEquals(0, this.model.getBombs().size());
        assertEquals(0, this.model.getPath().size());
        assertEquals(8, this.model.getSize());
    }

    @Test
    public void TestSetBombs(){
        this.model.setBombs();
        assertEquals(8, this.model.getBombs().size());
    }

    @Test
    public void TestSetBalls(){
        this.model.setBalls();
        assertEquals(2,this.model.getBalls().size());
        assertEquals(0, this.model.getBalls().get(0).getY());
        assertEquals(7, this.model.getBalls().get(1).getY());
    }

    @Test
    public void TestHit(){
        var bomb = this.model.getBombs().get(0);
        assertEquals(Type.BOMB, this.model.hit(bomb));
    }

}