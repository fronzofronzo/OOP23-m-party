package it.unibo.mparty.model.player.api;

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

}
