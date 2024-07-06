package it.unibo.mparty.view.endGame.api;

import it.unibo.mparty.model.player.api.Player;

import java.util.List;

public interface EndGameView {
    void showResults(List<Player> players);
}
