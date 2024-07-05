package it.unibo.mparty.utilities;

public enum BoardType {
    EASY("Easy"), 
    MEDIUM("Medium"),
    HARD("Hard");

    private final String text;

    private BoardType(final String text) {
        this.text = text;
    }

    @Override
    public String toString(){
        return this.text;
    }
}
