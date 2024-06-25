package it.unibo.mparty.model.player.impl;

public enum Character {
    CHAR_MARIO("Mario"),
    CHAR_LUIGI("Luigi"),
    CHAR_PEACH("Peach"),
    CHAR_WARIO("Wario"),
    CHAR_YOSHI("Yoshi"),
    CHAR_DK("Donkey-Kong");

    private final String name;

    private Character(final String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
}
