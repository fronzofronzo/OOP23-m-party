package it.unibo.mparty.model.minigames.nanogram.api;

import java.util.*;

import it.unibo.mparty.model.minigames.nanogram.util.CellState;

public interface NanogramModel {
    void initializeGameGrid(int size);

    CellState getCellState(int row, int column);

    void updateCellState(int row, int column, CellState state);

    List<List<Integer>> getRowHints();

    List<List<Integer>> getColumnHints();

    boolean isMoveValid(int row, int column, CellState state);

    boolean isGameComplete();

    int getLives();

    boolean isGameOver();
}