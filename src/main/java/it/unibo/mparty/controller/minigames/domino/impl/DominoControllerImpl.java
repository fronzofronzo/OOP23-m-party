package it.unibo.mparty.controller.minigames.domino.impl;

import it.unibo.mparty.controller.minigames.domino.api.DominoController;
import it.unibo.mparty.model.minigames.domino.api.DominoModel;
import it.unibo.mparty.model.minigames.domino.impl.DominoModelImpl;
import it.unibo.mparty.model.player.api.Player;
import it.unibo.mparty.view.minigames.domino.api.DominoView;

public class DominoControllerImpl implements DominoController {
    private final DominoModel model;
    private final DominoView view;
    private Player player1;
    private Player player2;

    public DominoControllerImpl(final DominoView view, Player player1, Player player2) {
        this.model = new DominoModelImpl();
        this.view = view;
        this.player1 = player1;
        this.player2 = player2;
    }

    @Override
    public void setUp(){
        this.model.initDomino(player1, player2);
        this.view.setPlayerTiles(true, this.model.getPlayersTiles().getPlayerTiles(player1));
        this.view.setPlayerTiles(false, this.model.getPlayersTiles().getPlayerTiles(player2));
    }
}
