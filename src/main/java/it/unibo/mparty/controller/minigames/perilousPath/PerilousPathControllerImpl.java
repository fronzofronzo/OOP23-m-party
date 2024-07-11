package it.unibo.mparty.controller.minigames.perilousPath;
import it.unibo.mparty.model.minigames.perilouspath.api.AbstractPosition;
import it.unibo.mparty.model.minigames.perilouspath.api.PerilousPath;
import it.unibo.mparty.model.minigames.perilouspath.impl.PerilousPathImpl;
import it.unibo.mparty.view.minigames.perilouspath.PerilousPathView;
import javafx.animation.PauseTransition;
import javafx.util.Duration;
import java.util.List;

/**
 * implementation of {@link PerilousPathController}.
 */
public class PerilousPathControllerImpl implements  PerilousPathController {

    private final PerilousPath model;
    private PerilousPathView view;
    private static final int SIZE = 8;
    private static final int SECONDS = 3000;
    private final PauseTransition pause = new PauseTransition(new Duration(SECONDS));

    /**
     * constructor of a new instance of Perilous Path Controller.
     * @param view the view that will be attached to this controller
     */
    public PerilousPathControllerImpl(final PerilousPathView view) {

        this.model = new PerilousPathImpl(SIZE);
        this.setView(view);
        this.model.setBalls();
        this.model.setBombs();
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public void setUp() throws InterruptedException {

        this.view.setUpView(this.model.getBalls(), this.model.getBombs());
        pause.setOnFinished(e -> this.view.hideBombs(this.model.getBombs()));
        pause.play();
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public void hit(final AbstractPosition p) {

        var type = this.model.hit(p);
        if (type.equals(PerilousPath.Type.BOMB) || type.equals(PerilousPath.Type.BALL)) {
            this.view.showBombs(this.model.getBombs());
            this.endGame();
        }
        this.view.hitTile(type);
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public void endGame() {
        this.view.showResult(this.model.getResult());
        this.view.getMainController().saveMinigameResult(this.model.getResult());
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public void initGame(final List<String> players) {
        this.model.setUpPlayers(players);
    }

    private void setView(final PerilousPathView view) {
        this.view = view;
    }

}
