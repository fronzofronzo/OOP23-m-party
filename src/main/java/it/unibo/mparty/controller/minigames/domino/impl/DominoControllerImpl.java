package it.unibo.mparty.controller.minigames.domino.impl;

import it.unibo.mparty.controller.minigames.domino.api.DominoController;
import it.unibo.mparty.model.minigames.domino.api.DominoModel;
import it.unibo.mparty.model.minigames.domino.impl.DominoModelImpl;
import it.unibo.mparty.model.player.api.Player;
import it.unibo.mparty.view.minigames.domino.api.DominoView;

public class DominoControllerImpl implements DominoController {
    private final DominoModel model;
    private final DominoView view;
    private final Player player1;
    private final Player player2;

    public DominoControllerImpl(final DominoView view, final Player player1, final Player player2) {
        this.model = new DominoModelImpl();
        this.view = view;
        this.player1 = player1;
        this.player2 = player2;
    }

    @Override
    public void setUp() {
        this.model.initDomino(this.player1, this.player2);

        this.view.setPlayerTiles(true, this.model.getPlayersTiles().getPlayerTiles(this.player1));
        this.view.setPlayerTiles(false, this.model.getPlayersTiles().getPlayerTiles(this.player2));

        this.view.setPlayerName(true, this.player1.getUsername());
        this.view.setPlayerName(false, this.player2.getUsername());

        boolean isPlayer1Turn = this.model.isPlayer1Turn(this.player1, this.player2);

        this.view.setTurn(isPlayer1Turn, isPlayer1Turn ?
                this.player1.getUsername() : this.player2.getUsername());
    }
}
