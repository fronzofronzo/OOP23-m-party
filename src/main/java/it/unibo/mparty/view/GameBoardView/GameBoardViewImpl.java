package it.unibo.mparty.view.GameBoardView;

import java.util.Map;
import java.util.Set;

import it.unibo.mparty.model.gameBoard.util.SlotType;
import it.unibo.mparty.utilities.Position;
import it.unibo.mparty.view.AbstractSceneView;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

public class GameBoardViewImpl extends AbstractSceneView implements GameBoardView{

    @FXML
    private GridPane board;

    @Override
    public void updatePlayer(String nickname, int coins, int money, Set<String> items) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updatePlayer'");
    }

    @Override
    public void updateCommands(Set<String> items) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateCommands'");
    }

    @Override
    public void setUpBoard(int width, int height, Map<Position, SlotType> map, Set<String> nicknames) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setUpBoard'");
    }    
}
