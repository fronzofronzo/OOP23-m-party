package it.unibo.mparty.model.minigames.domino.impl;

import it.unibo.mparty.model.minigames.domino.api.BoardTile;
import it.unibo.mparty.model.minigames.domino.api.Tile;

import java.util.LinkedList;

public class BoardTileImpl implements BoardTile {
    private final LinkedList<Tile> boardTiles;
    private int leftExtrem;
    private int rightExtrem;

    public BoardTileImpl() {
        this.boardTiles = new LinkedList<>();
    }

    @Override
    public LinkedList<Tile> getBoardTiles() {
        return boardTiles;
    }

    @Override
    public boolean canPlaceTile(Tile tile){
        if (this.boardTiles.isEmpty()){
            this.boardTiles.add(tile);
            this.leftExtrem = this.boardTiles.getFirst().getSideA();
            this.rightExtrem = this.boardTiles.getFirst().getSideB();
            return true;
        }

        if (tile.canMatchLeft(leftExtrem)){
            leftExtrem = tile.getSideA();
            this.boardTiles.addFirst(tile);
            return true;
        } else if (tile.canMatchRight(rightExtrem)){
            rightExtrem = tile.getSideB();
            this.boardTiles.addLast(tile);
            return true;
        }

        return false;
    }
}
