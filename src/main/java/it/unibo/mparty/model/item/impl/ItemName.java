package it.unibo.mparty.model.item.impl;

/**
 * enum of Item.
 */
public enum ItemName {
    DADO_MALEDETTO("Dado Maledetto"),
    DADO_FORTUNATO("Dado Fortunato"),
    TUBO_DORATO("Tubo Dorato"),
    CAMPANA_BOO("Campana Boo"),
    DOPPIO_DADO("Doppio Dado");

    private final String name;

    ItemName(String name) {
        this.name=name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.name;
    }

}
