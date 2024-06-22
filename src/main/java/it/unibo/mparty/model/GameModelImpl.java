package it.unibo.mparty.model;

import it.unibo.mparty.model.player.api.Player;

import java.util.List;
import java.util.stream.Collectors;

public class GameModelImpl implements GameModel{

    private static final int TURNS_NUMBER = 10;

    private final List<Player> players;
    private final GameBoard board;
    private final Shop shop;
    private int turn = 0;
    private int actualPlayerIndex = 0;

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
        final int maxStars = players.stream().map(Player::getNumStars).sorted().limit(1).reduce(0 , Integer::sum);
        List<Player> winners = players.stream().filter(p -> p.getNumStars() == maxStars).toList();
        if (winners.size() != 1){
            final int maxMoney = winners.stream().map(Player::getNumCoins).sorted().limit(1).reduce(0 , Integer::sum);
            winners = winners.stream().filter(p -> p.getNumCoins() == maxMoney).toList();
        }
        return winners.get(0).getUsername();
    }

    @Override
    public boolean isOver() {
        return turn == TURNS_NUMBER;
    }
}
