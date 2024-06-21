package it.unibo.mparty.controller.minigames.nanogram.api;

import it.unibo.mparty.model.minigames.nanogram.util.Pair;

public interface NanogramController {
    void startGame();

    void getClick(Pair<Integer, Integer> click);

    void updateModel();

    void updateView();
}