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

    private MinigameType(final String type){
        this.type = type;
    }

    /**
     * Method to get the string describing the type.
     * @return - String containing the type.
     */
    public String getType(){
        return this.type;
    }
}
