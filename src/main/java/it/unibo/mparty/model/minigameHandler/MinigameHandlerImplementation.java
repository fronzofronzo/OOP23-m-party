package it.unibo.mparty.model.minigameHandler;

import it.unibo.mparty.model.minigames.MinigameModel;
import it.unibo.mparty.model.minigames.MinigameType;
import it.unibo.mparty.model.player.api.Player;
import org.reflections.Reflections;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class MinigameHandlerImplementation implements MinigameHandler{

    private String actualMinigame = null;
    private List<Player> players;
    private MinigameType type = null;

    public MinigameHandlerImplementation(){
        this.players = Collections.emptyList();
    }

    @Override
    public void startMinigame(List<Player> players, MinigameType type) throws Exception {
        this.actualMinigame = generateRandomMinigame(type);
        this.type = type;
        this.players = players;
    }

    @Override
    public String getMinigame() {
        return this.actualMinigame;
    }

    @Override
    public List<String> getUsersPlaying() {
        return this.players.stream().map(Player::getUsername).toList();
    }

    @Override
    public boolean isInGame() {
        return actualMinigame != null;
    }

    @Override
    public void stopMinigame() {
        this.actualMinigame = null;
        this.players = Collections.emptyList();
        this.type = null;
    }

    private String generateRandomMinigame(MinigameType type) throws Exception {
        final BufferedReader reader = new BufferedReader(new InputStreamReader( ClassLoader.getSystemResourceAsStream(type + ".txt")));
        String name = null;
        final List<String> minigames = new ArrayList<>();
        while ((name = reader.readLine()) != null){
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
