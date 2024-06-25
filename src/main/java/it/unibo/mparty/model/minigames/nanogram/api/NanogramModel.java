package it.unibo.mparty.model.minigames.nanogram.api;

import java.util.*;

import it.unibo.mparty.model.minigames.nanogram.util.CellState;
import it.unibo.mparty.model.minigames.nanogram.util.Difficulty;

public interface NanogramModel{
    void initializeGameGrid(Difficulty difficulty);

    CellState getCellState(int row, int column);

    void updateCellState(int row, int column, CellState state);

    List<List<Integer>> getRowHints();

    List<List<Integer>> getColumnHints();

    boolean isMoveValid();

    boolean isGameComplete();

    boolean isGameOver();
}