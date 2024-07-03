package it.unibo.mparty.model.minigameHandler;

import it.unibo.mparty.model.minigames.MinigameType;
import it.unibo.mparty.model.player.api.Player;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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
    public boolean isInGame() {
        return actualMinigame.isEmpty();
    }

    @Override
    public void stopMinigame() {
        this.actualMinigame = null;
        this.players = Collections.emptyList();
        this.type = null;
    }

    private String generateRandomMinigame(MinigameType type) throws Exception {
        final BufferedReader reader = new BufferedReader(new FileReader(type + ".txt"));
        String name = null;
        final List<String> minigames = new ArrayList<>();
        while ((name = reader.readLine()) != null){
            minigames.add(name);
        }
        final Random random = new Random();
        return minigames.get(random.nextInt(minigames.size()));
    }
}
