package it.unibo.mparty.controller.minigames.secretCode;

import java.io.IOException;
import java.util.List;

import it.unibo.mparty.model.minigames.secretCode.api.SecretCodeModel;
import it.unibo.mparty.model.minigames.secretCode.impl.SecretCodeModelImpl;
import it.unibo.mparty.model.minigames.secretCode.util.SecretCodeColors;
import it.unibo.mparty.model.minigames.secretCode.util.SecretCodeResults;
import it.unibo.mparty.utilities.Position;
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
            try {
                this.view.getMainView().setBoardScene();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void initGame(List<String> players) {
        this.model.setUpPlayers(players); 
    }

    @Override
    public void addColor(SecretCodeColors color) {
        if (this.model.getGame().addColor(color)) {
            Position pos = new Position(this.model.getTurn(), this.model.getGame().getCurrentGuess().size() - 1);
            this.view.updateGuesses(this.model.getGame().getCurrentPlayer(), pos, color);
        }
    }

    @Override
    public void guess() {
        List<SecretCodeResults> res = this.model.getGame().getGuessResult();
        if (!res.isEmpty()) {
            this.view.updateResults(this.model.getGame().getCurrentPlayer(), this.model.getTurn(), res);
        }
        if (this.model.isOver()) {
            this.view.showSolution(this.model.getGame().getSoluction());
        }
    }
    
}
