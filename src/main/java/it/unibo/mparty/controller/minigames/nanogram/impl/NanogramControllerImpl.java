package it.unibo.mparty.controller.minigames.nanogram.impl;

import it.unibo.mparty.controller.minigames.nanogram.api.NanogramController;
import it.unibo.mparty.model.minigames.nanogram.impl.NanogramModelImpl;
import it.unibo.mparty.view.minigames.nanogram.impl.NanogramViewImpl;
import it.unibo.mparty.utilities.Pair;

public class NanogramControllerImpl implements NanogramController{

    private NanogramViewImpl view;
    private NanogramModelImpl model;

    public NanogramControllerImpl(NanogramViewImpl view, NanogramModelImpl model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void startGame() {

    }

    @Override
    public void getClick(Pair<Integer, Integer> click) {

    }

    @Override
    public void updateModel() {

    }

    @Override
    public void updateView() {

    }
}