package it.unibo.mparty.model.minigames.nanogram.util;

/**
 * Enum representing various status messages for a Nanogram game.
 */
public enum StatusMessage {

    /**
     * Error message indicating an incorrect cell.
     */
    ERROR("Cella errata");

    private String message;

    /**
     * Constructs a status message with the specified message.
     *
     * @param message the message associated with the status.
     */
    private StatusMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "*" + this.message;
    }
}
