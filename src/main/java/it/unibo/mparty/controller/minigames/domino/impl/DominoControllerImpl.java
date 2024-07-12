package it.unibo.mparty.controller.minigames.domino.impl;

import it.unibo.mparty.controller.minigames.domino.api.DominoController;
import it.unibo.mparty.model.minigames.domino.game.api.DominoModel;
import it.unibo.mparty.model.minigames.domino.game.impl.DominoModelImpl;
import it.unibo.mparty.model.minigames.domino.tile.api.Tile;
import it.unibo.mparty.model.minigames.domino.tile.impl.TileImpl;
import it.unibo.mparty.view.minigames.domino.DominoMessage;
import it.unibo.mparty.view.minigames.domino.api.DominoView;

import java.util.List;

/**
 * Implementation of the {@link DominoController} interface.
 */
public class DominoControllerImpl implements DominoController {

    private final DominoModel model;
    private DominoView view;
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
        this.setView(view);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initGame(final List<String> players) {
        this.model.setUpPlayers(players);
        this.model.addBoardObserver(this.view);
        this.player1 = players.get(0);
        this.player2 = players.get(1);

        this.isPlayer1Turn = this.model.initializeTurn(this.player1, this.player2);
        this.view.setTurn(this.isPlayer1Turn);
        this.view.setPlayerName(true, this.player1);
        this.view.setPlayerName(false, this.player2);
        this.view.updateRemainingTileSize(this.model.getDeckSize());

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
            this.endTurn();
        } else {
            this.view.setMessage(DominoMessage.MOVE_NOT_VALID);
            if (this.model.playerCannotMakeMove(currentPlayer) && !this.model.canDrawTile(currentPlayer)) {
                this.view.setMessage(DominoMessage.PASS_TURN);
                this.endTurn();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawTile() {
        this.model.drawTile(this.isPlayer1Turn ? this.player1 : this.player2);
        this.view.updateRemainingTileSize(this.model.getDeckSize());
        this.updatePlayersTiles();
        this.checkDraw();
        this.updateTurn();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void endGame() {
        if (this.model.isOver()) {
            this.updatePlayersTiles();
            this.view.showResult(this.model.getResult());
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
    }

    private void updatePlayersTiles() {
        this.view.setPlayerTiles(true, this.model.getPlayerTiles(this.player1));
        this.view.setPlayerTiles(false, this.model.getPlayerTiles(this.player2));
    }

    private void updateTurn() {
        this.view.setTurn(this.isPlayer1Turn);
    }

    private void endTurn() {
        this.isPlayer1Turn = !this.isPlayer1Turn;
        this.updatePlayersTiles();
        this.updateTurn();
        this.checkDraw();
        this.endGame();
    }

    private void setView(final DominoView view) {
        this.view = view;
    }
}
