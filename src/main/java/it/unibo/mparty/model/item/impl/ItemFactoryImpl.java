package it.unibo.mparty.model.item.impl;

import it.unibo.mparty.model.item.api.Item;
import it.unibo.mparty.model.item.api.ItemFactory;

public class ItemFactoryImpl implements ItemFactory{

    @Override
    public Item createItem(ItemName name) {
        switch (name){
            case DADO_FORTUNATO:
                return new LuckyDice();
            case DADO_MALEDETTO:
                return new CursedDice();
            case DOPPIO_DADO:
                return new DoubleDice();
            case TUBO_DORATO:
                return new GoldenPipe();
            case CAMPANA_BOO:
                return new BooBell();
            default:
                throw new IllegalArgumentException("Tipo di Item non esistente");
        }

    }

}
