package it.unibo.mparty.model.item.impl;

import it.unibo.mparty.model.player.api.Player;

public class LuckyDice extends AbstractItem{

    public LuckyDice() {
        super(ItemName.DADO_FORTUNATO, 7);
    }

    @Override
    public void activate(Player player) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'activate'");
    }

    @Override
    public String getDescrition() {
        return "Si ottiene qualsiasi risultato desiderato da 1 a 10.";
    }

}
