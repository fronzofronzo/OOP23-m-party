package it.unibo.mparty.controller;

import it.unibo.mparty.model.GameModel;

public interface GameController {

    int rollDice();

    void movePlayer();

    boolean buyItem();

    void startGame(GameModel model);

    void endGame();
}
