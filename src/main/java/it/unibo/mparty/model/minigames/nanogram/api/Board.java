package it.unibo.mparty.model.minigames.nanogram.api;

import it.unibo.mparty.model.minigames.nanogram.util.CellState;
import it.unibo.mparty.model.minigames.nanogram.util.Pair;

import java.util.List;
import java.util.Map;

public interface Board {
    Map<Pair<Integer, Integer>, CellState> getGrid();
    Map<Pair<Integer, Integer>, CellState> getShowGrid();
    List<Integer> getHints();
    CellState getCellState(int x, int y);
}
