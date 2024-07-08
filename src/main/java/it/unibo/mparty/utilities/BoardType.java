package it.unibo.mparty.utilities;

public enum BoardType {
    EASY("Facile"), 
    MEDIUM("Medio"),
    HARD("Difficile");

    private final String text;

    private BoardType(final String text) {
        this.text = text;
    }

    @Override
    public String toString(){
        return this.text;
    }
}
