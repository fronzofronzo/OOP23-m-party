package it.unibo.mparty.controller.minigames.domino.impl;

import it.unibo.mparty.controller.minigames.domino.api.DominoController;
import it.unibo.mparty.model.minigames.domino.api.DominoModel;
import it.unibo.mparty.model.minigames.domino.impl.DominoModelImpl;
import it.unibo.mparty.model.minigames.domino.api.Tile;
import it.unibo.mparty.model.minigames.domino.impl.TileImpl;
import it.unibo.mparty.model.player.api.Player;
import it.unibo.mparty.view.minigames.domino.DominoMessage;
import it.unibo.mparty.view.minigames.domino.api.DominoView;

public class DominoControllerImpl implements DominoController {

    private final DominoModel model;
    private final DominoView view;
    private final Player player1;
    private final Player player2;
    private boolean isPlayer1Turn;

    public DominoControllerImpl(final DominoView view, final Player player1, final Player player2) {
        this.model = new DominoModelImpl();
        this.view = view;
        this.player1 = player1;
        this.player2 = player2;
    }

    @Override
    public void setUp() {
        this.model.setPlayerTiles(this.player1, this.player2);

        this.isPlayer1Turn = this.model.initializeTurn(this.player1, this.player2);
        this.view.setTurn(this.isPlayer1Turn);
        this.view.setPlayerName(true, this.player1.getUsername());
        this.view.setPlayerName(false, this.player2.getUsername());

        this.updatePlayersTiles();
        this.updateTurn();
    }

    @Override
    public void playTile(final int sideA, final int sideB) {
        Tile selectedTile = new TileImpl(sideA, sideB);
        Player currentPlayer = this.isPlayer1Turn ? this.player1 : this.player2;
        boolean isValidMove = this.model.checkAndAddToBoard(currentPlayer, selectedTile);
        if (isValidMove) {
            this.updatePlayersTiles();
            this.isPlayer1Turn = !this.isPlayer1Turn;
            this.updateTurn();
            this.checkDraw();
            this.updateBoard(selectedTile.isDoubleSide());
            this.haveWinner();
        } else {
            this.view.setMessage(DominoMessage.MOVE_NOT_VALID);
        }
    }

    @Override
    public void checkDraw() {
        Player currentPlayer = isPlayer1Turn ? player1 : player2;
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

    private void updatePlayersTiles() {
        this.view.setPlayerTiles(true, this.model.getPlayersTiles().getPlayerTiles(this.player1));
        this.view.setPlayerTiles(false, this.model.getPlayersTiles().getPlayerTiles(this.player2));
    }

    private void updateTurn() {
        this.view.setTurn(this.isPlayer1Turn);
    }

    private void updateBoard(boolean isDoubleSide) {
        this.view.setBoard(this.model.getBoardTile().getBoardTiles(), isDoubleSide);
    }

    private void haveWinner() {
        Player winner = this.model.getWinner(this.player1, this.player2);
        if (winner != null) {
            this.view.gameEnd(winner.getUsername());
        }
    }
}
