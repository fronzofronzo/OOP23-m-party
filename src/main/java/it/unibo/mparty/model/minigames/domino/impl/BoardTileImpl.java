package it.unibo.mparty.model.minigames.domino.impl;

import it.unibo.mparty.model.minigames.domino.api.BoardTile;
import it.unibo.mparty.model.minigames.domino.api.Tile;

import java.util.LinkedList;

public class BoardTileImpl implements BoardTile {
    private final LinkedList<Tile> boardTiles;

    public BoardTileImpl() {
        this.boardTiles = new LinkedList<>();
    }

    @Override
    public LinkedList<Tile> getBoardTiles() {
        return boardTiles;
    }

    @Override
    public boolean canPlaceTile(final Tile tile){
        if (this.boardTiles.isEmpty()){
            this.boardTiles.add(tile);
            return true;
        }

        if (this.boardTiles.getFirst().match(tile)) {
            this.boardTiles.addFirst(tile);
            return true;
        } else if (this.boardTiles.getLast().match(tile)) {
            this.boardTiles.addLast(tile);
            return true;
        }

        return false;
    }
}