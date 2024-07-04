package it.unibo.mparty.model.item.impl;

import it.unibo.mparty.model.player.api.Player;

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

    @Override
    public void activate(Player player) {
        player.setPosition(null);
    }

    @Override
    public String getDescription() {
        return "Vieni teletrasportato nella casella prima della stella";
    }

}
