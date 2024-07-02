package it.unibo.mparty.view.minigames.memorySweep;

import it.unibo.mparty.controller.minigames.memorySweep.MemorySweepController;
import it.unibo.mparty.model.minigames.memorysweep.api.MemorySweep;
import it.unibo.mparty.utilities.Position;
import javafx.event.ActionEvent;

import java.util.Set;

public interface MemorySweepView {

    public void setUp(Set<Position> randoms);

    public void hit(MemorySweep.HitType type);

    public void buttonClicked(ActionEvent e);

    public void setObserver(MemorySweepController controller);
}