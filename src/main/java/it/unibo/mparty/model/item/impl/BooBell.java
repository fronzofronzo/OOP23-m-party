package it.unibo.mparty.model.item.impl;

import it.unibo.mparty.model.player.api.Player;

public class BooBell extends AbstractItem{

    public BooBell() {
        super(ItemName.CAMPANA_BOO, 10);
    }

    @Override
    public void activate(Player player) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'activate'");
    }

    @Override
    public String getDescrition() {
        return "Boo ruba delle monete ad un avversario.";
    }

}
