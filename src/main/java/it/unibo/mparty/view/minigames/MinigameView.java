package it.unibo.mparty.view.minigames;

import it.unibo.mparty.utilities.Pair;
import it.unibo.mparty.view.SceneView;

import java.util.List;

/**
 * Interface representing the view for a minigame.
 * This interface extends the {@link SceneView} interface to include functionality specific to minigames.
 */
public interface MinigameView extends SceneView {

    /**
     * Show the result of the minigame.
     * @param result is a {@link Pair} of player who has won and how many
     *               coins he has earned.
     */
    void showResult(Pair<String, Integer> result);

    /**
     * Method to start the minigame.
     * @param players that participate to the minigame.
     */
    void startMinigame(List<String> players);

}
