package it.unibo.mparty.model.minigames.domino.api;

import it.unibo.mparty.model.minigames.MinigameModel;

import java.util.List;

public interface DominoModel extends MinigameModel {

    boolean initializeTurn(String p1, String p2);

    boolean checkAndAddToBoard(String player, Tile domino);

    boolean canDrawTile(String player);

    boolean canPlayerPlace(String player);

    void drawTile(String player);

    List<Tile> getDominoSet();

    PlayerTiles getPlayersTiles();

    BoardTile getBoardTile();
}
