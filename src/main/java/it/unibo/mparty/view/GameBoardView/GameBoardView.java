package it.unibo.mparty.view.GameBoardView;

import it.unibo.mparty.utilities.Pair;
import it.unibo.mparty.utilities.Position;
import it.unibo.mparty.utilities.SlotType;
import it.unibo.mparty.view.SceneView;
import java.util.List;
import java.util.Map;

/**
 * This interface model the view of the game Secret Code based on the pattern
 * MVC and extends {@link SceneView}.
 */
public interface GameBoardView extends SceneView {

    /**
     * Create the board based on the input property.
     * 
     * @param dimension that defines width and height.
     * @param board     that defines for each slot his slot type.
     * @param usernames players.
     */
    void setUpBoard(Pair<Integer, Integer> dimension, Map<Position, SlotType> board, List<String> usernames);

    /**
     * Update in the board the stats and the position of a player.
     * 
     * @param player   that is the name of the player to update.
     * @param coins    that are the player'coins.
     * @param stars    that are the player'stars.
     * @param items    that are the player'items.
     * @param position that is the player'position.
     */
    void updatePlayer(String player, int coins, int stars, List<String> items, Position position);

    /**
     * Update the board.
     * 
     * @param boardUpdates a {@link Map} that contains the {@link Position} and the
     *                     {@link SlotType} to be updated.
     */
    void updateBoard(Map<Position, SlotType> boardUpdates);

    /**
     * Update the section of the view dedicated to the buttons' items and the
     * message to display.
     * 
     * @param items   that are the items of the actual player.
     * @param message that is the message to display.
     */
    void updateCommands(List<String> items, String message, Pair<String, String> turn);

    /**
     * Show in the view the result of the dice roll.
     * 
     * @param result that is the result of the dice roll.
     */
    void showResultDice(int result);
}
