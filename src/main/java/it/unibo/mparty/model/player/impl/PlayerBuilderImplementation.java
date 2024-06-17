package it.unibo.mparty.model.player.impl;

import it.unibo.mparty.model.player.Position;
import it.unibo.mparty.model.player.api.Player;
import it.unibo.mparty.model.player.api.PlayerBuilder;

public class PlayerBuilderImplementation implements PlayerBuilder {

    private String username;
    private Character character;
    private Position position;

    @Override
    public PlayerBuilder username(String username) {
        return null;
    }

    @Override
    public PlayerBuilder character(Character character) {
        return null;
    }

    @Override
    public PlayerBuilder position(Position position) {
        return null;
    }

    @Override
    public Player buildPlayer() {
        return null;
    }
}
