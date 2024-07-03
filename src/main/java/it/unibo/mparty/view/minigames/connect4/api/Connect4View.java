package it.unibo.mparty.view.minigames.connect4.api;

import it.unibo.mparty.view.minigames.MinigameView;
import javafx.event.ActionEvent;

;

public interface Connect4View extends MinigameView{

    public void addCircle(int col, int row, boolean color);

    public void updateDisplayLabel(String msg);

    public void getColumn(ActionEvent e);

    public void activateExitButton(boolean pred);

}
