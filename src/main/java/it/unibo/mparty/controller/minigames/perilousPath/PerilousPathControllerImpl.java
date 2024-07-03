package it.unibo.mparty.controller.minigames.perilousPath;
import it.unibo.mparty.model.minigames.perilouspath.api.AbstractPosition;
import it.unibo.mparty.model.minigames.perilouspath.api.PerilousPath;
import it.unibo.mparty.model.minigames.perilouspath.impl.PerilousPathImpl;
import it.unibo.mparty.view.minigames.perilouspath.PerilousPathView;
import it.unibo.mparty.view.minigames.perilouspath.PerilousPathViewImpl;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

public class PerilousPathControllerImpl implements  PerilousPathController{

    private final PerilousPath model;
    private final PerilousPathView view;
    private final static int SIZE = 8;
    private final static int SECONDS = 6000;
    private final PauseTransition pause = new PauseTransition(new Duration(SECONDS));

    public PerilousPathControllerImpl(PerilousPathView view){
        this.model = new PerilousPathImpl(SIZE);
        this.view = view;
        this.model.setBombs();
        this.model.setBalls();

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
        this.view.hitTile(type);
    }

}
