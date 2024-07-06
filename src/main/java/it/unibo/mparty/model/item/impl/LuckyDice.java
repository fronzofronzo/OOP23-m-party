package it.unibo.mparty.model.item.impl;

import java.util.Optional;

import it.unibo.mparty.model.player.api.Player;
import it.unibo.mparty.utilities.Position;

/**
 * class that implements the Lucky Dice item.
 */
public class LuckyDice extends AbstractItem {

    private final static int LUCKYDICE_COST =5;

    /**
     * Constructs a new instance of LuckyDice item.
     */
    public LuckyDice() {
        super(ItemName.DADO_FORTUNATO, LUCKYDICE_COST);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void activate(Player player, Optional<Player> target, Optional<Position> position) {
        player.getDice().setMinNumber(4);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return "Il dado ha solo numeri da 4 a 6";
    }

}
