package it.unibo.mparty.model.minigames.memorysweep.impl;


import it.unibo.mparty.model.minigames.MinigameType;
import it.unibo.mparty.model.minigames.memorysweep.api.MemorySweep;
import it.unibo.mparty.utilities.Pair;
import it.unibo.mparty.utilities.Position;

import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.List;

/**
 * implementation of {@link MemorySweep}.
 */
public class MemorySweepImpl implements MemorySweep {

    private final Set<Position> randomList;
    private int counter;
    private Pair<String, Set<Position>> p1;
    private Pair<String, Set<Position>> p2;
    private final Random random;
    private final int side;
    private boolean turn = true;
    private String winner;
    private static final int COINS = 10;
    private static final int SIDE = 8;

    /**
     * constructor of this.
     * @param side the side of the grid
     */
    public MemorySweepImpl(final int side) {
        this.random = new Random();
        this.randomList = new HashSet<>();
        this.side = side;
        this.counter = 1;
    }

    /**
     * constructor of this without parameters.
     */
    public MemorySweepImpl() {
        this.random = new Random();
        this.randomList = new HashSet<>();
        this.side = SIDE;
        this.counter = 1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRandomList() {
        this.setCounter();
        this.randomList.clear();
        for (var i = 0; i < this.getCounter(); i++) {
            this.randomList.add(getNewPosition());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Position> getRandomList() {
        return Collections.unmodifiableSet(this.randomList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HitType hit(final Position p) {
        return this.turn() ? this.playerTurn(this.p1, p) : this.playerTurn(this.p2, p);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean turn() {
        return this.turn;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getWinner() {
        return this.winner;
    }

    /**
     * method for getting a new position which is not already included in the randomList variable.
     *
     * @return the new position
     */
    private Position getNewPosition() {
        Position p;
        do {
            p = new Position(random.nextInt(this.side), random.nextInt(this.side));
        } while (this.getRandomList().contains(p));
        return p;
    }

    /**
     * method for increasing the list of buttons that will be recreated.
     */
    private void setCounter() {
        this.counter++;
    }

    /**
     * the actual method that manages the players turns.
     *
     * @param player the set of the player
     * @param p      the position clicked by that player
     * @return whether his guess was right(in this case returns
     * whether the guess is over or not) or wrong
     */
    private HitType playerTurn(final Pair<String, Set<Position>> player, final Position p) {
        if (this.randomList.contains(p)) {
            player.getSecond().add(p);
            if (player.getSecond().size() == this.randomList.size()) {
                this.changeTurn();
                player.getSecond().clear();
                return HitType.TURN_END;
            }
            return HitType.RIGHT_CHOICE;
        }
        this.winner = player.equals(notNull(this.p1)) ? notNull(this.p2).getFirst() : notNull(this.p1).getFirst();
        return HitType.LOSS;
    }

    /**
     * changes the turn.
     */
    private void changeTurn() {
        this.turn = !this.turn;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCounter() {
        return this.counter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pair<String, Integer> getResult() {
        return new Pair<>(this.winner, COINS);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUpPlayers(final List<String> players) {
        this.p1 = new Pair<>(players.get(0), new HashSet<>());
        this.p2 = new Pair<>(players.get(1), new HashSet<>());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isOver() {
        return this.winner.equals(notNull(this.p1).getFirst()) || this.winner.equals(notNull(this.p2).getFirst());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return "memorySweep";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MinigameType getType() {
        return MinigameType.MULTI_PLAYER;
    }

    private Pair<String, Set<Position>> notNull(final Pair<String, Set<Position>> p) {
        return p == null ? new Pair<>("", new HashSet<>()) : p;
    }

}
