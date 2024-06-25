package it.unibo.mparty.model.item.impl;

import it.unibo.mparty.model.player.api.Player;

/**
 * class that implements the Lucky Dice item
 */
public class LuckyDice extends AbstractItem{

    public LuckyDice() {
        super(ItemName.DADO_FORTUNATO, 7);
    }

    @Override
    public void activate(Player player) {
        player.getDice().setMinNumber(4);
    }

    @Override
    public String getDescrition() {
        return "Il dado ha solo numeri da 4 a 6";
    }

}
