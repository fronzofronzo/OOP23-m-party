package it.unibo.mparty.model.item.impl;

import java.util.Optional;

import it.unibo.mparty.model.player.api.Player;
import it.unibo.mparty.utilities.Position;

/**
 * class that implements the Golden Pipe item.
 */
public class GoldenPipe extends AbstractItem {
    private static final int GOLDENPIPE_COST = 10;

    /**
     * Construct a new istance of GoldenPipe item.
     */
    public GoldenPipe() {
        super(ItemName.TUBO_DORATO, GOLDENPIPE_COST);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void activate(final Player player, final Optional<Player> target, final Optional<Position> position) {
        player.setPosition(position.get());
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return "Vieni teletrasportato nella casella prima della stella";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean needPosition() {
        return true;
    }
}
