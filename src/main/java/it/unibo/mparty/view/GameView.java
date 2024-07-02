package it.unibo.mparty.view;

import java.io.IOException;

import it.unibo.mparty.utilities.Pair;
import it.unibo.mparty.utilities.Position;
import it.unibo.mparty.utilities.SlotType;

import java.util.Map;
import java.util.List;


/**
 * Models the view of the application
 */
public interface GameView {

    /**
     * Sets the next scene to show
     * @param path of the scene to show
     */
    void setScene(String path) throws IOException;

    void setUpBoard(Pair<Integer,Integer> dimension, Map<Position, SlotType> board, List<String> nicknames);
}
