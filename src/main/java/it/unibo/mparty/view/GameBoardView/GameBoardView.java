package it.unibo.mparty.view.GameBoardView;

import it.unibo.mparty.utilities.Pair;
import it.unibo.mparty.utilities.Position;
import it.unibo.mparty.utilities.SlotType;
import it.unibo.mparty.view.SceneView;
import java.util.List;
import java.util.Map;

public interface GameBoardView extends SceneView{

    void showResultDice(int result);

    void updatePlayer(String player, int coins, int stars, List<String> items, Position position);

    void updateCommands(List<String> items, String message);

    void setUpBoard(Pair<Integer,Integer> dimension, Map<Position,SlotType> board, List<String> players);
}
