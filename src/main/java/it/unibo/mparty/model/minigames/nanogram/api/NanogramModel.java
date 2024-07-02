package it.unibo.mparty.model.minigames.nanogram.api;

import java.util.List;

public interface NanogramModel {

    boolean getSolutionCellState(int x, int y);

    int getLives();

    void hitCell(int x, int y, boolean state);

    boolean isMoveValid(int x, int y, boolean state);

    void updateLives(int lives);

    List<List<Integer>> getRowHints();

    List<List<Integer>> getColumnHints();

    boolean isGameComplete();

    boolean isGameOver();

    boolean checkAndSelectCell(int x, int y, boolean state);

    int getBoardSize();
}
