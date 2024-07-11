package it.unibo.mparty.utilities;

import it.unibo.mparty.model.gameBoard.api.GameBoard;

/**
 * The enum SlotType identifies and defines a slot in a {@link GameBoard}.
 */
public enum SlotType {
    /**
     * void, do not belong to the actual path of the board.
     */
    VOID(""),
    /**
     * path.
     */
    PATH("SENTIERO (nessun effetto)"),
    /**
     * singleplayer.
     */
    SINGLEPLAYER("GIOCO GIOCATORE SINGOLO"),
    /**
     * multiplayer.
     */
    MULTIPLAYER("GIOCO 1 CONTRO 1"),
    /**
     * bonus.
     */
    BONUS("BONUS"),
    /**
     * malus.
     */
    MALUS("MALUS"),
    /**
     * active star.
     */
    ACTIVE_STAR("STELLA"),
    /**
     * not active star.
     */
    NOT_ACTIVE_STAR("SENTIERO (nessun effetto)"),
    /**
     * shop.
     */
    SHOP("NEGOZIO");

    private final String text;

    SlotType(final String text) {
        this.text = text;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.text;
    }
}
