package it.unibo.mparty.model.minigames.domino.impl;

import it.unibo.mparty.model.minigames.domino.api.BoardTile;
import it.unibo.mparty.model.minigames.domino.api.Tile;
import it.unibo.mparty.utilities.Pair;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class BoardTileImpl extends ESourceImpl<List<Pair<Integer, Integer>>> implements BoardTile {

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
        } else if (this.boardTiles.size() == 1) {
            var firstTile = this.boardTiles.getFirst();
            firstTile.match(tile);
            if (tile.getSideA().isMatched()){
                this.boardTiles.addLast(tile);
            } else if (tile.getSideB().isMatched()){
                this.boardTiles.addFirst(tile);
            }
        } else if (this.boardTiles.getFirst().match(tile) ) {
            this.boardTiles.addFirst(tile);
        } else if (this.boardTiles.getLast().match(tile)) {
            this.boardTiles.addLast(tile);
        }
        this.notifyObservers(this.boardTiles.stream().map(t->new Pair<>(t.getSideA().getValue(), t.getSideB().getValue())).toList());
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardTileImpl boardTile = (BoardTileImpl) o;
        return Objects.equals(boardTiles, boardTile.boardTiles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(boardTiles);
    }

    @Override
    public String toString() {
        return "BoardTileImpl{" +
                "boardTiles=" + boardTiles +
                '}';
    }
}
