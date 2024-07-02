package it.unibo.mparty.view.minigames.nanogram;

/**
 * Enum representing various status messages for a Nanogram game.
 */
public enum StatusMessage {

    /**
     * Error message indicating an incorrect cell.
     */
    ERROR("Cella errata"),

    /**
     * Message indicating the player has won the game.
     */
    WIN("Hai vinto!"),

    /**
     * Message indicating the game is over.
     */
    LOSE("Fine del gioco...");

    private final String message;

    /**
     * Constructs a status message with the specified message.
     *
     * @param message the message associated with the status.
     */
    StatusMessage(final String message) {
        this.message = message;
    }

    /**
     * Returns the string representation of the status message.
     *
     * @return the string representation of the status message.
     */
    @Override
    public String toString() {
        return "*" + this.message;
    }
}
