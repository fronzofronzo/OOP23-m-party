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

/**
 * Implementation of the {@link DominoController} interface.
 */
public class DominoControllerImpl implements DominoController {

    private final DominoModel model;
    private final DominoView view;
    private String player1;
    private String player2;
    private boolean isPlayer1Turn;

    /**
     * Constructs a new DominoControllerImpl with the specified view.
     *
     * @param view the domino view
     */
    public DominoControllerImpl(final DominoView view) {
        this.model = new DominoModelImpl();
        this.view = view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initGame(final List<String> players) {
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void playTile(final int sideA, final int sideB) {
        final Tile selectedTile = new TileImpl(sideA, sideB);
        final String currentPlayer = this.isPlayer1Turn ? this.player1 : this.player2;
        final boolean isValidMove = this.model.checkAndAddToBoard(currentPlayer, selectedTile);
        if (isValidMove) {
            this.updatePlayersTiles();
            this.isPlayer1Turn = !this.isPlayer1Turn;
            this.updateTurn();
            this.checkDraw();
            if (this.model.isOver()) {
                this.endGame();
            }
        } else {
            this.view.setMessage(DominoMessage.MOVE_NOT_VALID);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawTile() {
        this.model.drawTile(this.isPlayer1Turn ? this.player1 : this.player2);
        this.updatePlayersTiles();
        this.checkDraw();
        this.updateTurn();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void endGame() {
        final Pair<String, Integer> winner = this.model.getResult();
        if (winner != null && winner.getFirst() != null) {
            this.view.showResult(winner);
            this.view.getMainController().saveMinigameResult(this.model.getResult());
        }
    }

    private void checkDraw() {
        final String currentPlayer = this.isPlayer1Turn ? this.player1 : this.player2;
        if (this.model.canDrawTile(currentPlayer)) {
            this.view.setMessage(DominoMessage.DRAW_TILE);
            this.view.playerCanDraw();
        } else {
            this.view.playerCantDraw();
        }

        if (this.model.isOver()) {
            this.endGame();
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
