package it.unibo.mparty.model.item.impl;

import it.unibo.mparty.model.player.api.Player;

/**
 * class that implements the Double Dice item
 */
public class DoubleDice extends AbstractItem{

    /**
     * Construct a new istance of DoubleDice item
     */
    public DoubleDice() {
        super(ItemName.DOPPIO_DADO, 7);
    }

    @Override
    public void activate(Player player) {
        player.getDice().setNumberOfAttempts(2);
    }

    @Override
    public String getDescription() {
        return "Si lancia 2 dadi per avanzare";
    }

}
