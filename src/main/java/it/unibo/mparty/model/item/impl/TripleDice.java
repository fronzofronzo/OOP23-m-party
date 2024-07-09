package it.unibo.mparty.model.item.impl;

import java.util.Optional;

import it.unibo.mparty.model.player.api.Player;
import it.unibo.mparty.utilities.Position;

/**
 * Class that implements the Triple Dice item.
 */
public class TripleDice extends AbstractItem {

    private static final int TRIPLEDICE_COST = 10;

    /**
     * Construct a new istance of TripleDice item.
     */
    public TripleDice() {
        super(ItemName.TRIPLO_DADO, TRIPLEDICE_COST);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return "Si lancia 3 dadi per avanzare";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void activate(final Player player, final Optional<Player> target, final Optional<Position> position) {
        player.getDice().setNumberOfAttempts(3);
    }

}
