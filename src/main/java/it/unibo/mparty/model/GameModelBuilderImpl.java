package it.unibo.mparty.model;

import it.unibo.mparty.model.player.api.Player;
import it.unibo.mparty.model.player.api.PlayerBuilder;
import it.unibo.mparty.model.player.impl.PlayerBuilderImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides an implementation of {@link GameModelBuilder} interface.
 * It implements all the methods of the interface.
 */
public class GameModelBuilderImpl implements GameModelBuilder {

    private static final int MIN_PLAYERS = 2;
    private static final int MAX_PLAYERS = 4;

    private final List<Player> players = new ArrayList<>();
    private String difficulty;

    /**
     * {@inheritDoc}
     */
    @Override
    public GameModelBuilder addPlayer(final String nickname, final String character){
        final PlayerBuilder builder = new PlayerBuilderImpl();
        final Player pl = builder.username(nickname)
                .character(character)
                .buildPlayer();
        if (players.stream().anyMatch(p -> p.getUsername().equals(pl.getUsername())
                || p.getCharacter().equals(pl.getCharacter()))) {
            throw new IllegalArgumentException("Il player con questo " +
                    "nome/personaggio e' gia' presente ");
        }
        if (!this.isFull()) {
            players.add(pl);
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameModelBuilder difficulty(final String difficulty) {
        this.difficulty = difficulty;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameModel build() {
        if (this.enoughPlayers()) {
            return new GameModelImpl(players, this.difficulty);
        } else {
            throw new IllegalStateException("There are not enough players.");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean enoughPlayers() {
        return players.size() >= MIN_PLAYERS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isFull() {
        return players.size() == MAX_PLAYERS;
    }

}
