package it.unibo.mparty.model.minigames.memorysweep.api;

import java.util.Set;

import it.unibo.mparty.utilities.*;

public interface MemorySweep {

    enum HitType{
        RIGHT_CHOICE,TURN_END,LOSS
    }

    /**
     * method for getting the random list that the players should try to recreate
     * @return the list of random buttons to be recreated by players
     */
    public Set<Position> getRandomList();
    
    /**
     * method for knowing wheter a player is done with his try
     * @param p the position clicked by a player
     * @return wheter the list of buttons clicked by the players is finished or not
     */
    public HitType hit(Position p);

    /**
     * method for getting which turn is it
     * @return true if it is the turn of one player, false if it is the turn of the other player
     */
    public boolean getTurn();
    /**
     * method to check wheter either player1 or player2 has lost
     * @return true if either player1 or player2 flase otherwise
     */
    public boolean isOver(Set<Position> player);
}
