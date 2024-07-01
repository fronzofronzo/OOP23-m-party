package it.unibo.mparty.view.minigames.domino;

public enum DominoMessage {

    TURN("E' il turno di "),

    MOVE_NOT_VALID("Mossa non valida. Seleziona un'altra tessera."),

    DRAW_TILE("Tessere non compattibili. Pesca una tessera.");

    private final String message;

    DominoMessage(final String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "*" + this.message;
    }
}
