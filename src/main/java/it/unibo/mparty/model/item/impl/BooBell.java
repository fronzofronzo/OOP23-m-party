package it.unibo.mparty.model.item.impl;

import it.unibo.mparty.model.player.api.Player;

/**
 * class that implements the Boobell item
 */
public class BooBell extends AbstractItem{

    /**
     * Construct a new istance of BooBell item
     */
    public BooBell() {
        super(ItemName.CAMPANA_BOO, 7);
    }

    @Override
    public void activate(Player player) {
        player.removeCoins(player.getNumCoins()/2);
    }

    @Override
    public String getDescription() {
        return "Boo ruba delle monete ad un avversario.";
    }

    @Override
    public boolean isOnOthers () {
        return true;
    }

}
