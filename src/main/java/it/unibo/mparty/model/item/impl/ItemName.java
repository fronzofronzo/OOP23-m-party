package it.unibo.mparty.model.item.impl;

/**
 * enum of Item
 */
public enum ItemName {
    DADO_MALEDETTO("Dado Maledetto"),
    DADO_FORTUNATO("Dado Fortunato"),
    TUBO_DORATO("Tubo Dorato"),
    CAMPANA_BOO("Campana Boo"),
    DOPPIO_DADO("Doppio Dado");

    private final String name;

    private ItemName (String name) {
        this.name=name;
    }

    /**
     * Get the name of the item
     * @return the string representing the item
     */
    public String getName() {
        return this.name;
    }
}
