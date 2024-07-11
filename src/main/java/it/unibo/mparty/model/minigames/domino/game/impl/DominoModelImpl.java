package it.unibo.mparty.model.minigames.domino.game.impl;

import it.unibo.mparty.model.minigames.MinigameType;
import it.unibo.mparty.model.minigames.domino.board.api.BoardTile;
import it.unibo.mparty.model.minigames.domino.game.api.DominoModel;
import it.unibo.mparty.model.minigames.domino.player.api.PlayerTiles;
import it.unibo.mparty.model.minigames.domino.board.impl.BoardTileImpl;
import it.unibo.mparty.model.minigames.domino.player.impl.PlayerTilesImpl;
import it.unibo.mparty.model.minigames.domino.tile.api.Tile;
import it.unibo.mparty.model.minigames.domino.tile.impl.TileFactoryImpl;
import it.unibo.mparty.utilities.Pair;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Implementation of the {@link DominoModel} interface.
 */
public class DominoModelImpl implements DominoModel {

    private static final int DISTRIBUTION_TILES = 7;
    private static final int COINS = 10;
    private final BoardTile boardTile;
    private final PlayerTiles playerTiles;
    private final List<Tile> dominoSet;
    private final Random random;
    private String player1;
    private String player2;

    /**
     * Constructs a new {@link DominoModelImpl}.
     */
    public DominoModelImpl() {
        this.boardTile = new BoardTileImpl();
        this.playerTiles = new PlayerTilesImpl();
        this.dominoSet = new TileFactoryImpl().createDoubleSixSet();
        this.random = new Random();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUpPlayers(final List<String> players) {
        this.player1 = players.get(0);
        this.player2 = players.get(1);
        this.distribution(this.player1);
        this.distribution(this.player2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean initializeTurn(final String player1, final String player2) {
        return this.getDoubleTiles(player1) > this.getDoubleTiles(player2) || this.random.nextBoolean();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean checkAndAddToBoard(final String player, final Tile tile) {
        if (this.boardTile.canMatchBoardTile(tile)) {
            this.boardTile.addTileToBoard(tile);
            this.playerTiles.removeTilesFromPlayer(player, tile);
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canDrawTile(final String player) {
        return this.playerTiles.cannotPlayerPlace(player, this.boardTile) && !this.dominoSet.isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean playerCannotMakeMove(final String player) {
        return this.playerTiles.cannotPlayerPlace(player, this.boardTile) && this.dominoSet.isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawTile(final String player) {
        final Tile newTile = this.dominoSet.iterator().next();
        this.dominoSet.remove(newTile);
        this.playerTiles.addTileToPlayer(player, newTile);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Tile> getDominoSet() {
        //return Collections.unmodifiableList(this.dominoSet);
        return this.dominoSet;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PlayerTiles getAllPlayersTiles() {
        return this.playerTiles;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BoardTile getBoardTile() {
        return this.boardTile;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pair<String, Integer> getResult() {
        final Set<Tile> player1Tiles = this.playerTiles.getPlayerTiles(this.player1);
        final Set<Tile> player2Tiles = this.playerTiles.getPlayerTiles(this.player2);

        if (player1Tiles.isEmpty() && !player2Tiles.isEmpty()) {
            return new Pair<>(this.player1, COINS);
        } else if (player2Tiles.isEmpty() && !player1Tiles.isEmpty()) {
            return new Pair<>(this.player2, COINS);
        } else if (this.playerCannotMakeMove(this.player1) && this.playerCannotMakeMove(this.player2)) {
            final int player1Score = this.calculateTileScore(player1Tiles);
            final int player2Score = this.calculateTileScore(player2Tiles);
            if (player1Score == player2Score) {
                final int smallestTilePlayer1 = this.getSmallestTileValue(player1Tiles);
                final int smallestTilePlayer2 = this.getSmallestTileValue(player2Tiles);
                return smallestTilePlayer1 < smallestTilePlayer2
                        ? new Pair<>(this.player1, COINS) : new Pair<>(this.player2, COINS);
            }
            return player1Score < player2Score ? new Pair<>(this.player1, COINS) : new Pair<>(this.player2, COINS);
        } else {
            return new Pair<>(null, 0);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isOver() {
        return this.playerTiles.getPlayerTiles(this.player1).isEmpty()
                || this.playerTiles.getPlayerTiles(this.player2).isEmpty()
                || this.playerCannotMakeMove(this.player1)
                && this.playerCannotMakeMove(this.player2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return "domino";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MinigameType getType() {
        return MinigameType.MULTI_PLAYER;
    }

    private int calculateTileScore(final Set<Tile> tiles) {
        return tiles.stream().mapToInt(tile -> tile.getSideA().getValue() + tile.getSideB().getValue()).sum();
    }

    private int getSmallestTileValue(final Set<Tile> tiles) {
        return tiles.stream()
                .flatMapToInt(tile -> IntStream.of(tile.getSideA().getValue(), tile.getSideB().getValue()))
                .min()
                .orElse(Integer.MAX_VALUE);
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
