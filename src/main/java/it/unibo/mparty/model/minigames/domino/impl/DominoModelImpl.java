package it.unibo.mparty.model.minigames.domino.impl;

import it.unibo.mparty.model.minigames.domino.api.*;
import it.unibo.mparty.model.player.api.Player;

import java.util.*;
import java.util.stream.Collectors;

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

        //todo: distribuzione, ogni player ne ha 7, una volta distribuita le tessere possedute dal player viene tolto dal set
        playerTiles.addTilesToPlayer(p1, this.dominoSet.stream().limit(7).collect(Collectors.toSet()));
        dominoSet.removeAll(this.playerTiles.getPlayerTiles(p1));
        playerTiles.addTilesToPlayer(p2, this.dominoSet.stream().limit(7).collect(Collectors.toSet()));
        dominoSet.removeAll(this.playerTiles.getPlayerTiles(p2));
    }

    @Override
    public void setTurn() {
        //todo: inizia quello con il doppio piu alto, se no a random

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
