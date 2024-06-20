package it.unibo.mparty.model.item.impl;

import it.unibo.mparty.model.player.api.Player;

/**
 * class that implements the Double Dice item
 */
public class DoubleDice extends AbstractItem{

    public DoubleDice() {
        super(ItemName.DOPPIO_DADO, 5);
    }

    @Override
    public void activate(Player player) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'activate'");
    }

    @Override
    public String getDescrition() {
        return "Si lancia 2 dadi per avanzare";
    }

}
