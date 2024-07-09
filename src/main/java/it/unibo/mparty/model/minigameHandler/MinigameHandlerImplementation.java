package it.unibo.mparty.model.minigameHandler;

import it.unibo.mparty.model.minigames.MinigameModel;
import it.unibo.mparty.model.minigames.MinigameType;
import it.unibo.mparty.model.player.api.Player;
import org.reflections.Reflections;
import java.lang.reflect.InvocationTargetException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.Random;

/**
 * This class provides an implementation for {@link MinigameHandler} interface.
 */
public class MinigameHandlerImplementation implements MinigameHandler{

    private String actualMinigame = null;
    private List<Player> players;
    private MinigameType type = null;

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
                              final MinigameType type) throws Exception {
        this.actualMinigame = generateRandomMinigame(type);
        this.type = type;
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
        this.type = null;
    }

    private String generateRandomMinigame(final MinigameType type) throws Exception {
        final BufferedReader reader = new BufferedReader(
                new InputStreamReader(ClassLoader.getSystemResourceAsStream(type + ".txt")));
        String name = null;
        final List<String> minigames = new ArrayList<>();
        while ((name = reader.readLine()) != null) {
            minigames.add(name);
        }
        final Random random = new Random();
        return minigames.get(random.nextInt(minigames.size()));
    }

    private String generateMinigame(MinigameType type){
        final List<String> minigames = new ArrayList<>();
        final Reflections reflections = new Reflections("it.unibo.mparty.model.minigames");
        Set<Class<? extends MinigameModel>> classes = reflections.getSubTypesOf(MinigameModel.class);
        for(Class<? extends MinigameModel> cl : classes){
            try {
                if(!cl.isInterface()){
                    final MinigameModel minigame = cl.getDeclaredConstructor().newInstance();
                    if(minigame.getType() == type){
                        minigames.add(minigame.getName());
                    }
                }
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
        final Random random = new Random();
        return minigames.get(random.nextInt(minigames.size()));
    }
}
