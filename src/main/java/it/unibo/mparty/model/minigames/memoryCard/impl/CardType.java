package it.unibo.mparty.model.minigames.memoryCard.impl;

/**
 * This enum contains all the types of card of the Memory card mini-game
 */
public enum CardType {
    PEAR("PEAR"),
    APPLE("APPLE"),
    BANANA("BANANA"),
    STRAWBERRY("STRAWBERRY"),
    LEMON("LEMON");

    private final String name;

    private CardType(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
}
