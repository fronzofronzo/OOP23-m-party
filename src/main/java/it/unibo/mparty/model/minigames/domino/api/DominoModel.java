package it.unibo.mparty.model.minigames.domino.api;

import it.unibo.mparty.model.player.api.Player;

import java.util.List;

public interface DominoModel {

    void setPlayerTiles(Player p1, Player p2);

    boolean isPlayer1Turn(Player p1, Player p2);

    boolean checkMove(Player player, Tile domino);

    boolean canDrawTile(Player player);

    Player getWinner(Player p1, Player p2);

    List<Tile> getDominoSet();

    PlayerTiles getPlayersTiles();

    BoardTile getBoardTile();
}
