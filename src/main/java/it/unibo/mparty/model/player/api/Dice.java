package it.unibo.mparty.model.player.api;

/**
 * This interface models a dice. Each player has his own dice, which can be
 * modified with items effect.
 */
public interface Dice {

    /**
     * Sets the number of attempts that can be done when the player rolls
     * the dice
     * @param num of attempts
     */
    void setNumberOfAttempts(int num);

    /**
     * Sets max number that player can make when the player rolls dice
     * @param num to set as max
     */
    void setMaxNumber(int num);

    /**
     * Sets minimum number that player can make when the player rolls dice
     * @param num to set as minimum
     */
    void setMinNumber(int num);

    /**
     * Generate the number of the dice when you roll it
     * @return the number generated when you roll the dice
     */
    int generateNumber();

}
