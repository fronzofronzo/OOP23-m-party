package it.unibo.mparty.controller.minigames.domino.impl;

import it.unibo.mparty.controller.minigames.domino.api.DominoController;
import it.unibo.mparty.model.minigames.domino.api.DominoModel;
import it.unibo.mparty.model.minigames.domino.impl.DominoModelImpl;
import it.unibo.mparty.view.minigames.domino.api.DominoView;

public class DominoControllerImpl implements DominoController {
    private final DominoModel model;
    private final DominoView view;

    public DominoControllerImpl(DominoView view) {
        model = new DominoModelImpl();
        this.view = view;
    }
}
