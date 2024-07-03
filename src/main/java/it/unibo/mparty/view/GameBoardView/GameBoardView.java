package it.unibo.mparty.view.GameBoardView;

import it.unibo.mparty.utilities.Direction;
import it.unibo.mparty.utilities.Pair;
import it.unibo.mparty.utilities.Position;
import it.unibo.mparty.utilities.SlotType;
import it.unibo.mparty.view.SceneView;
import java.util.List;
import java.util.Map;

public interface GameBoardView extends SceneView{

    void showResultDice(int result);

    void updatePlayerPos(String player, Position position);

    void updatePlayerStats(String player, int coins, int stars, List<String> items);

    void updateCommands(List<String> items, List<Direction> directions);

    void setUpBoard(Pair<Integer,Integer> dimension, Map<Position,SlotType> board, List<String> players);
}
