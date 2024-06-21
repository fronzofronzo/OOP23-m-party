package it.unibo.mparty.controller.minigames.nanogram.impl;

import it.unibo.mparty.controller.minigames.nanogram.api.NanogramController;
import it.unibo.mparty.model.minigames.nanogram.impl.NanogramModelImpl;
import it.unibo.mparty.view.minigames.nanogram.impl.NanogramViewImpl;
import it.unibo.mparty.model.minigames.nanogram.util.Pair;

public class NanogramControllerImpl implements NanogramController{

    private NanogramViewImpl view;
    private NanogramModelImpl model;

    public NanogramControllerImpl(NanogramViewImpl view, NanogramModelImpl model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void startGame() {
        model.initializeGameGrid(5);
    }

    @Override
    public void getClick(Pair<Integer, Integer> click) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getClick'");
    }

    @Override
    public void updateModel() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateModel'");
    }

    @Override
    public void updateView() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateView'");
    }
    
}