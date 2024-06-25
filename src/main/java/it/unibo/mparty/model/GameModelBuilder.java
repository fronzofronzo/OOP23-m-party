package it.unibo.mparty.model;

/**
 * Interface of the GameModelBuilder: it's an application of the pattern
 * Builder to build an instance of GameModel
 */
public interface GameModelBuilder {

    /**
     * add a player to the game
     * @param nickname of the player
     * @param character chosen by the Player
     */
    void addPlayer(String nickname, String character);

    /**
     * Chose the difficulty of the game
     * @param difficulty of the game: it's related to the Board type
     */
    void difficulty(String difficulty);
}
