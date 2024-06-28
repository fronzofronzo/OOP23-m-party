package it.unibo.mparty.view.minigames.nanogram.util;

/**
 * Enum representing various status messages for a Nanogram game.
 */
public enum StatusMessage {

    /**
     * Error message indicating an incorrect cell.
     */
    ERROR("Cella errata"),

    WIN("You won!"),

    LOSE("Game Over...");

    private final String message;

    /**
     * Constructs a status message with the specified message.
     *
     * @param message the message associated with the status.
     */
    StatusMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "*" + this.message;
    }
}
