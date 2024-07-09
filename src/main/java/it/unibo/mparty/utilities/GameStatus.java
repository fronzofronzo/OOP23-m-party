package it.unibo.mparty.utilities;

/**
 * Enum to represent the different status during the game
 */
public enum GameStatus {

    /**
     * Player has to roll dice.
     */
    ROLL_DICE,
    /**
     * Player has to move.
     */
    MOVE_PLAYER,
    /**
     * Player is moving.
     */
    MOVING_PLAYER,
    /**
     * Player has to activate the slot where he is.
     */
    ACTIVE_SLOT,
    /**
     * Player has to end his turn.
     */
    END_TURN;

    /**
     * Method tho get the next status. This method has to called on a GameStatus object.
     * @return - next game status.
     */
    public GameStatus switchStatus(){
        switch (this) {
            case ROLL_DICE -> {
                return GameStatus.MOVE_PLAYER;
            }
            case MOVE_PLAYER -> {
                return GameStatus.MOVING_PLAYER;
            }
            case MOVING_PLAYER -> {
                return GameStatus.ACTIVE_SLOT;
            }
            case ACTIVE_SLOT -> {
                return GameStatus.END_TURN;
            }
            case END_TURN -> {
                return GameStatus.ROLL_DICE;
            }
        };
        return null;
    }
}
