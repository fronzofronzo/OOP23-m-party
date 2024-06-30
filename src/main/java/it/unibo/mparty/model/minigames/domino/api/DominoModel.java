package it.unibo.mparty.model.minigames.domino.api;

import it.unibo.mparty.model.player.api.Player;

import java.util.Set;

public interface DominoModel {

    void initDomino(Player p1, Player p2);

    boolean isPlayer1Turn(Player p1, Player p2);

    boolean checkMove(Player player, Tile domino);

    Player getWinner(Player p1, Player p2);

    Set<Tile> getDominoSet();

    PlayerTiles getPlayerTiles();
}
