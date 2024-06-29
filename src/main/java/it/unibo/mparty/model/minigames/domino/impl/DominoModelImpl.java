package it.unibo.mparty.model.minigames.domino.impl;

import it.unibo.mparty.model.minigames.domino.api.*;
import it.unibo.mparty.model.player.api.Player;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DominoModelImpl implements DominoModel {

    private final TileFactory dominoFactory;
    private final BoardTile boardTile;
    private final PlayerTiles playerTiles;
    private Set<Tile> dominoSet;

    public DominoModelImpl() {
        this.dominoFactory = new TileFactoryImpl();
        this.boardTile = new BoardTileImpl();
        this.playerTiles = new PlayerTilesImpl();
    }

    @Override
    public void initDomino(final Player p1, final Player p2) {
        this.dominoSet = this.dominoFactory.createFullSet();

        playerTiles.addTilesToPlayer(p1, this.dominoSet.stream().limit(7).collect(Collectors.toSet()));
        dominoSet.removeAll(this.playerTiles.getPlayerTiles(p1));
        playerTiles.addTilesToPlayer(p2, this.dominoSet.stream().limit(7).collect(Collectors.toSet()));
        dominoSet.removeAll(this.playerTiles.getPlayerTiles(p2));
    }

    @Override
    public Player setTurn(final Player p1, final Player p2) {
        if (this.boardTile.getBoardTiles().isEmpty()) {
            return (getDoubleTiles(p1) > getDoubleTiles(p2)) ? p1 : p2;
        }
        Random random = new Random();
        return random.nextBoolean()? p1 : p2;
    }

    private int getDoubleTiles(Player player) {
        int max = playerTiles.getPlayerTiles(player).stream()
                .filter(Tile::isDoubleSide)
                .flatMapToInt(tile -> IntStream.of(tile.getSideA(), tile.getSideB()))
                .max()
                .orElse(Integer.MIN_VALUE);
        System.out.println(max);
        return playerTiles.getPlayerTiles(player).stream()
                .filter(Tile::isDoubleSide)
                .flatMapToInt(tile -> IntStream.of(tile.getSideA(), tile.getSideB()))
                .max()
                .orElse(Integer.MIN_VALUE);
    }

    @Override
    public boolean checkMove(final Player player, final Tile domino) {
        return false;
    }

    @Override
    public Player getWinner() {
        return null;
    }

    @Override
    public Set<Tile> getDominoSet() {
        return dominoSet;
    }

    @Override
    public PlayerTiles getPlayerDomino() {
        return this.playerTiles;
    }
}
