package it.unibo.mparty.model.item.impl;

/**
 * Enum of Item.
 */
public enum ItemName {
    /**
     * Cursed dice item.
     */
    DADO_MALEDETTO("Dado Maledetto"),
    /**
     * Lucky dice item.
     */
    DADO_FORTUNATO("Dado Fortunato"),
    /**
     * Golden Pipe item.
     */
    TUBO_DORATO("Tubo Dorato"),
    /**
     * Boo bell item.
     */
    CAMPANA_BOO("Campana Boo"),
    /**
     * Triple dice item.
     */
    TRIPLO_DADO("Triplo Dado");

    private final String name;

    ItemName(final String name) {
        this.name = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.name;
    }

}
