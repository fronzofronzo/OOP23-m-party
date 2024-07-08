package it.unibo.mparty.model;

import it.unibo.mparty.model.player.api.Player;
import it.unibo.mparty.model.player.api.PlayerBuilder;
import it.unibo.mparty.model.player.impl.PlayerBuilderImplementation;

import java.util.ArrayList;
import java.util.List;

public class GameModelBuilderImpl implements GameModelBuilder{

    private static final int MIN_PLAYERS  = 2;
    private static final int MAX_PLAYERS = 4;

    private final List<Player> players = new ArrayList<>();
    private String difficulty;

    @Override
    public GameModelBuilder addPlayer(String nickname, String character) throws IllegalArgumentException {
        final PlayerBuilder builder = new PlayerBuilderImplementation();
        final Player pl = builder.username(nickname)
                                    .character(character)
                                    .buildPlayer();
        if(players.stream().anyMatch(p -> p.getUsername().equals(pl.getUsername()) || p.getCharacter().equals(pl.getCharacter()))){
            throw new IllegalArgumentException("Il player con questo nome/personaggio è già presente ");
        }
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

    @Override
    public boolean enoughPlayers() {
        return players.size() >= MIN_PLAYERS;
    }

    @Override
    public boolean isFull() {
        return players.size() == MAX_PLAYERS;
    }

}
