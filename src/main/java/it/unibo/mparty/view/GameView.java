package it.unibo.mparty.view;

import java.io.IOException;

import it.unibo.mparty.utilities.Direction;
import it.unibo.mparty.utilities.Pair;
import it.unibo.mparty.utilities.Position;
import it.unibo.mparty.utilities.SlotType;

import java.util.Map;
import java.util.Set;
import java.util.List;


/**
 * This interface models the main View of the application. Its role
 * is to handle change between different scenes of the game and communicate
 * with the controller according to players decisions.
 */
public interface GameView {

    /**
     * Sets the next scene to show
     * @param path of the scene to show
     */
    void setScene(String path) throws IOException;

    void setUpBoard(Pair<Integer,Integer> dimension, Map<Position, SlotType> board, List<String> nicknames, Position startingPosition);

    /**
     * Method to set the view scene with a minigame
     * @param name of the minigame to set
     */
    void setMinigameScene(String name) throws IOException;

    /**
     * Set the shop scene in the view
     */
    void setShopScene();

    void showResultDice(int result);

    void updatePlayerPos(Pair<String,Position> playerInfo);

    void updatePlayerStats(String player, int coins, int stars, List<String> items);

    void updateCommands(List<String> items, String message);
}
