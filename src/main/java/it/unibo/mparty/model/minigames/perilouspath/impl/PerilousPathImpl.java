package it.unibo.mparty.model.minigames.perilouspath.impl;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import it.unibo.mparty.model.minigames.perilouspath.api.AbstractPosition;
import it.unibo.mparty.model.minigames.perilouspath.api.PerilousPath;

public class PerilousPathImpl implements PerilousPath{

    private Optional<List<BombPosition>> bombs = Optional.empty();
    private Optional<List<BallPosition>> balls = Optional.empty();
    private Optional<List<PathPosition>> path = Optional.empty();
    private Random random;
    private int size;
    private static final int NUM_BOMBS = 7;
    private static final int NUM_BALLS = 2;


    public PerilousPathImpl(int size){
        this.bombs = Optional.of(new LinkedList<>());
        this.balls = Optional.of(new LinkedList<>());
        this.path = Optional.of(new LinkedList<>());
        this.random = new Random();
        this.size = size;
    }

    @Override
    public void setBombs() {
        for(var i = 0; i < NUM_BOMBS; i++){
            BombPosition b;
            do {
                b = new BombPosition(random.nextInt(this.size - 1), random.nextInt(this.size - 1), this.size);
            } while (!b.isSafe(this.getBombs()));
            this.bombs.get().add(b);
        }
    }

    @Override
    public void setBalls() {
        this.balls.get().add(new BallPosition(this.random.nextInt(this.size - 1),0,this.size));
        this.balls.get().add(new BallPosition(this.random.nextInt(this.size - 1),this.size - 1,this.size));

    }

    @Override
    public List<AbstractPosition> getBombs() {
        return Collections.unmodifiableList(this.bombs.get());
    }

    @Override
    public List<AbstractPosition> getBalls() {
        return Collections.unmodifiableList(this.balls.get());
    }

    
}
