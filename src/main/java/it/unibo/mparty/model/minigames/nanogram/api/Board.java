package it.unibo.mparty.model.minigames.nanogram.api;

import it.unibo.mparty.utilities.Position;

public interface Board {

    int getSize();

    void setCellState(Position position, boolean state);

    boolean getState(Position position);
}
