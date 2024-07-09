package it.unibo.mparty.controller.minigames.memorySweep;

import it.unibo.mparty.model.minigames.memorysweep.api.MemorySweep;
import it.unibo.mparty.model.minigames.memorysweep.impl.MemorySweepImpl;
import it.unibo.mparty.utilities.Position;
import it.unibo.mparty.view.minigames.memorySweep.MemorySweepView;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

import java.util.List;

public class MemorySweepControllerImpl implements MemorySweepController {

    private final MemorySweepView view;
    private final MemorySweep model;
    private final static int SIZE = 8;
    private final static int SECONDS = 3000;
    private final PauseTransition pause = new PauseTransition(new Duration(SECONDS));

    public MemorySweepControllerImpl(MemorySweepView view){
        this.view = view;
        this.model = new MemorySweepImpl(SIZE);
        this.setRandoms();
    }
    @Override
    public void setUp() {
        this.setRandoms();
        this.view.setUp(this.model.getRandomList());
        this.pause.setOnFinished(event -> this.view.hideRandoms(this.model.getRandomList()));
        this.pause.play();
    }

    @Override
    public void hit(Position p) {
        var type = this.model.hit(p);
        if(type.equals(MemorySweep.HitType.LOSS)){
            this.endGame();
        }
        this.view.hit(type,this.model.getTurn());
        if(type.equals(MemorySweep.HitType.LOSS)){
            this.view.setUp(this.model.getRandomList());
        }
    }

    @Override
    public void endGame() {
        if(this.model.isOver()){
            this.view.showResult(this.model.getResult());
        }

    }

    @Override
    public void initGame(List<String> players) {
        this.model.setUpPlayers(players);
    }

    @Override
    public void setRandoms(){
        this.model.setRandomList();
    }
}