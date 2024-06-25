package it.unibo.mparty.model;

import it.unibo.mparty.model.gameBoard.api.GameBoard;
import it.unibo.mparty.model.player.api.Player;
import it.unibo.mparty.model.player.impl.PlayerImplementation;

import java.util.ArrayList;
import java.util.List;

public class GameModelBuilderImpl implements GameModelBuilder{

    private final List<Player> players = new ArrayList<>();
    private String difficulty;

    @Override
    public void addPlayer(String nickname, String character) {
        final Player pl = new PlayerImplementation(nickname,character);
        players.add(pl);
    }

    @Override
    public void difficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public GameModel build() {
        return new GameModelImpl(players, this.difficulty);
    }
}
