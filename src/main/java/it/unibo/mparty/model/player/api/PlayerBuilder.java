package it.unibo.mparty.model.player.api;

public interface PlayerBuilder {

    /**
     * Sets the username of the player
     * @param username of the player
     * @return current builder
     */
    PlayerBuilder username(String username);
}
