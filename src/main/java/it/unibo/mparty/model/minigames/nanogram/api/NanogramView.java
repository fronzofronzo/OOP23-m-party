package it.unibo.mparty.model.minigames.nanogram.api;

import it.unibo.mparty.model.minigames.nanogram.impl.Pair;

public interface NanogramView {
    Pair<Integer, Integer> userClicked();

    void displayStatusMessage(String message, String messageType);
}