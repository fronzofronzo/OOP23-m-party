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
     * Message indicating the coins that the player has earned.
     */
    END("Fine del gioco. %s ha guadagnato %d monete");

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
