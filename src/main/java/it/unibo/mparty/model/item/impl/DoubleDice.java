package it.unibo.mparty.model.item.impl;

import java.util.Optional;

import it.unibo.mparty.model.player.api.Player;
import it.unibo.mparty.utilities.Position;

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
    public void activate(Player player, Optional<Player> target, Optional<Position> position) {
        player.getDice().setNumberOfAttempts(2);
    }

}
