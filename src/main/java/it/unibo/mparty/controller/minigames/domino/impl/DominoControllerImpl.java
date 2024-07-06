package it.unibo.mparty.controller.minigames.domino.impl;

import it.unibo.mparty.controller.minigames.domino.api.DominoController;
import it.unibo.mparty.model.minigames.domino.api.DominoModel;
import it.unibo.mparty.model.minigames.domino.impl.DominoModelImpl;
import it.unibo.mparty.model.minigames.domino.api.Tile;
import it.unibo.mparty.model.minigames.domino.impl.TileImpl;
import it.unibo.mparty.utilities.Pair;
import it.unibo.mparty.view.minigames.domino.DominoMessage;
import it.unibo.mparty.view.minigames.domino.api.DominoView;

import java.util.List;

public class DominoControllerImpl implements DominoController {

    private final DominoModel model;
    private final DominoView view;
    private String player1;
    private String player2;
    private boolean isPlayer1Turn;

    public DominoControllerImpl(final DominoView view) {
        this.model = new DominoModelImpl();
        this.view = view;
    }

    @Override
    public void initGame(List<String> players) {
        this.model.setUpPlayers(players);
        this.model.getBoardTile().addObserver(this.view);
        this.player1 = players.get(0);
        this.player2 = players.get(1);

        this.isPlayer1Turn = this.model.initializeTurn(this.player1, this.player2);
        this.view.setTurn(this.isPlayer1Turn);
        this.view.setPlayerName(true, this.player1);
        this.view.setPlayerName(false, this.player2);

        this.updatePlayersTiles();
        this.updateTurn();
    }

    @Override
    public void playTile(final int sideA, final int sideB) {
        Tile selectedTile = new TileImpl(sideA, sideB);
        String currentPlayer = this.isPlayer1Turn ? this.player1 : this.player2;
        boolean isValidMove = this.model.checkAndAddToBoard(currentPlayer, selectedTile);
        if (isValidMove) {
            this.updatePlayersTiles();
            this.isPlayer1Turn = !this.isPlayer1Turn;
            this.updateTurn();
            this.checkDraw();
            this.endGame();
        } else {
            this.view.setMessage(DominoMessage.MOVE_NOT_VALID);
        }
    }

    @Override
    public void checkDraw() {
        String currentPlayer = isPlayer1Turn ? player1 : player2;
        if (this.model.canDrawTile(currentPlayer)) {
            this.view.setMessage(DominoMessage.DRAW_TILE);
            this.view.playerCanDraw();
        } else {
            this.view.playerCantDraw();
        }
    }

    @Override
    public void drawTile() {
        this.model.drawTile(isPlayer1Turn ? player1 : player2);
        this.updatePlayersTiles();
        this.checkDraw();
        this.updateTurn();
    }

    @Override
    public void endGame() {
        Pair<String, Integer> winner = this.model.getResult();
        if (winner != null && winner.getFirst() != null) {
            this.view.showResult(winner);
            this.view.getMainController().saveMinigameResult(this.model.getResult());
        }
    }

    private void updatePlayersTiles() {
        this.view.setPlayerTiles(true, this.model.getPlayersTiles().getPlayerTiles(this.player1));
        this.view.setPlayerTiles(false, this.model.getPlayersTiles().getPlayerTiles(this.player2));
    }

    private void updateTurn() {
        this.view.setTurn(this.isPlayer1Turn);
    }
}
