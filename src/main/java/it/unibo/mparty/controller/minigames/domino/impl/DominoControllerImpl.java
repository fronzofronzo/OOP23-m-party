package it.unibo.mparty.controller.minigames.domino.impl;

import it.unibo.mparty.controller.minigames.domino.api.DominoController;
import it.unibo.mparty.model.minigames.domino.api.DominoModel;
import it.unibo.mparty.model.minigames.domino.impl.DominoModelImpl;
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
        this.model.initDomino(this.player1, this.player2);

        this.view.setPlayerName(true, this.player1.getUsername());
        this.view.setPlayerName(false, this.player2.getUsername());

        this.updatePlayersTiles();
        this.updateTurn();
    }

    @Override
    public void playTile(final int sideA, final int sideB) {
        if (this.isPlayer1Turn) {
            if (!this.model.checkMove(this.player1, new TileImpl(sideA, sideB))) {
                this.view.setMessage(DominoMessage.MOVE_NOT_VALID);
            }
        } else {
            if (!this.model.checkMove(this.player2, new TileImpl(sideA, sideB))) {
                this.view.setMessage(DominoMessage.MOVE_NOT_VALID);
            }
        }
        this.drawTile();
        this.updatePlayersTiles();
        this.updateTurn();
        this.updateBoard();
        this.haveWinner();
    }

    @Override
    public void drawTile() {
        if (isPlayer1Turn && this.model.canDrawTile(player1)) {
            this.view.setMessage(DominoMessage.DRAW_TILE);
            this.view.playerCanDraw();
        } else if (!isPlayer1Turn && this.model.canDrawTile(player2)) {
            this.view.setMessage(DominoMessage.DRAW_TILE);
            this.view.playerCanDraw();
        } else {
            this.view.playerCantDraw();
        }

        this.updatePlayersTiles();
        this.updateTurn();
        this.updateBoard();
    }

    @Override
    public void updatePlayersTiles() {
        this.view.setPlayerTiles(true, this.model.getPlayersTiles().getPlayerTiles(this.player1));
        this.view.setPlayerTiles(false, this.model.getPlayersTiles().getPlayerTiles(this.player2));
    }

    @Override
    public void updateTurn() {
        this.isPlayer1Turn = this.model.isPlayer1Turn(this.player1, this.player2);

        this.view.setTurn(isPlayer1Turn, isPlayer1Turn ?
                this.player1.getUsername() : this.player2.getUsername());
    }

    @Override
    public void updateBoard() {
        this.view.setBoard(this.model.getBoardTile().getBoardTiles());
    }

    @Override
    public void haveWinner() {
        Player winner = this.model.getWinner(this.player1, this.player2);
        if (winner != null) {
            this.view.setWinner(winner.getUsername());
        }
    }
}
