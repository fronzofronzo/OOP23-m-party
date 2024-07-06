package it.unibo.mparty.model.minigames.domino.api;

import java.util.LinkedList;
import java.util.List;

public interface BoardTile extends ESource<List<Tile>> {

    LinkedList<Tile> getBoardTiles();

    boolean canMatchBoardTile(Tile tile);

    void addTileToBoard(Tile tile);

    void addObserver(EObserver<? super List<Tile>> obs);

    void notifyObservers(List<Tile> tile);
}
