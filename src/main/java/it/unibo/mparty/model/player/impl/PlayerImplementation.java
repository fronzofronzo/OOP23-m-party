package it.unibo.mparty.model.player.impl;

import it.unibo.mparty.model.player.Position;
import it.unibo.mparty.model.player.api.Player;

public class PlayerImplementation implements Player{

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public Character getCharacter() {
        return null;
    }

    @Override
    public Position getPosition() {
        return null;
    }

    @Override
    public void setPosition(Position position) {

    }

    @Override
    public void addCoins(int num) {

    }

    @Override
    public void removeCoins(int num) {

    }

    @Override
    public int getNumCoins() {
        return 0;
    }

    @Override
    public void addStar() {

    }

    @Override
    public int getNumStars() {
        return 0;
    }
}