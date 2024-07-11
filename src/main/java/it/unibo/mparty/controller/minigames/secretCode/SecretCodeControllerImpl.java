package it.unibo.mparty.controller.minigames.secretCode;

import java.io.IOException;
import java.util.List;

import it.unibo.mparty.model.minigames.secretCode.api.SecretCodeModel;
import it.unibo.mparty.model.minigames.secretCode.impl.SecretCodeModelImpl;
import it.unibo.mparty.model.minigames.secretCode.util.SecretCodeColors;
import it.unibo.mparty.model.minigames.secretCode.util.SecretCodeResults;
import it.unibo.mparty.utilities.Pair;
import it.unibo.mparty.view.minigames.secretCode.SecretCodeView;

/**
 * This class implements {@link SecretCodeController}.
 */
public class SecretCodeControllerImpl implements SecretCodeController {

    private final SecretCodeModel model;
    private final SecretCodeView view;

    /**
     * This is the constructor of this class that. It set the reference to the model
     * and the view of the game.
     * 
     * @param view the view of the game.
     */
    public SecretCodeControllerImpl(final SecretCodeView view) {
        this.model = new SecretCodeModelImpl();
        this.view = view;
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void initGame(final List<String> players) {
        this.model.setUpPlayers(players);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addColor(final SecretCodeColors color) {
        if (!this.model.isOver() && this.model.addColor(color)) {
            Pair<Integer, Integer> pos = new Pair<>(this.model.getCurrentGuess().size() - 1, this.model.getTurn());
            this.view.updateGuess(this.model.getCurrentPlayer(), pos, color);
        }
        this.updateEndaGame();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void guess() {
        if (!this.model.isOver()) {
            String player = this.model.getCurrentPlayer();
            int turn = this.model.getTurn();
            List<SecretCodeResults> res = this.model.getGuessResult();
            if (!res.isEmpty()) {
                this.view.updateResults(player, turn, res);
            }
        }
        this.updateEndaGame();
    }

    private void updateEndaGame() {
        if (this.model.isOver()) {
            this.view.showSolution(this.model.getSoluction());
            this.view.showResult(new Pair<String, Integer>(this.model.getWinner(), 10));
        }
    }
}
