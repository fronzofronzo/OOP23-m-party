package it.unibo.mparty.model.item.impl;

import it.unibo.mparty.model.player.api.Player;

/**
 * class that implements the CursedDice item
 */
public class CursedDice extends AbstractItem{

    public CursedDice (){
        super (ItemName.DADO_MALEDETTO,5);
    }
    @Override
    public void activate(Player player) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'activate'");
    }

    @Override
    public String getDescrition() {
        return "Il dado ha solo numeri da 1 a 3. Può essere attivato su un giocatore a tua scelta";
    }

}
