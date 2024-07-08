package it.unibo.mparty.model.minigames.memoryCard.impl;

/**
 * This enum contains all the types of card of the Memory card mini-game.
 */
public enum CardType {

    /**
     * "Pear" card type
     */
    PEAR("PERA"),
    /**
     * "Apple" card type
     */
    APPLE("MELA"),
    /**
     * "Banana" card type
     */
    BANANA("BANANA"),
    /**
     * "Strawberry" card type
     */
    STRAWBERRY("FRAGOLA"),
    /**
     * "Lemon" card type
     */
    LEMON("LIMONE"),
    /**
     * "Peach" card type
     */
    PEACH("PESCA");

    private final String name;

    private CardType(final String name) {
        this.name = name;
    }

    /**
     * Method to get the name of each type.
     * @return - {@link String} containing name of type.
     */
    public String getName() {
        return this.name;
    }
}
