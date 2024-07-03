package it.unibo.mparty.controller;

import it.unibo.mparty.model.GameModel;
import it.unibo.mparty.utilities.Position;
import it.unibo.mparty.model.item.impl.ItemName;

public interface GameController {

    int rollDice();

    Position movePlayer();

    boolean buyItem(ItemName item);

    void startGame(GameModel model);

    void endGame();
}
