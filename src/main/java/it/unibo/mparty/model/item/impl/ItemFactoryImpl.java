package it.unibo.mparty.model.item.impl;

import it.unibo.mparty.model.item.api.Item;
import it.unibo.mparty.model.item.api.ItemFactory;

/**
 * Implementation of the factory method ItemFactory.
 */
public class ItemFactoryImpl implements ItemFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    public Item createItem(final ItemName name) throws IllegalArgumentException {
        switch (name) {
            case DADO_FORTUNATO:
                return new LuckyDice();
            case DADO_MALEDETTO:
                return new CursedDice();
            case TRIPLO_DADO:
                return new TripleDice();
            case TUBO_DORATO:
                return new GoldenPipe();
            case CAMPANA_BOO:
                return new BooBell();
            default:
                throw new IllegalArgumentException("Tipo di Item non esistente");
        }

    }

}
