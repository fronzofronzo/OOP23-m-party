package it.unibo.mparty.model;

import it.unibo.mparty.model.player.api.Player;

import java.util.List;

public class GameModelImpl implements GameModel{

    private final List<Player> players;
    private final GameBoard board;
    private final Shop shop;

    /**
     * {@inheritDoc}
     */
    @Override
    public void movePlayer() {

    }

    @Override
    public String getActiveMinigame() {
        return "";
    }

    @Override
    public boolean isActiveMinigame() {
        return false;
    }

    @Override
    public void nextPlayer() {

    }

    @Override
    public String getWinner() {
        return "";
    }

    @Override
    public boolean isOver() {
        return false;
    }
}
