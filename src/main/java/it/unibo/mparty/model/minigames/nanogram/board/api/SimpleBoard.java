package it.unibo.mparty.model.minigames.nanogram.board.api;

import java.util.List;

public interface SimpleBoard extends Board {

    boolean getCell(int x, int y);

    List<List<Integer>> getHints(boolean isRow);
}
