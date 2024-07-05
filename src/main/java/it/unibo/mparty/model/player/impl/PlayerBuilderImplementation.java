package it.unibo.mparty.model.player.impl;

import it.unibo.mparty.model.player.api.Player;
import it.unibo.mparty.model.player.api.PlayerBuilder;

public class PlayerBuilderImplementation implements PlayerBuilder {

    private String username;
    private String character;

    @Override
    public PlayerBuilder username(String username) {
        this.username = username;
        return this;
    }

    @Override
    public PlayerBuilder character(String character) {
        this.character = character;
        return this;
    }


    @Override
    public Player buildPlayer() {
        if (this.username == null  || this.character == null){
            throw new IllegalStateException("Missing fields: cannot create a new Player");
        }
        return new PlayerImplementation(this.username, this.character);
    }
}
