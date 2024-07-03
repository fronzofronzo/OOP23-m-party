package it.unibo.mparty.view.GameBoardView;

import it.unibo.mparty.utilities.Direction;
import it.unibo.mparty.utilities.Pair;
import it.unibo.mparty.utilities.Position;
import it.unibo.mparty.utilities.SlotType;
import it.unibo.mparty.view.SceneView;
import java.util.List;
import java.util.Map;

public interface GameBoardView extends SceneView{

    void updatePlayer(String nickname, int coins, int money, List<String> items);

    void updateCommands(List<String> items, List<Direction> directions);

    void setUpBoard(Pair<Integer,Integer> dimension, Map<Position,SlotType> map, List<String> nicknames);
}
