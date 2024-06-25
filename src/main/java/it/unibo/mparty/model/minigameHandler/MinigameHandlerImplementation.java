package it.unibo.mparty.model.minigameHandler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MinigameHandlerImplementation implements MinigameHandler{

    private String actualMinigame = null;

    @Override
    public void startMinigame() throws Exception {
        this.actualMinigame = generateRandomMinigame();
    }

    @Override
    public String getMinigame() {
        return this.actualMinigame;
    }

    @Override
    public void stopMinigame() {
        this.actualMinigame = null;
    }

    private String generateRandomMinigame() throws Exception {
        final BufferedReader reader = new BufferedReader(new FileReader("singlePlayerMinigames.txt"));
        String name = null;
        final List<String> minigames = new ArrayList<>();
        while ((name = reader.readLine()) != null){
            minigames.add(name);
        }
        final Random random = new Random();
        return minigames.get(random.nextInt(minigames.size()));
    }
}
