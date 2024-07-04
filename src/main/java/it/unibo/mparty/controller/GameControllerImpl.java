package it.unibo.mparty.controller;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;

import it.unibo.mparty.model.GameModel;
import it.unibo.mparty.model.item.impl.ItemName;
import it.unibo.mparty.utilities.Direction;
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
            this.view.showResultDice(this.model.rollDice());
            this.switchStatus();
        }
    }

    @Override
    public void movePlayer() {
        if (!this.model.movePlayer()) {
            this.view.updateCommands(null, this.model.getDirections());   
        }
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
            this.view.setUpBoard(this.model.getBoardDimensions(), this.model.getBoardConfiguration(), this.model.getPlayersNicknames(), this.model.getPlayerPosition(this.model.getPlayersNicknames().stream().findFirst().get()));
            this.view.updateCommands(Collections.emptyList(), Collections.EMPTY_SET);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveMinigameResult(Pair<String, Integer> result) {
        this.model.endMinigame(result);
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
                this.status = GameStatus.ROLL_DICE;
            }
        };

    }

    @Override
    public void movePlayerWithDirection(Direction dir) {
        this.model.movePlayerWithDirection(dir);
        this.movePlayer();
    }
}
