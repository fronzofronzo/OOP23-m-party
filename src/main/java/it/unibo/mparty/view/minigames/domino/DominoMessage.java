package it.unibo.mparty.view.minigames.domino;

public enum DominoMessage {

    MOVE_NOT_VALID("Mossa non valida. Seleziona un'altra tessera."),

    DRAW_TILE("Tessere non compattibili. Pesca una tessera."),

    WIN("%s ha vinto! Guadagnando %d monete"),

    SELECT_TILE("Seleziona una tessera per giocare.");

    private final String message;

    DominoMessage(final String message) {
        this.message = message;
    }

    public String getFormattedMessage(String winnerName, int coins) {
        return String.format(this.message, winnerName, coins);
    }

    @Override
    public String toString() {
        return "*" + this.message;
    }
}
