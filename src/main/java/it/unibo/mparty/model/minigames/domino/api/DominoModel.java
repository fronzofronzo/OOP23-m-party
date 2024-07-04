package it.unibo.mparty.model.minigames.domino.api;

import it.unibo.mparty.model.player.api.Player;

import java.util.List;

public interface DominoModel {

    void setPlayerTiles(Player p1, Player p2);

    boolean initializeTurn(Player p1, Player p2);

    boolean checkAndAddToBoard(Player player, Tile domino);

    boolean canDrawTile(Player player);

    void drawTile(Player player);

    Player getWinner(Player p1, Player p2);

    List<Tile> getDominoSet();

    PlayerTiles getPlayersTiles();

    BoardTile getBoardTile();
}
