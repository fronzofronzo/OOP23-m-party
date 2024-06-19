package it.unibo.mparty.model.minigames.nanogram.impl;

public enum StatusMessage {
    ERROR("Cella errata");

    private String message;

    private StatusMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "*" + this.message;
    }
}
