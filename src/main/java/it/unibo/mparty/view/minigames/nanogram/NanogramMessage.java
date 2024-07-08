package it.unibo.mparty.view.minigames.nanogram;

/**
 * Enum representing various status messages for a Nanogram game.
 */
public enum NanogramMessage {

    /**
     * Error message indicating an incorrect cell.
     */
    ERROR("Cella errata"),

    /**
     * Message indicating the player has won the game, and coins that earned.
     */
    WIN("%s ha vinto! Guadagnando %d monete."),

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
    NanogramMessage(final String message) {
        this.message = message;
    }

    public String getFormattedMessage(String winnerName, int coins) {
        return String.format(this.message, winnerName, coins);
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
