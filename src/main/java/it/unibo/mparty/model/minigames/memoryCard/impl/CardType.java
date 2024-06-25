package it.unibo.mparty.model.minigames.memoryCard.impl;

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
