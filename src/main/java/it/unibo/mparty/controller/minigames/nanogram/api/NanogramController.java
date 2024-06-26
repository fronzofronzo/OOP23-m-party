package it.unibo.mparty.controller.minigames.nanogram.api;

import it.unibo.mparty.utilities.Pair;

public interface NanogramController {
    void startGame();

    void getClick(Pair<Integer, Integer> click);

    void updateModel();

    void updateView();
}