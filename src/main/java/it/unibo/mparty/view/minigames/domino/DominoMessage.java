package it.unibo.mparty.view.minigames.domino;

public enum DominoMessage {

    MOVE_NOT_VALID("Mossa non valida. Seleziona un'altra tessera."),

    DRAW_TILE("Tessere non compattibili. Pesca una tessera."),

    WIN(" ha vinto!"),

    SELECT_TILE("Seleziona una tessera per giocare.");

    private final String message;

    DominoMessage(final String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "*" + this.message;
    }
}
