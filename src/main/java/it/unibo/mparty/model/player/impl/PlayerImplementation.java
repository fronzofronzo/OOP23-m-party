package it.unibo.mparty.model.player.impl;

import it.unibo.mparty.model.player.api.Dice;
import it.unibo.mparty.model.player.api.Player;
import it.unibo.mparty.model.player.api.PlayerBag;
import it.unibo.mparty.utilities.Position;

import java.util.Arrays;

/**
 * Class {@code PlayerImplementation} models the player. Each player has username,
 * character, position a player bag with all the items and a dice. Also, the player
 * has a certain amount of coins and stars.
 */
public class PlayerImplementation implements Player{

    private static final int NUM_ITEMS = 3;

    private final String username;
    private Position position = Position.getStandardPosition();
    private final Character character;
    private final PlayerBag playerBag;
    private final Dice dice;
    private int numCoins;
    private int numStars;

    /**
     * Creates a new {@code Player} with username and {@link Character} set.
     * @param username of player.
     * @param character of player.
     */
    public PlayerImplementation(final String username, final String character) {
        this.username = username;
        this.character = Arrays.stream(Character.values())
                .filter(c -> c.getName().equals(character))
                .findAny()
                .get();
        this.playerBag = new PlayerBagImplementation(NUM_ITEMS);
        this.dice = new DiceImpl();
        this.numCoins = 0;
        this.numStars = 0;
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public String getUsername() {
        return this.username;
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public Character getCharacter() {
        return this.character;
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public Position getPosition() {
        return this.position;
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public void setPosition(final Position position) {
        this.position = position;
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public void addCoins(final int num) {
        this.numCoins += num;
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public void removeCoins(final int num) {
        if (num >= this.getNumCoins()) {
            this.numCoins = 0;
        } else {
            this.numCoins -= num;
        }
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public int getNumCoins() {
        return this.numCoins;
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public void addStar() {
        this.numStars++;
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public int getNumStars() {
        return this.numStars;
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public void removeStar() {
        if (this.numStars > 0) {
            this.numStars--;
        }
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public PlayerBag getPlayerBag() {
        return this.playerBag;
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public Dice getDice() {
        return this.dice;
    }
}
