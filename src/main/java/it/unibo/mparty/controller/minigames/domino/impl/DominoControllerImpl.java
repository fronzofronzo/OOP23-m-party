package it.unibo.mparty.controller.minigames.domino.impl;

import it.unibo.mparty.controller.minigames.domino.api.DominoController;
import it.unibo.mparty.model.minigames.domino.api.DominoModel;
import it.unibo.mparty.model.minigames.domino.impl.DominoModelImpl;
import it.unibo.mparty.model.player.api.Player;
import it.unibo.mparty.view.minigames.domino.api.DominoView;

public class DominoControllerImpl implements DominoController {
    private final DominoModel model;
    private final DominoView view;
    private Player p1;
    private Player p2;

    public DominoControllerImpl(final DominoView view) {
        this.model = new DominoModelImpl();
        this.view = view;
    }

    @Override
    public void setUp(){
        this.model.initDomino(p1, p2);
        this.view.setPlayerTiles(true, this.model.getPlayersTiles().getPlayerTiles(p1));
        this.view.setPlayerTiles(false, this.model.getPlayersTiles().getPlayerTiles(p2));
    }
}
