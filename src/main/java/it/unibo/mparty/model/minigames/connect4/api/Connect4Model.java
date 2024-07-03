package it.unibo.mparty.model.minigames.connect4.api;

import it.unibo.mparty.model.minigames.MinigameModel;

public interface Connect4Model extends MinigameModel{
    public String getTurnPlayer();

    public boolean addDisc(int column);

    public int getAvailableRow(int column);

    public String getPlayer1 ();
}
