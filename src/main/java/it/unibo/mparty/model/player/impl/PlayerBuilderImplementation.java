package it.unibo.mparty.model.player.impl;

import it.unibo.mparty.model.player.api.Player;
import it.unibo.mparty.model.player.api.PlayerBuilder;
import it.unibo.mparty.utilities.Position;

public class PlayerBuilderImplementation implements PlayerBuilder {

    private String username;
    private Character character;
    private Position position;

    @Override
    public PlayerBuilder username(String username) {
        this.username = username;
        return this;
    }

    @Override
    public PlayerBuilder character(Character character) {
        this.character = character;
        return this;
    }

    @Override
    public PlayerBuilder position(Position position) {
        this.position = position;
        return this;
    }

    @Override
    public Player buildPlayer() {
        if (this.username == null || this.position == null || this.character == null){
            throw new IllegalStateException("Missing fields: cannot create a new Player");
        }
        return new PlayerImplementation(this.username, this.position, this.character);
    }
}
