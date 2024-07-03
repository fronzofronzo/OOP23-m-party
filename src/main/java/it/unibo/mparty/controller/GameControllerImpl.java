package it.unibo.mparty.controller;

import java.io.IOException;

import java.util.ArrayList;

import it.unibo.mparty.model.GameModel;
import it.unibo.mparty.model.item.impl.ItemName;
import it.unibo.mparty.utilities.GameStatus;
import it.unibo.mparty.utilities.Pair;
import it.unibo.mparty.view.GameView;

public class GameControllerImpl implements GameController{

    private final GameView view;
    private GameModel model;
    private GameStatus status = GameStatus.ROLL_DICE;

    public GameControllerImpl(final GameView view){
        this.view = view;
    }

    @Override
    public void rollDice() {
        if(this.status.equals(GameStatus.ROLL_DICE)){
            //this.view.showRollResult(this.model.rollDice());
            this.switchStatus();
        }
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
            this.view.setUpBoard(this.model.getBoardDimensions(), this.model.getBoardConfiguration(), new ArrayList<>());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveMinigameResult(Pair<String, Integer> result) {
        this.model.endMinigame(result);
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

    private void switchStatus(){
        switch (this.status) {
            case ROLL_DICE -> {
                this.status = GameStatus.MOVE_PLAYER;
            }
            case MOVE_PLAYER -> {

            }
            case USE_ITEM -> {
            }
        };

    }
}
