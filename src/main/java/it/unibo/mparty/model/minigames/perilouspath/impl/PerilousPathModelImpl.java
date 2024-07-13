package it.unibo.mparty.model.minigames.perilouspath.impl;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import it.unibo.mparty.model.minigames.MinigameType;
import it.unibo.mparty.model.minigames.perilouspath.api.AbstractPosition;
import it.unibo.mparty.model.minigames.perilouspath.api.PerilousPathModel;
import it.unibo.mparty.utilities.Pair;

/**
 * implementation of {@link PerilousPathModel} that models the actual model of the mini-game.
 */
public class PerilousPathModelImpl implements PerilousPathModel {

    private final List<AbstractPosition> bombs;
    private final List<AbstractPosition> balls;
    private final List<AbstractPosition> path;
    private final Random random;
    private final int size;
    private static final int NUM_BOMBS = 8;
    private static final int COINS = 10;
    private List<String> players = new LinkedList<>();

    /**
     * constructor of a new instance of this.
     *
     * @param size the size of the grid
     */
    public PerilousPathModelImpl(final int size) {
        this.bombs = new LinkedList<>();
        this.balls = new LinkedList<>();
        this.path = new LinkedList<>();
        this.random = new Random();
        this.size = size;
    }

    /**
     * constructor of a new instance of this.
     */
    public PerilousPathModelImpl() {
        this.bombs = new LinkedList<>();
        this.balls = new LinkedList<>();
        this.path = new LinkedList<>();
        this.random = new Random();
        this.size = NUM_BOMBS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBombs() {
        IntStream.iterate(0, i -> i + 1).limit(NUM_BOMBS).forEach(b -> this.bombs.add(getNewBombPosition()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBalls() {
        this.balls.add(this.getNewBallPosition(0));
        this.balls.add(this.getNewBallPosition(this.getSize() - 1));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<AbstractPosition> getBombs() {
        return Collections.unmodifiableList(this.bombs);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<AbstractPosition> getBalls() {
        return Collections.unmodifiableList(this.balls);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Type hit(final AbstractPosition p) {
        if (p.isSafe(this.path, this.getBalls())) {
            if (this.bombs.stream().anyMatch(b -> b.samePosition(p))) {
                return Type.BOMB;
            }
            if (this.balls.stream().anyMatch(b -> b.samePosition(p)) && !p.samePosition(this.getBalls().get(0))) {
                return Type.BALL;
            }
            this.path.add(p);
            return Type.PATH;
        }
        return Type.WRONG;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pair<String, Integer> getResult() {
        return isOver() ? new Pair<>(this.players.get(0), COINS) : new Pair<>(this.players.get(0), 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUpPlayers(final List<String> players) {
        this.players = Collections.unmodifiableList(players);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isOver() {
        if (this.path.isEmpty()) {
            return false;
        }
        final var p = this.path.get(this.path.size() - 1);
        return p.inHorizontal(getBalls().get(1)) || p.inVertical(getBalls().get(1));
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return "perilousPath";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MinigameType getType() {
        return MinigameType.SINGLE_PLAYER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSize() {
        return this.size;
    }

    /**
     * a private method for getting always a new BombPosition in a pseudo-random manner.
     *
     * @return a new BombPosition which is safe
     */
    private AbstractPosition getNewBombPosition() {
        BombPosition b;
        do {
            b = new BombPosition(random.nextInt(this.getSize() - 1), random.nextInt(this.getSize() - 1), this.getSize());
        } while (!b.isSafe(this.getBombs(), this.getBalls()));
        return b;
    }

    /**
     * a private method for getting always a new BallPosition in a pseudo-random manner.
     *
     * @param y the already set y position in a generic(x,y) position, meaning that only the x position will be random
     * @return a new BallPosition which is safe
     */
    private AbstractPosition getNewBallPosition(final int y) {
        BallPosition b;
        do {
            b = new BallPosition(this.random.nextInt(this.getSize() - 1), y, this.getSize());
        } while (!b.isSafe(List.of(), List.of()));
        return b;
    }

}
