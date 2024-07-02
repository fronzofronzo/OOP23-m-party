package it.unibo.mparty.model.minigames.nanogram.api;

import java.util.List;

public interface NanogramModel {

    boolean checkAndSelectCell(int x, int y, boolean state);

    int getLives();

    List<List<Integer>> getRowHints();

    List<List<Integer>> getColumnHints();

    int getBoardSize();

    boolean isGameComplete();

    boolean isGameOver();
}
