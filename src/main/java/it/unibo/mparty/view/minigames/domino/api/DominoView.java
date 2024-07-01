package it.unibo.mparty.view.minigames.domino.api;

import it.unibo.mparty.model.minigames.domino.api.Tile;
import it.unibo.mparty.view.SceneView;
import it.unibo.mparty.view.minigames.domino.DominoMessage;

import java.util.LinkedList;
import java.util.Set;

public interface DominoView extends SceneView {
    void setPlayerTiles(boolean isPlayer1, Set<Tile> playerTiles);

    void setPlayerName(boolean isPlayer1, String playerName);

    void setTurn(boolean isPlayer1Turn, String playerName);

    void setBoard(LinkedList<Tile> board);

    void setMessage(DominoMessage message);
}
