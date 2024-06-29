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

    private PerilousPath model;
    private PerilousPathView view;
    private final static int SIZE = 8;
    private final Map<Button, AbstractPosition> map = new HashMap<>();

    @Override
    public void setUp() {
        this.model = new PerilousPathImpl(SIZE);
        this.view = new PerilousPathViewImpl();
        this.model.setBombs();
        this.model.setBalls();
    }

    @Override
    public PerilousPath.Type hit(Button button) {
        var pos = this.map.get(button);
        return this.model.hit(pos);
    }
}