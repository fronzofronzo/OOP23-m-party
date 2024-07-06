package it.unibo.mparty.model.item.impl;

import java.util.Optional;

import it.unibo.mparty.model.player.api.Player;
import it.unibo.mparty.utilities.Position;

/**
 * class that implements the CursedDice item
 */
public class CursedDice extends AbstractItem{

    /**
     * Construct a new istance of CursedDice item
     */
    public CursedDice (){
        super (ItemName.DADO_MALEDETTO,5);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void activate(Player player, Optional<Player> target, Optional<Position> position) {
        target.get().getDice().setMaxNumber(3);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return "Il dado ha solo numeri da 1 a 3. Puo' essere attivato su un giocatore a tua scelta";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isOnOthers () {
        return true;
    }

}
