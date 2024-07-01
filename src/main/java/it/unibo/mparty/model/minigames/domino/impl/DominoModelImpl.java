package it.unibo.mparty.model.minigames.domino.impl;

import it.unibo.mparty.model.minigames.domino.api.BoardTile;
import it.unibo.mparty.model.minigames.domino.api.DominoModel;
import it.unibo.mparty.model.minigames.domino.api.PlayerTiles;
import it.unibo.mparty.model.minigames.domino.api.TileFactory;
import it.unibo.mparty.model.minigames.domino.api.Tile;
import it.unibo.mparty.model.player.api.Player;

import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DominoModelImpl implements DominoModel {

    private static final int DISTRIBUTION_TILES = 7;
    private final TileFactory dominoFactory;
    private final BoardTile boardTile;
    private final PlayerTiles playerTiles;
    private Set<Tile> dominoSet;
    private boolean actualTurn = true; //true for player1, false for player2

    public DominoModelImpl() {
        this.dominoFactory = new TileFactoryImpl();
        this.boardTile = new BoardTileImpl();
        this.playerTiles = new PlayerTilesImpl();
    }

    @Override
    public void initDomino(final Player p1, final Player p2) {
        this.dominoSet = this.dominoFactory.createDoubleSixSet();

        this.playerTiles.initializePlayerTiles(p1,
                this.dominoSet.stream().limit(DISTRIBUTION_TILES).collect(Collectors.toSet()));
        this.dominoSet.removeAll(this.playerTiles.getPlayerTiles(p1));

        this.playerTiles.initializePlayerTiles(p2,
                this.dominoSet.stream().limit(DISTRIBUTION_TILES).collect(Collectors.toSet()));
        this.dominoSet.removeAll(this.playerTiles.getPlayerTiles(p2));
    }

    @Override
    public boolean isPlayer1Turn(final Player p1, final Player p2) {
        Random random = new Random();
        if (this.boardTile.getBoardTiles().isEmpty()) {
            this.actualTurn = getDoubleTiles(p1) > getDoubleTiles(p2) || random.nextBoolean();
        }
        return this.actualTurn;
    }

    private int getDoubleTiles(final Player player) {
        return this.playerTiles.getPlayerTiles(player).stream()
                .filter(Tile::isDoubleSide)
                .flatMapToInt(tile -> IntStream.of(tile.getSideA().getValue(), tile.getSideB().getValue()))
                .max()
                .orElse(Integer.MIN_VALUE);
    }

    @Override
    public boolean checkMove(final Player player, final Tile tile) {
        if (this.boardTile.canPlaceTile(tile)) {
            this.playerTiles.removeTilesFromPlayer(player, tile);
            this.actualTurn = !this.actualTurn;
            return true;
        }
        return false;
    }

    @Override
    public boolean canDrawTile(final Player player) {
        if (!this.playerTiles.canPlayerPlace(player, this.boardTile) && !this.dominoSet.isEmpty()) {
            Tile newTile = this.dominoSet.iterator().next();
            this.dominoSet.remove(newTile);
            this.playerTiles.addTileToPlayer(player, newTile);
            return true;
        }
        return false;
    }

    @Override
    public Player getWinner(final Player p1, final Player p2) {
        return this.playerTiles.getPlayerTiles(p1).isEmpty() ? p1 :
                this.playerTiles.getPlayerTiles(p2).isEmpty() ? p2 : null;
    }

    @Override
    public Set<Tile> getDominoSet() {
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
}
