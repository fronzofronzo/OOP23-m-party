package it.unibo.mparty.model.minigames.domino.api;

import java.util.LinkedList;

public interface BoardTile {
    LinkedList<Tile> getBoardTiles();

    boolean canMatchBoardTile(Tile tile);

    void addTileToBoard(Tile tile);
}
