package it.unibo.mparty.view.GameBoardView;

import it.unibo.mparty.model.gameBoard.util.SlotType;
import it.unibo.mparty.view.SceneView;

public interface GameBoardView extends SceneView{


    public void addCell(int width, int height, SlotType slotType);
}
