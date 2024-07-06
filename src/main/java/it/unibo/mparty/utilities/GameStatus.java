package it.unibo.mparty.utilities;

public enum GameStatus {
    ROLL_DICE, MOVE_PLAYER, MOVING_PLAYER, ACTIVE_SLOT, END_TURN;

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
