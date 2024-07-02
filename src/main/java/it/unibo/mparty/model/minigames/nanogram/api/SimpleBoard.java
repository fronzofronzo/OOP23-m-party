package it.unibo.mparty.model.minigames.nanogram.api;

import it.unibo.mparty.utilities.Position;

import java.util.List;

public interface SimpleBoard {

    boolean getState(Position position);

    List<List<Integer>> generateHints(boolean isRow);
}
