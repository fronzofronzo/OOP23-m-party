package it.unibo.mparty.model.item.impl;

import java.util.Optional;

import it.unibo.mparty.model.player.api.Player;
import it.unibo.mparty.utilities.Position;

/**
 * class that implements the Boobell item.
 */
public class BooBell extends AbstractItem {

    private final static int BOOBELL_COST = 7;

    /**
     * Construct a new istance of BooBell item.
     */
    public BooBell() {
        super(ItemName.CAMPANA_BOO, BOOBELL_COST);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void activate(Player player, Optional<Player> target, Optional<Position> position) {
        int coins = target.get().getNumCoins() / 3;
        target.get().removeCoins(coins);
        player.addCoins(coins);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return "Boo ruba delle monete ad un avversario.";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isOnOthers() {
        return true;
    }

}
