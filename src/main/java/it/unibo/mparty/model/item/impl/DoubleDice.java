package it.unibo.mparty.model.item.impl;

import java.util.Optional;

import it.unibo.mparty.model.player.api.Player;
import it.unibo.mparty.utilities.Position;

/**
 * class that implements the Double Dice item.
 */
public class DoubleDice extends AbstractItem {

    private static final int DOUBLEDICE_COST = 7;

    /**
     * Construct a new istance of DoubleDice item.
     */
    public DoubleDice() {
        super(ItemName.DOPPIO_DADO, DOUBLEDICE_COST);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return "Si lancia 2 dadi per avanzare";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void activate(final Player player, final Optional<Player> target, final Optional<Position> position) {
        player.getDice().setNumberOfAttempts(2);
    }

}
