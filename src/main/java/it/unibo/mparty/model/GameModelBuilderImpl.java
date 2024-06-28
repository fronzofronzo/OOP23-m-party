package it.unibo.mparty.model;

import it.unibo.mparty.model.gameBoard.api.GameBoard;
import it.unibo.mparty.model.player.api.Player;
import it.unibo.mparty.model.player.api.PlayerBuilder;
import it.unibo.mparty.model.player.impl.PlayerBuilderImplementation;
import it.unibo.mparty.model.player.impl.PlayerImplementation;

import java.util.ArrayList;
import java.util.List;

public class GameModelBuilderImpl implements GameModelBuilder{

    private final List<Player> players = new ArrayList<>();
    private String difficulty;

    @Override
    public GameModelBuilder addPlayer(String nickname, String character) {
        final PlayerBuilder builder = new PlayerBuilderImplementation();
        final Player pl = builder.username(nickname)
                                    .character(character)
                                    .buildPlayer();
        players.add(pl);
        return this;
    }

    @Override
    public GameModelBuilder difficulty(String difficulty) {
        this.difficulty = difficulty;
        return this;
    }

    @Override
    public GameModel build() {
        return new GameModelImpl(players, this.difficulty);
    }
}
