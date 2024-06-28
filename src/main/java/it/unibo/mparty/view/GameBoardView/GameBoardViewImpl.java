package it.unibo.mparty.view.GameBoardView;

import it.unibo.mparty.model.gameBoard.util.SlotType;
import it.unibo.mparty.view.AbstractSceneView;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

public class GameBoardViewImpl extends AbstractSceneView implements GameBoardView{

    @FXML
    private GridPane board;
}
