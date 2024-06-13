package it.unibo.mparty.model.minigames.nanogram.api;

import java.util.*;

public interface NanogramModel {
    void initializeGameGrid(int size);

    String getCellState(int row, int column);

    void setCellState(int row, int column, String state);

    List<String> getRowHints();

    List<String> getColumnHints();

    boolean isMoveValid(int row, int column, String move);

    boolean isGameComplete();
}