package it.unibo.mparty.view.GameBoardView;

import it.unibo.mparty.model.gameBoard.util.SlotType;
import it.unibo.mparty.view.AbstractSceneView;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class GameBoardViewImpl extends AbstractSceneView implements GameBoardView{

    @FXML
    private GridPane board;

    @Override
    public void addCell(int width, int height, SlotType slotType) {
        Label tmp = new Label();
        tmp.setText(slotType.equals(SlotType.VOID) ? "void" : "altro");
        this.board.add(tmp, width, height);
    }    
}
