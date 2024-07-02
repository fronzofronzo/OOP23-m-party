package it.unibo.mparty.view.GameBoardView;

import it.unibo.mparty.utilities.Position;
import it.unibo.mparty.utilities.SlotType;
import it.unibo.mparty.view.SceneView;
import java.util.List;
import java.util.Map;

public interface GameBoardView extends SceneView{

    void updatePlayer(String nickname, int coins, int money, List<String> items);

    void updateCommands(List<String> items);

    void setUpBoard(int width, int height, Map<Position,SlotType> map, List<String> nicknames);
}
