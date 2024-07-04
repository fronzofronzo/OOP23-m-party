package it.unibo.mparty.controller;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import it.unibo.mparty.model.GameModel;
import it.unibo.mparty.model.item.impl.ItemName;
import it.unibo.mparty.utilities.Direction;
import it.unibo.mparty.utilities.GameStatus;
import it.unibo.mparty.utilities.Pair;
import it.unibo.mparty.view.GameView;

public class GameControllerImpl implements GameController{

    private final GameView view;
    private GameModel model;

    public GameControllerImpl(final GameView view){
        this.view = view;
    }

    @Override
    public void rollDice() {
        this.view.showResultDice(this.model.rollDice());
        this.view.updateCommands(Collections.emptyList(), this.model.getMessage());
    }

    @Override
    public void movePlayer(Optional<Direction> dir) {
        this.model.movePlayer(dir);
        this.view.updateCommands(Collections.emptyList(), this.model.getMessage());
        this.view.updatePlayerPos(this.model.getActualPlayerInfo());
    }

    @Override
    public void action() throws IOException {
        this.model.action();
        //if (this.model.getActiveMinigame().isPresent()) {
        //    this.view.setScene(this.model.getActiveMinigame().get());
        //}
        this.view.updateCommands(Collections.emptyList(), this.model.getMessage());
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
            this.view.setUpBoard(this.model.getBoardDimensions(), this.model.getBoardConfiguration(), this.model.getPlayersNicknames(), this.model.getActualPlayerInfo().getY());
            this.view.updateCommands(Collections.emptyList(), this.model.getMessage());
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
}
