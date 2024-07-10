package it.unibo.mparty.view.minigames;

import it.unibo.mparty.utilities.Pair;
import it.unibo.mparty.view.SceneView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;

/**
 * Interface representing the view for a minigame.
 * This interface extends the {@link SceneView} interface to include functionality specific to minigames.
 */
public interface MinigameView extends SceneView {

    /**
     * Show the result of the minigame
     * @param result is a {@link Pair} of player who has won and how many
     *               coins he has earned
     */
    void showResult(Pair<String,Integer> result);

    /**
     * Method to start the minigame
     * @param players that partecipate to the minigame
     */
    void startMinigame(List<String> players);

    /**
     * Utility method to get tutorial for a mini-game directly from file.
     * @param path - relative path of file.
     * @return String containing the tutorial read by file.
     */
    static String getTutorialFromFile(final String path) {
        final StringBuilder text = new StringBuilder();
        final BufferedReader input = new BufferedReader(
                new InputStreamReader(Objects.requireNonNull(MinigameView.class.getResourceAsStream(path))));
        try {
            String line;
            while ((line = input.readLine()) != null) {
                text.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return text.toString();
    };
}
