package it.unibo.mparty.controller;

import it.unibo.mparty.model.GameModel;
import it.unibo.mparty.view.GameView;

public class GameControllerImpl implements GameController{

    private final GameView view;
    private GameModel model;

    public GameControllerImpl(final GameView view){
        this.view = view;
    }

    @Override
    public void rollDice() {

    }

    @Override
    public void movePlayer() {

    }

    @Override
    public boolean buyItem() {
        return false;
    }

    @Override
    public void startGame() {

    }

    @Override
    public void endGame() {

    }
}
