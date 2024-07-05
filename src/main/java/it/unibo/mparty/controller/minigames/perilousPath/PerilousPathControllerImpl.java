package it.unibo.mparty.controller.minigames.perilousPath;
import it.unibo.mparty.model.minigames.perilouspath.api.AbstractPosition;
import it.unibo.mparty.model.minigames.perilouspath.api.PerilousPath;
import it.unibo.mparty.model.minigames.perilouspath.impl.PerilousPathImpl;
import it.unibo.mparty.view.minigames.perilouspath.PerilousPathView;
import it.unibo.mparty.view.minigames.perilouspath.PerilousPathViewImpl;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

import java.util.List;

public class PerilousPathControllerImpl implements  PerilousPathController{

    private final PerilousPath model;
    private final PerilousPathView view;
    private final static int SIZE = 8;
    private final static int SECONDS = 3000;
    private final PauseTransition pause = new PauseTransition(new Duration(SECONDS));

    public PerilousPathControllerImpl(PerilousPathView view){

        this.model = new PerilousPathImpl(SIZE);
        this.view = view;
        this.model.setBalls();
        this.model.setBombs();

    }

    @Override
    public void setUp() throws InterruptedException {

        this.view.setUpView(this.model.getBalls(),this.model.getBombs());
        pause.setOnFinished(e -> this.view.hideBombs(this.model.getBombs()));
        pause.play();
    }

    @Override
    public void hit(AbstractPosition p) {

        var type = this.model.hit(p);
        if(type.equals(PerilousPath.Type.BOMB) || type.equals(PerilousPath.Type.BALL)){
            this.view.showBombs(this.model.getBombs());
        }
        this.view.hitTile(type);
        this.endGame();
    }

    @Override
    public void endGame() {
        this.view.showResult(this.model.getResult());
    }

    @Override
    public void initGame(List<String> players) {
        this.model.setUpPlayers(players);
    }
}
