package it.unibo.mparty.utilities;

public enum SlotType {
    VOID(""),
    PATH("SENTIERO (nessun effetto)"),
    SINGLEPLAYER("GIOCO GIOCATORE SINGOLO"),
    MULTIPLAYER("GIOCO 1 CONTRO 1"),
    BONUS("BONUS"),
    MALUS("MALUS"),
    ACTIVE_STAR("STELLA"),
    NOT_ACTIVE_STAR("SENTIERO (nessun effetto)"),
    SHOP("NEGOZIO");

    private final String text;

    private SlotType(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return this.text;
    }
}