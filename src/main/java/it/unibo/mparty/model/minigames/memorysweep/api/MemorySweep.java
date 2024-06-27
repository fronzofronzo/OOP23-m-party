package it.unibo.mparty.model.minigames.memorysweep.api;

import java.util.Set;

import it.unibo.mparty.utilities.*;

public interface MemorySweep {

    /**
     * enum describing the types of event that can appen when a button is clicked:
     *  - RIGHT_CHOICE: the player guessed the right button of the sequence that needs to be recreated but the guess isn't over
     *  - TURN_END: the player guessed right all the buttons of the sequence which needed to be recreated, his turn has end now it'the other player turn
     *  - LOSS: the player guessed the wrong button, so he has lost
     */
    enum HitType{
        RIGHT_CHOICE,TURN_END,LOSS
    }

    /**
     * method for setting the random list that the players should try to recreate
     * @return the list of random buttons to be recreated by players
     */
    public Set<Position> getRandomList();

    /**
     * method for getting the random list that the players should try to recreate
     */
    public void setRandomList();
    
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
     * method for getting the winner of the game
     * @return the winner of the game
     */
    public Set<Position> getWinner();
}
