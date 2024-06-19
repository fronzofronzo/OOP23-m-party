package it.unibo.mparty.model.minigames.nanogram.api;

import it.unibo.mparty.model.minigames.nanogram.impl.Pair;

public interface NanogramController {
    void startGame();

    void getClick(Pair<Integer, Integer> click);

    void updateModel();

    void updateView();
}