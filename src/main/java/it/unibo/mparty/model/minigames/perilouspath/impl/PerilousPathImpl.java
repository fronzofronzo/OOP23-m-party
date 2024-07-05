package it.unibo.mparty.model.minigames.perilouspath.impl;

import java.util.*;
import java.util.stream.IntStream;

import it.unibo.mparty.model.minigames.perilouspath.api.AbstractPosition;
import it.unibo.mparty.model.minigames.perilouspath.api.PerilousPath;
import it.unibo.mparty.utilities.Pair;

public class PerilousPathImpl implements PerilousPath{

    private Optional<List<AbstractPosition>> bombs = Optional.empty();
    private Optional<List<AbstractPosition>> balls = Optional.empty();
    private Optional<List<AbstractPosition>> path = Optional.empty();
    private final Random random;
    private final int size;
    private static final int NUM_BOMBS = 8;
    private List<String> players = new LinkedList<>();
    private int coins = 20;


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
        if(p.isSafe(this.path.get(),this.getBalls())){
            if(this.bombs.get().stream().anyMatch(b -> b.getX() == p.getX() && b.getY() == p.getY())){
                return Type.BOMB;
            }
            if(this.balls.get().stream().anyMatch(b -> b.getX() == p.getX() && b.getY() == p.getY()) && !p.equals(this.getBalls().get(0))){
                return Type.BALL;
            }
            this.path.get().add(p);
            return Type.PATH;
        }
        return Type.WRONG;
    }

    @Override
    public Pair<String, Integer> getResult() {
        return isOver() ? new Pair<>(this.players.get(0),this.coins) : new Pair<>(this.players.get(0),0);
    }

    @Override
    public void setUpPlayers(List<String> players) {
        this.players = players;
    }

    @Override
    public boolean isOver() {
        var p = this.path.get().get(this.path.get().size() - 1);
        return p.inOrizzontal(getBalls().get(1)) || p.inVertical(getBalls().get(1));
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
        }while (!b.isSafe(this.getBombs(),this.getBalls()));
        return b;
    }

    /**
     * a private method for getting always a new BallPosition in a pseudo-random manner
     * @param y the already set y position in a generic(x,y) position, meaning that only the x position will be random
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
