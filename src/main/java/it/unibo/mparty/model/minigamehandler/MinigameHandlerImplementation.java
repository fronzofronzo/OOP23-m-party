package it.unibo.mparty.model.minigamehandler;

import it.unibo.mparty.model.minigames.MinigameModel;
import it.unibo.mparty.model.minigames.MinigameType;
import it.unibo.mparty.model.player.api.Player;
import it.unibo.mparty.utilities.RandomFromSet;
import org.reflections.Reflections;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class provides an implementation for {@link MinigameHandler} interface.
 */
public class MinigameHandlerImplementation implements MinigameHandler {

    private String actualMinigame;
    private List<Player> players;

    /**
     * Constructor of a new implementation of a {@link MinigameHandler}.
     */
    public MinigameHandlerImplementation() {
        this.players = Collections.emptyList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startMinigame(final List<Player> players,
                              final MinigameType type){
        this.actualMinigame = generateMinigame(type);
        this.players = players;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMinigame() {
        return this.actualMinigame;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getUsersPlaying() {
        return this.players.stream()
                .map(Player::getUsername)
                .toList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isInGame() {
        return actualMinigame != null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stopMinigame() {
        this.actualMinigame = null;
        this.players = Collections.emptyList();
    }

    private String generateMinigame(final MinigameType type) {
        final Logger logger = Logger.getAnonymousLogger();
        final Set<String> minigames = new HashSet<>();
        final Reflections reflections = new Reflections("it.unibo.mparty.model.minigames");
        final Set<Class<? extends MinigameModel>> classes =
                reflections.getSubTypesOf(MinigameModel.class);
        for (final Class<? extends MinigameModel> cl : classes) {
            try {
                if (!cl.isInterface()) {
                    final MinigameModel minigame =
                            cl.getDeclaredConstructor().newInstance();
                    if (minigame.getType().equals(type)) {
                        minigames.add(minigame.getName());
                    }
                }
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException
                     | NoSuchMethodException e) {
                logger.log(Level.SEVERE, e.toString());
            }
        }
        return RandomFromSet.get(minigames);
    }
}
