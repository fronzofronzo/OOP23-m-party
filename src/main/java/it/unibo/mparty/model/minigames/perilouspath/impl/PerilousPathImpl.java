package it.unibo.mparty.model.minigames.perilouspath.impl;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.IntStream;

import it.unibo.mparty.model.minigames.perilouspath.api.AbstractPosition;
import it.unibo.mparty.model.minigames.perilouspath.api.PerilousPath;

public class PerilousPathImpl implements PerilousPath{

    private Optional<List<AbstractPosition>> bombs = Optional.empty();
    private Optional<List<AbstractPosition>> balls = Optional.empty();
    private Optional<List<AbstractPosition>> path = Optional.empty();
    private Random random;
    private int size;
    private static final int NUM_BOMBS = 7;


    public PerilousPathImpl(int size){
        this.bombs = Optional.of(new LinkedList<>());
        this.balls = Optional.of(new LinkedList<>());
        this.path = Optional.of(new LinkedList<>());
        this.random = new Random();
        this.size = size;
    }

    @Override
    public void setBombs() {
        IntStream.iterate(0, i -> i + 1).limit(NUM_BOMBS).forEach(b -> this.bombs.get().add(setNewBombPosition()));
    }

    @Override
    public void setBalls() {
        this.balls.get().add(this.setNewBallPosition(0));
        this.balls.get().add(this.setNewBallPosition(this.getSize() - 1));
    }

    @Override
    public List<AbstractPosition> getBombs() {
        return Collections.unmodifiableList(this.bombs.get());
    }

    @Override
    public List<AbstractPosition> getBalls() {
        return Collections.unmodifiableList(this.balls.get());
    }

    @Override
    public Type hit(AbstractPosition p) {
        if(this.bombs.get().contains(p)){
            return Type.BOMB;
        }
        if(this.balls.get().contains(p)){
            return Type.BALL;
        }
        if(p.isSafe(this.path.get(),this.getBalls())){
            this.path.get().add(p);
            return Type.PATH;
        }
        return Type.WRONG;
    }

    @Override
    public boolean isOver() {
        var p1 = this.path.get().get(0);
        var p2 = this.path.get().get(this.getSize() - 1);
        if(p1.adjacent(this.balls.get().get(0)) && p2.adjacent(this.balls.get().get(1))){
            return true;
        }
        return false;
    }

    @Override
    public List<AbstractPosition> getPath() {
        return Collections.unmodifiableList(this.path.get());
    }

    @Override
    public int getSize() {
        return this.size;
    }

    /**
     * a private method for getting always a new BombPosition in a pseudo-random manner
     * @return a new BombPosition which is safe
     */
    private AbstractPosition setNewBombPosition(){
        BombPosition b;
        do{
            b = new BombPosition(random.nextInt(this.getSize() - 1), random.nextInt(this.getSize()- 1), this.getSize());
        }while (!b.isSafe(this.getBombs(),List.of()));
        return b;
    }

    /**
     * a private method for getting always a new BallPosition in a pseudo-random manner
     * @param y the already setted y position in a generic(x,y) position, meaning that onlye the x position will be random
     * @return a new BallPosition which is safe
     */
    private AbstractPosition setNewBallPosition(int y){
        BallPosition b;
        do{
            b = new BallPosition(this.random.nextInt(this.getSize() - 1), y, this.getSize());
        }while(!b.isSafe(List.of(), List.of()));
        return b;
    }

}
