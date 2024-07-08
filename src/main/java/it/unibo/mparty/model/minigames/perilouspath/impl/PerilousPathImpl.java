package it.unibo.mparty.model.minigames.perilouspath.impl;

import java.util.*;
import java.util.stream.IntStream;

import it.unibo.mparty.model.minigames.perilouspath.api.AbstractPosition;
import it.unibo.mparty.model.minigames.perilouspath.api.PerilousPath;
import it.unibo.mparty.utilities.Pair;

public class PerilousPathImpl implements PerilousPath{

    private final List<AbstractPosition> bombs;
    private final List<AbstractPosition> balls;
    private final List<AbstractPosition> path;
    private final Random random;
    private final int size;
    private final static int NUM_BOMBS = 8;
    private final static int COINS = 10;
    private List<String> players = new LinkedList<>();


    public PerilousPathImpl(int size){
        this.bombs = new LinkedList<>();
        this.balls = new LinkedList<>();
        this.path = new LinkedList<>();
        this.random = new Random();
        this.size = size;
    }

    @Override
    public void setBombs() {
        IntStream.iterate(0, i -> i + 1).limit(NUM_BOMBS).forEach(b -> this.bombs.add(setNewBombPosition()));
    }

    @Override
    public void setBalls() {
        this.balls.add(this.setNewBallPosition(0));
        this.balls.add(this.setNewBallPosition(this.getSize() - 1));
    }

    @Override
    public List<AbstractPosition> getBombs() {
        return Collections.unmodifiableList(this.bombs);
    }

    @Override
    public List<AbstractPosition> getBalls() {
        return Collections.unmodifiableList(this.balls);
    }

    @Override
    public Type hit(AbstractPosition p) {
        if(p.isSafe(this.path,this.getBalls())){
            if(this.bombs.stream().anyMatch(b -> this.samePosition(b,p))){
                return Type.BOMB;
            }
            if(this.balls.stream().anyMatch(b -> this.samePosition(b,p)) && !p.equals(this.getBalls().get(0))){
                return Type.BALL;
            }
            this.path.add(p);
            return Type.PATH;
        }
        return Type.WRONG;
    }

    @Override
    public Pair<String, Integer> getResult() {
        return isOver() ? new Pair<>(this.players.get(0), COINS) : new Pair<>(this.players.get(0),0);
    }

    @Override
    public void setUpPlayers(List<String> players) {
        this.players = players;
    }

    @Override
    public boolean isOver() {
        var p = this.path.get(this.path.size() - 1);
        return p.inOrizzontal(getBalls().get(1)) || p.inVertical(getBalls().get(1));
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

    /**
     * a private method to know if 2 positions are in the same place
     * @param p1 the first position
     * @param p2 the second position
     * @return true if they are in the same position false otherwise
     */
    private boolean samePosition(AbstractPosition p1, AbstractPosition p2){
        return p1.getX() == p2.getX() && p1.getY() == p2.getY();
    }

}
