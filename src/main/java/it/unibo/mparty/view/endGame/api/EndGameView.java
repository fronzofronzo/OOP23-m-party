package it.unibo.mparty.view.endGame.api;

import java.util.List;

public interface EndGameView {

    void setPlayerColumn(List<String> playerNames);

    void setCoinColumn(List<Integer> playerCoins);

    void setStarColumn(List<Integer> playerStars);
}
