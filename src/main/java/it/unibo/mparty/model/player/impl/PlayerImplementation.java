package it.unibo.mparty.model.player.impl;

import it.unibo.mparty.model.player.api.Dice;
import it.unibo.mparty.model.player.api.Player;
import it.unibo.mparty.model.player.api.PlayerBag;
import it.unibo.mparty.utilities.Position;

import java.util.Arrays;

public class PlayerImplementation implements Player{

    private static final int NUM_ITEMS = 3;

    private final String username;
    private Position position = Position.getStandardPosition();
    private final Character character;
    private final PlayerBag playerBag;
    private final Dice dice;
    private int numCoins;
    private int numStars;

    public PlayerImplementation(String username, String character){
        this.username = username;
        this.character = Arrays.stream(Character.values()).filter(c -> c.getName().equals(character)).findAny().get();
        this.playerBag = new PlayerBagImplementation(NUM_ITEMS);
        this.dice = new DiceImpl();
        this.numCoins = 0;
        this.numStars = 0;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public Character getCharacter() {
        return this.character;
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public void addCoins(int num) {
        this.numCoins += num;
    }

    @Override
    public void removeCoins(int num) {
        if(num >= this.getNumCoins()){
            this.numCoins = 0;
        } else {
            this.numCoins -= num;
        }
    }

    @Override
    public int getNumCoins() {
        return this.numCoins;
    }

    @Override
    public void addStar() {
        this.numStars++;
    }

    @Override
    public int getNumStars() {
        return this.numStars;
    }

    @Override
    public void removeStar() {
        if(this.numStars > 0 ){
            this.numStars--;
        }
    }

    @Override
    public PlayerBag getPlayerBag() {
        return this.playerBag;
    }

    @Override
    public Dice getDice() {
        return this.dice;
    }
}