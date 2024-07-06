package it.unibo.mparty.model;

/**
 * Interface of the GameModelBuilder: it's an application of the pattern
 * Builder to build a new instance of GameModel
 */
public interface GameModelBuilder {

    /**
     * Method to add a player to the game. It is forbidden to add a player with
     * a nickname or character that is already used by someone else.
     * @param nickname of the player;
     * @param character chosen by the player;
     * @return the builder itself;
     * @throws IllegalArgumentException if there is already another player with
     * same nickname and/or username.
     */
    GameModelBuilder addPlayer(String nickname, String character) throws IllegalArgumentException;

    /**
     * Chose the difficulty of the game
     * @param difficulty of the game: it's related to the Board type
     */
    GameModelBuilder difficulty(String difficulty);

    /**
     * Get the instance of the GameModel with the selected settings
     * @return a {@link GameModel} instance
     */
    GameModel build();

    /**
     * Check if the game is initialized with enough player
     * @return true if there are enough players, false otherwise
     */
    boolean enoughPlayers();

    /**
     * Check if it can be added other players
     * @return true if the game is full ( no other players can be added),
     * false otherwise
     */
    boolean isFull();
}
