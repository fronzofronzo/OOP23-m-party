package it.unibo.mparty.view.minigames.domino;

public enum DominoMessage {

    TURN("E' il turno di ");

    private final String message;

    DominoMessage(final String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "*" + this.message;
    }
}
