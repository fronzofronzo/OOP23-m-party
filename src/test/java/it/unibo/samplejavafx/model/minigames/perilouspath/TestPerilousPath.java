package it.unibo.samplejavafx.model.minigames.perilouspath;




import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import it.unibo.mparty.model.minigames.perilouspath.api.AbstractPosition;
import it.unibo.mparty.model.minigames.perilouspath.api.PerilousPath;
import it.unibo.mparty.model.minigames.perilouspath.impl.PathPosition;
import it.unibo.mparty.model.minigames.perilouspath.impl.PerilousPathImpl;


public class TestPerilousPath{

    private static int SIDE = 8;
    private static int NUM_BALLS = 2;
    private static int NUM_BOMBS = 8;
    private static int FIRST_COLUMN = 0;
    private static int LAST_COLUMN = 7;

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
        this.model.setBalls();
        this.model.setBombs();
        var p = getSafePosition();
        assertEquals(PerilousPath.Type.PATH, this.model.hit(p));
    }

    @Test
    public void TestIsOver(){

        
    }

    private AbstractPosition getSafePosition(){
        var p = new PathPosition(this.model.getBalls().get(0).getX() + 1,this.model.getBalls().get(0).getY(), SIDE);
        if(!this.model.getBombs().contains(p)){
            return p;
        }
        var p1 = new PathPosition(this.model.getBalls().get(0).getX(),this.model.getBalls().get(0).getY() + 1, SIDE);
        if(!this.model.getBombs().contains(p1)){
            return p1;
        }
        var p2 = new PathPosition(this.model.getBalls().get(0).getX() - 1,this.model.getBalls().get(0).getY(), SIDE);
        return p2;
        
    }

    
}