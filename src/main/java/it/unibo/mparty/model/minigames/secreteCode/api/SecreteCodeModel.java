package it.unibo.mparty.model.minigames.secreteCode.api;

import it.unibo.mparty.model.minigames.MinigameModel;

public interface SecreteCodeModel extends MinigameModel{

    void guess();

    //void add

    void remove();

    void resultGuess();

     void getWinner();
    
}
