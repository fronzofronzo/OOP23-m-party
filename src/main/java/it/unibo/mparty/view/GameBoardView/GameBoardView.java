package it.unibo.mparty.view.GameBoardView;

import it.unibo.mparty.utilities.Position;
import it.unibo.mparty.utilities.SlotType;
import it.unibo.mparty.view.SceneView;
import java.util.Set;
import java.util.Map;

public interface GameBoardView extends SceneView{

    void updatePlayer(String nickname, int coins, int money, Set<String> items);

    void updateCommands(Set<String> items);

    void setUpBoard(int width, int height, Map<Position,SlotType> map, Set<String> nicknames);
}
