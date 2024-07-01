package it.unibo.mparty.controller;

import java.io.IOException;

import it.unibo.mparty.model.GameModel;
import it.unibo.mparty.utilities.Position;
import it.unibo.mparty.view.GameView;
import javafx.scene.layout.Pane;

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
    }

    @Override
    public boolean buyItem() {
        return false;
    }

    @Override
    public void startGame(GameModel model) {
        this.model = model;
        try {
            this.view.setScene("GameBoard.fxml");
            this.drawBoard();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void drawBoard() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                this.view.
            }
        }
    }

    @Override
    public void endGame() {

    }
}
