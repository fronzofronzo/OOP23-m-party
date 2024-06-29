package it.unibo.mparty.model.minigames.domino.api;

import it.unibo.mparty.model.player.api.Player;

import java.util.Set;

public interface DominoModel {

    void initDomino(Player p1, Player p2);

    Player setTurn(Player p1, Player p2);

    boolean checkMove(Player player, Tile domino);

    Player getWinner();

    Set<Tile> getDominoSet();

    PlayerTiles getPlayerDomino();
}
