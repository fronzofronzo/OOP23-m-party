package it.unibo.mparty.view.minigames.domino;

/**
 * Enum representing messages that can be displayed in the domino game.
 */
public enum DominoMessage {

    /**
     * Message indicating that the move is not valid.
     */
    MOVE_NOT_VALID("Mossa non valida. Seleziona un'altra tessera."),

    /**
     * Message indicating that there are no compatible tiles and the player should draw a tile.
     */
    DRAW_TILE("Tessere non compatibili. Pesca una tessera."),

    /**
     * Message indicating the winning player and the amount of coins won.
     */
    WIN("Fine del gioco. %s ha vinto e guadagnato %d monete."),

    /**
     * Message indicating that the player should select a tile to play.
     */
    SELECT_TILE("Seleziona una tessera per giocare."),

    /**
     * Message indicating that the player should pass the turn because they have no playable tiles and the deck is empty.
     */
    PASS_TURN("Non hai tessere giocabili e il mazzo e' vuoto. Passi il turno.");

    private final String message;

    /**
     * Constructor for DominoMessage enum.
     *
     * @param message the message string associated with the enum constant.
     */
    DominoMessage(final String message) {
        this.message = message;
    }

    /**
     * Returns the formatted message for the WIN enum constant.
     *
     * @param winnerName the name of the winning player.
     * @param coins the number of coins won.
     * @return the formatted message string.
     */
    public String getFormattedMessage(final String winnerName, final int coins) {
        return String.format(this.message, winnerName, coins);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "*" + this.message;
    }
}
