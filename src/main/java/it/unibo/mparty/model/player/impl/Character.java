package it.unibo.mparty.model.player.impl;

/**
 * This enum contains all the character playable of the game.
 */
public enum Character {
    /**
     * "Mario" character of the game.
     */
    CHAR_MARIO("Mario"),
    /**
     * "Luigi" character of the game.
     */
    CHAR_LUIGI("Luigi"),
    /**
     * "Peach" character of the game.
     */
    CHAR_PEACH("Peach"),
    /**
     * "Wario" character of the game.
     */
    CHAR_WARIO("Wario"),
    /**
     * "Yoshi" character of the game.
     */
    CHAR_YOSHI("Yoshi"),
    /**
     * "Donkey-Kong" character of the game.
     */
    CHAR_DK("Donkey-Kong");

    private final String name;

    Character(final String name) {
        this.name = name;
    }

    /**
     * Method to get the name of a character.
     * @return - {@link String} containing the name of character.
     */
    public String getName() {
        return this.name;
    }
}
