package it.unibo.mparty.model.player.impl;

import it.unibo.mparty.model.player.Position;
import it.unibo.mparty.model.player.api.Player;

public class PlayerImplementation implements Player{

    private final String username;
    private Position position;
    private final Character character;
    private int numCoins;
    private int numStars;

    public PlayerImplementation(String username, Position position, Character character){
        this.username = username;
        this.position = position;
        this.character = character;
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
}