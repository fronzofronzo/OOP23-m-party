package it.unibo.mparty.model.minigames;

/**
 * This Enum represent the type of minigames.
 */
public enum MinigameType {

    /**
     * Single-player minigame.
     */
    SINGLE_PLAYER("singlePlayerMinigame"),

    /**
     * Multi-player (2) minigame.
     */
    MULTI_PLAYER("multiPlayerMinigame");

    private final String type;

    MinigameType(final String type) {
        this.type = type;
    }

    /**
     * Method to get the string describing the type.
<<<<<<< HEAD
     * 
=======
     *
>>>>>>> 92724912c635ab924d9cbe30b857f8d692b1df9f
     * @return - String containing the type.
     */
    public String getType() {
        return this.type;
    }
}
