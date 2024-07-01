package it.unibo.mparty.view.GameBoardView;

import java.util.Map;
import java.util.Set;
import java.util.Objects;

import it.unibo.mparty.model.gameBoard.util.SlotType;
import it.unibo.mparty.utilities.Position;
import it.unibo.mparty.view.AbstractSceneView;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class GameBoardViewImpl extends AbstractSceneView implements GameBoardView{

    private final static int N_VBOX = 4;

    private GridPane board;
    @FXML
    private Pane paneCommand;
    @FXML 
    private VBox sectionP1;
    @FXML 
    private VBox sectionP2;
    @FXML 
    private VBox sectionP3;
    @FXML 
    private VBox sectionP4; 

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
        this.board = new GridPane();
        this.populateGridPane(width, height, map);
    }

    private void populateGridPane(int width, int height, Map<Position, SlotType> map) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Label tmp = new Label(Objects.isNull(map.get(new Position(i, j))) ? "void" : map.get(new Position(i, j)).toString());
                this.board.add(tmp, i, j);
            }
        }
    }    
}
