package it.unibo.mparty.controller;

import java.io.IOException;

import it.unibo.mparty.model.GameModel;
import it.unibo.mparty.model.item.impl.ItemName;
import it.unibo.mparty.utilities.Pair;
import it.unibo.mparty.view.GameView;

public class GameControllerImpl implements GameController{

    private final GameView view;
    private GameModel model;

    public GameControllerImpl(final GameView view){
        this.view = view;
    }

    @Override
    public int rollDice() {
        return this.model.rollDice();        
    }

    @Override
    public void movePlayer() {
        this.model.movePlayer();
        // this.view.clean()
        // this.view.drawBoard()
    }

    @Override
    public boolean buyItem(ItemName item) {
       // return this.model.buyItem()
        return true;
    }

    @Override
    public void startGame(GameModel model) {
        this.model = model;
        try {
            this.view.setScene("GameBoard.fxml");
            this.drawBoard();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveMinigameResult(Pair<String, Integer> result) {
        this.model.
    }

    private void drawBoard() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
            }
        }
    }

    @Override
    public void endGame() {
        // this.view.showWinner(this.model.getWinner)
    }
}
