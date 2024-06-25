package it.unibo.mparty.model.minigameHandler;

import java.io.BufferedReader;
import java.io.FileReader;

public class MinigameHandlerImplementation implements MinigameHandler{

    private String actualMinigame = null;

    @Override
    public void startMinigame() {
        final BufferedReader reader = new BufferedReader(new FileReader("singlePlayerMinigames.txt"))
    }

    @Override
    public String getMinigame() {
        return "";
    }

    @Override
    public void stopMinigame() {

    }

    private String getRandomMinigame(){

    }
}
