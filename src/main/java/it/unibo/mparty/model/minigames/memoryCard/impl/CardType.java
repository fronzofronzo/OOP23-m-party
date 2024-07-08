package it.unibo.mparty.model.minigames.memoryCard.impl;

/**
 * This enum contains all the types of card of the Memory card mini-game
 */
public enum CardType {
    PEAR("PERA"),
    APPLE("MELA"),
    BANANA("BANANA"),
    STRAWBERRY("FRAGOLA"),
    LEMON("LIMONE"),
    PEACH("PESCA");

    private final String name;

    private CardType(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
}
