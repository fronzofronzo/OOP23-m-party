package it.unibo.mparty.view;

import java.io.IOException;

import it.unibo.mparty.utilities.Pair;
import it.unibo.mparty.utilities.Position;
import it.unibo.mparty.utilities.SlotType;

import java.util.Map;
import java.util.List;


/**
 * This interface models the main View of the application. Its role
 * is to handle change between different scenes of the game and communicate
 * with the controller according to players decisions.
 */
public interface GameView {

    //void setScene(SceneType sceneType) throws IOException;
    
    /**
     * Set the board scene in the view
     */
    void setBoardScene() throws IOException;

    /**
     * Method to set the view scene with a minigame
     * @param name of the minigame to set
     */
    void setMinigameScene(String name) throws IOException;

    /**
     * Set the shop scene in the view
     */
    void setShopScene() throws IOException;
    
    /**
     * Create the board based on the input property 
     * @param dimension that defines width and height
     * @param board that defines for each slot his slot type
     * @param usernames
     */
    void setUpBoard(Pair<Integer,Integer> dimension, Map<Position, SlotType> board, List<String> usernames);

    /**
     * Show in the view the result of the dice roll
     * @param result that is the result of the dice roll
     */
    void showResultDice(int result);

    /**
     * Update in the board the stats and the position of a player
     * @param player that is the name of the player to update
     * @param coins that are the player'coins
     * @param stars that are the player'stars
     * @param items that are the player'items
     * @param position that is the player'position
     */
    void updatePlayer(String player, int coins, int stars, List<String> items, Position position);

    /**
     * Update the section of the view dedicated to the buttons' items and the message to display
     * @param items that are the items of the actual player
     * @param message that is the message to display
     */
    void updateCommands(List<String> items, String message);
}
