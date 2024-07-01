package it.unibo.mparty.controller;

import it.unibo.mparty.model.GameModel;
import it.unibo.mparty.model.item.impl.ItemName;

public interface GameController {

    int rollDice();

    void movePlayer();

    boolean buyItem(ItemName item);

    void startGame(GameModel model);

    void endGame();
}
