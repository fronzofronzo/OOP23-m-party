package it.unibo.mparty.controller.minigames.memorySweep;

import it.unibo.mparty.model.minigames.memorysweep.api.MemorySweep;
import it.unibo.mparty.model.minigames.memorysweep.impl.MemorySweepImpl;
import it.unibo.mparty.utilities.Position;
import it.unibo.mparty.view.minigames.memorySweep.MemorySweepView;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

import java.util.List;

/**
 * implementation of {@link MemorySweep}.
 */
public class MemorySweepControllerImpl implements MemorySweepController {

    private MemorySweepView view;
    private final MemorySweep model;
    private static final int SIZE = 8;
    private static final int SECONDS = 3000;
    private final PauseTransition pause = new PauseTransition(new Duration(SECONDS));

    /**
     * constructor of this.
     * @param view view that is linked to this
     */
    public MemorySweepControllerImpl(final MemorySweepView view) {
        this.setView(view);
        this.model = new MemorySweepImpl(SIZE);
        this.setRandoms();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.setRandoms();
        this.view.setUp(this.model.getRandomList());
        this.pause.setOnFinished(event -> this.view.hideRandoms(this.model.getRandomList()));
        this.pause.play();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void hit(final Position p) {
        var type = this.model.hit(p);
        if (type.equals(MemorySweep.HitType.LOSS)) {
            this.endGame();
        }
        this.view.hit(type, this.model.getTurn());
        if (type.equals(MemorySweep.HitType.LOSS)) {
            this.view.setUp(this.model.getRandomList());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void endGame() {
        if (this.model.isOver()) {
            this.view.getMainController().saveMinigameResult(this.model.getResult());
            this.view.showResult(this.model.getResult());
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initGame(final List<String> players) {
        this.model.setUpPlayers(players);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRandoms() {
        this.model.setRandomList();
    }

    private void setView(final MemorySweepView view) {
        this.view = view;
    }

}
