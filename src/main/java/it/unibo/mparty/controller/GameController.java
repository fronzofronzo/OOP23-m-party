package it.unibo.mparty.controller;

import it.unibo.mparty.model.GameModel;
import it.unibo.mparty.utilities.Position;

public interface GameController {

    int rollDice();

    Position movePlayer();

    boolean buyItem();

    void startGame(GameModel model);

    void endGame();
}
