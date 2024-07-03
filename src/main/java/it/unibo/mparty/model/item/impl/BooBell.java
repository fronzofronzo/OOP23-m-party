package it.unibo.mparty.model.item.impl;

import it.unibo.mparty.model.player.api.Player;

/**
 * class that implements the Boobell item
 */
public class BooBell extends AbstractItem{

    public BooBell() {
        super(ItemName.CAMPANA_BOO, 10);
    }

    @Override
    public void activate(Player player) {
        player.removeCoins(player.getNumCoins()/2);
    }

    @Override
    public String getDescription() {
        return "Boo ruba delle monete ad un avversario.";
    }

}
