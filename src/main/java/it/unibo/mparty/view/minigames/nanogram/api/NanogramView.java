package it.unibo.mparty.view.minigames.nanogram.api;

import it.unibo.mparty.model.minigames.nanogram.util.Pair;

public interface NanogramView {
    Pair<Integer, Integer> userClicked();

    void displayStatusMessage(String message, String messageType);
}