package it.unibo.mparty.model.item.impl;

import java.util.Optional;

import it.unibo.mparty.model.player.api.Player;
import it.unibo.mparty.utilities.Position;

/**
 * class that implements the Golden Pipe item
 */
public class GoldenPipe extends AbstractItem{

    /**
     * Construct a new istance of GoldenPipe item
     */
    public GoldenPipe() {
        super(ItemName.TUBO_DORATO, 10);
    }
    

    /**
     * {@inheritDoc}
     */
    @Override
    public void activate(Player player, Optional<Player> target, Optional<Position> position) {
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
