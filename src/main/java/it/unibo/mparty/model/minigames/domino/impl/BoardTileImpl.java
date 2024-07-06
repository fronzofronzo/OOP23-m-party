package it.unibo.mparty.model.minigames.domino.impl;

import it.unibo.mparty.model.minigames.domino.api.BoardTile;
import it.unibo.mparty.model.minigames.domino.api.EObserver;
import it.unibo.mparty.model.minigames.domino.api.Tile;

import java.util.LinkedList;
import java.util.List;
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
        } else if (this.boardTiles.getFirst().match(tile)) {
            this.boardTiles.addFirst(tile);
        } else if (this.boardTiles.getLast().match(tile)) {
            this.boardTiles.addLast(tile);
        }
        System.out.println("[BoardTileImpl] Added tile " + tile); //ok ora non si vede sul board
        this.notifyObservers(this.boardTiles);
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

    @Override
    public void addObserver(EObserver<? super List<Tile>> obs) {

    }

    @Override
    public void notifyObservers(List<Tile> tile) {

    }
}
