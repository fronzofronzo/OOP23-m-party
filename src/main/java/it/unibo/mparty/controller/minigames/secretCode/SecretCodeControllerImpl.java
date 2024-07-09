package it.unibo.mparty.controller.minigames.secretCode;

import java.util.List;

import it.unibo.mparty.model.minigames.secretCode.api.SecretCodeModel;
import it.unibo.mparty.model.minigames.secretCode.impl.SecretCodeModelImpl;
import it.unibo.mparty.model.minigames.secretCode.util.SecretCodeColors;
import it.unibo.mparty.model.minigames.secretCode.util.SecretCodeResults;
import it.unibo.mparty.view.minigames.secretCode.SecretCodeView;

public class SecretCodeControllerImpl implements SecretCodeController{
    private SecretCodeModel model;
    private SecretCodeView view;

    public SecretCodeControllerImpl(SecretCodeView view) {
        this.model = new SecretCodeModelImpl();
        this.view = view;
    }

    @Override
    public void endGame() {
        if (this.model.isOver()) {
            this.view.getMainController().saveMinigameResult(this.model.getResult());
        }
    }

    @Override
    public void initGame(List<String> players) {
        this.model.setUpPlayers(players); 
    }

    @Override
    public void addColor(SecretCodeColors color) {
        this.model.getGame().addColor(color);
    }

    @Override
    public void guess() {
        List<SecretCodeResults> res = this.model.getGame().getGuessResult();
        if (!res.isEmpty()) {
            this.view.updateResults(this.model.getGame().getCurrentPlayer(), this.model.getTurn(), res);
        }
    }
    
}
