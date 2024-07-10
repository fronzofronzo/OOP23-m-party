package it.unibo.mparty.utilities;

/**
 * This enum identifies the board
 */
public enum BoardType {
    /**
     * 
     */
    EASY("Facile"),
    /**
     * 
     */
    MEDIUM("Medio"),
    /**
     * 
     */
    HARD("Difficile");

    private final String text;

    private BoardType(final String text) {
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
