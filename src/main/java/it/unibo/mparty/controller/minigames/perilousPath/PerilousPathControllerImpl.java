package it.unibo.mparty.controller.minigames.perilousPath;

import it.unibo.mparty.model.minigames.perilouspath.api.AbstractPosition;
import it.unibo.mparty.model.minigames.perilouspath.api.PerilousPath;
import it.unibo.mparty.model.minigames.perilouspath.impl.PathPosition;
import it.unibo.mparty.model.minigames.perilouspath.impl.PerilousPathImpl;
import it.unibo.mparty.view.minigames.perilouspath.PerilousPathView;
import it.unibo.mparty.view.minigames.perilouspath.PerilousPathViewImpl;
import javafx.scene.control.Button;

import java.util.HashMap;
import java.util.Map;

public class PerilousPathControllerImpl implements  PerilousPathController{

    private final PerilousPath model;
    private final PerilousPathView view;
    private final static int SIZE = 8;
    private final static int SECONDS = 6000;

    public PerilousPathControllerImpl(){
        this.model = new PerilousPathImpl(SIZE);
        this.view = new PerilousPathViewImpl(SIZE);
        this.model.setBombs();
        this.model.setBalls();

    }

    @Override
    public void setUp() throws InterruptedException {
        this.view.setObserver(this);
        this.view.setUpView(this.model.getBalls(),this.model.getBombs());
        Thread.sleep(SECONDS);
        this.view.hideBombs(this.model.getBombs());
    }

    @Override
    public void hit(AbstractPosition p) {
        var type = this.model.hit(p);
        this.view.hitTile(type);
    }


}