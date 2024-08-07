package it.unibo.mparty.model.item.impl;

import java.util.Optional;

import it.unibo.mparty.model.player.api.Player;
import it.unibo.mparty.utilities.Position;

/**
 * Class that implements the Boobell item.
 */
public class BooBell extends AbstractItem {

    private static final int BOOBELL_COST = 5;

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
    public void activate(final Player player, final Optional<Player> target, final Optional<Position> position) {
        final int coins = target.get().getNumCoins() / 3;
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
