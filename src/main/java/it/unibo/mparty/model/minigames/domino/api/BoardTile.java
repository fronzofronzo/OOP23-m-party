package it.unibo.mparty.model.minigames.domino.api;

import it.unibo.mparty.utilities.Pair;

import java.util.LinkedList;
import java.util.List;

public interface BoardTile extends ESource<List<Pair<Integer, Integer>>> {

    LinkedList<Tile> getBoardTiles();

    boolean canMatchBoardTile(Tile tile);

    void addTileToBoard(Tile tile);

    void addObserver(EObserver<? super List<Pair<Integer, Integer>>> obs); //correggi errori ^^

    void notifyObservers(List<Pair<Integer, Integer>> tile);
}
