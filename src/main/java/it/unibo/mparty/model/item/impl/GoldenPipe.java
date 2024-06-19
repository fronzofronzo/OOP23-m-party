package it.unibo.mparty.model.item.impl;

import it.unibo.mparty.model.player.api.Player;

public class GoldenPipe extends AbstractItem{

    public GoldenPipe() {
        super(ItemName.TUBO_DORATO, 15);
    }

    @Override
    public void activate(Player player) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'activate'");
    }

    @Override
    public String getDescrition() {
        return "Vieni teletrasportato nella casella della stella";
    }

}
