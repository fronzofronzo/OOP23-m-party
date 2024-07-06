package it.unibo.mparty.model.minigames.domino.impl;

import it.unibo.mparty.model.minigames.domino.api.BoardTile;
import it.unibo.mparty.model.minigames.domino.api.DominoModel;
import it.unibo.mparty.model.minigames.domino.api.PlayerTiles;
import it.unibo.mparty.model.minigames.domino.api.Tile;
import it.unibo.mparty.utilities.Pair;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DominoModelImpl implements DominoModel {

    private static final int DISTRIBUTION_TILES = 7;
    private static final int COINS = 10;
    private final BoardTile boardTile;
    private final PlayerTiles playerTiles;
    private final List<Tile> dominoSet;
    private String player1;
    private String player2;

    public DominoModelImpl() {
        this.boardTile = new BoardTileImpl();
        this.playerTiles = new PlayerTilesImpl();
        this.dominoSet = new TileFactoryImpl().createDoubleSixSet();
    }

    @Override
    public void setUpPlayers(List<String> players) {
        this.player1 = players.get(0);
        this.player2 = players.get(1);
        this.distribution(this.player1);
        this.distribution(this.player2);
    }

    @Override
    public boolean initializeTurn(final String p1, final String p2) {
        Random random = new Random();
        return getDoubleTiles(p1) > getDoubleTiles(p2) || random.nextBoolean();
    }

    @Override
    public boolean checkAndAddToBoard(final String player, final Tile tile) {
        if (this.boardTile.canMatchBoardTile(tile)) {
            this.boardTile.addTileToBoard(tile);
            this.playerTiles.removeTilesFromPlayer(player, tile);
            return true;
        }
        return false;
    }

    @Override
    public boolean canDrawTile(final String player) {
        return !this.playerTiles.canPlayerPlace(player, this.boardTile) && !this.dominoSet.isEmpty();
    }

    @Override
    public void drawTile(final String player) {
        Tile newTile = this.dominoSet.iterator().next();
        this.dominoSet.remove(newTile);
        this.playerTiles.addTileToPlayer(player, newTile);
    }

    @Override
    public List<Tile> getDominoSet() {
        return this.dominoSet;
    }

    @Override
    public PlayerTiles getPlayersTiles() {
        return this.playerTiles;
    }

    @Override
    public BoardTile getBoardTile() {
        return this.boardTile;
    }

    @Override
    public Pair<String, Integer> getResult() {
        Set<Tile> player1Tiles = this.playerTiles.getPlayerTiles(this.player1);
        Set<Tile> player2Tiles = this.playerTiles.getPlayerTiles(this.player2);

        if (player1Tiles.isEmpty() && !player2Tiles.isEmpty()) {
            return new Pair<>(this.player1, COINS);
        } else if (player2Tiles.isEmpty() && !player1Tiles.isEmpty()) {
            return new Pair<>(this.player2, COINS);
        } else {
            return new Pair<>(null, 0);
        }
    }

    @Override
    public boolean isOver() {
        return this.playerTiles.getPlayerTiles(this.player1).isEmpty()
                || this.playerTiles.getPlayerTiles(this.player2).isEmpty();
    }

    private int getDoubleTiles(final String player) {
        return this.playerTiles.getPlayerTiles(player).stream()
                .filter(Tile::isDoubleSide)
                .flatMapToInt(tile -> IntStream.of(tile.getSideA().getValue(), tile.getSideB().getValue()))
                .max()
                .orElse(Integer.MIN_VALUE);
    }

    private void distribution(final String player) {
        this.playerTiles.initializePlayerTiles(player,
                this.dominoSet.stream().limit(DISTRIBUTION_TILES).collect(Collectors.toSet()));
        this.dominoSet.removeAll(this.playerTiles.getPlayerTiles(player));
    }
}
