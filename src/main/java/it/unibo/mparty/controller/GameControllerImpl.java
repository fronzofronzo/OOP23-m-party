package it.unibo.mparty.controller;

import java.io.IOException;

import it.unibo.mparty.model.GameModel;
import it.unibo.mparty.model.item.impl.ItemName;
import it.unibo.mparty.utilities.Position;
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
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void endGame() {

    }
}
