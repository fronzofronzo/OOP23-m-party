package it.unibo.mparty.model.minigames.domino.impl;

import it.unibo.mparty.model.minigames.domino.api.BoardTile;
import it.unibo.mparty.model.minigames.domino.api.Tile;

import java.util.LinkedList;
import java.util.Objects;

public class BoardTileImpl implements BoardTile {

    private final LinkedList<Tile> boardTiles;

    public BoardTileImpl() {
        this.boardTiles = new LinkedList<>();
    }

    @Override
    public LinkedList<Tile> getBoardTiles() {
        return this.boardTiles;
    }

    @Override
    public boolean canMatchBoardTile(final Tile tile){
        if (this.boardTiles.isEmpty()){
            return true;
        }
        return this.boardTiles.getFirst().canMatch(tile) || this.boardTiles.getLast().canMatch(tile);
    }

    @Override
    public void addTileToBoard(final Tile tile) {
        if (this.boardTiles.isEmpty()) {
            this.boardTiles.add(tile);
            return;
        }

        if (this.boardTiles.getFirst().match(tile)) {
            //tile = new TileImpl(tile.getSideB().getValue(), tile.getSideA().getValue());
            this.boardTiles.addFirst(tile);
        } else if (this.boardTiles.getLast().match(tile)) {
            this.boardTiles.addLast(tile);
        }

    }


    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardTileImpl boardTile = (BoardTileImpl) o;
        return Objects.equals(this.boardTiles, boardTile.boardTiles);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(boardTiles);
    }
}
