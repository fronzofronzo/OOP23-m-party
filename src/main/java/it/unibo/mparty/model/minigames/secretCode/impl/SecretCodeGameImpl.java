package it.unibo.mparty.model.minigames.secretCode.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import it.unibo.mparty.model.minigames.secretCode.api.SecretCodeGame;
import it.unibo.mparty.model.minigames.secretCode.api.SecretCodePlayer;
import it.unibo.mparty.model.minigames.secretCode.util.SecretCodeColors;
import it.unibo.mparty.model.minigames.secretCode.util.SecretCodeResults;
import it.unibo.mparty.utilities.RandomFromSet;

public class SecretCodeGameImpl implements SecretCodeGame{

    private static final int DIM_SOLUCTION = 4;
    private static final int TURNS = 8;

    private List<SecretCodePlayer> players = new ArrayList<>();;
    private List<SecretCodeColors> soluction = new ArrayList<>();
    private int actualPlayerIndex = 0;
    private int turn = 1 ;
    private Optional<String> winner = Optional.empty();


    public SecretCodeGameImpl(List<String> players)  {
        generateSoluction();
        players.stream()
               .forEach(p -> this.players.add(new SecretCodePlayerImpl(p, this.soluction.size())));
    }

    private void generateSoluction() {
        do {
            SecretCodeColors tmp = RandomFromSet.get(Set.of(SecretCodeColors.values()));
            if (this.soluction.isEmpty() || !this.soluction.contains(tmp)) {
                this.soluction.add(tmp);
            }
        } while (this.soluction.size() < DIM_SOLUCTION);
    }

    @Override
    public List<SecretCodeResults> getGuessResult() {
        List<SecretCodeResults> res = computeResult(this.players.get(actualPlayerIndex).getGuess());
        if (!res.isEmpty()) {
            this.nextPlayer();
        }
        return res;
    } 

    private List<SecretCodeResults> computeResult(List<SecretCodeColors> guess) {
        if (guess.size() != DIM_SOLUCTION) {
            return Collections.emptyList();
        }
        List<SecretCodeResults> results = new ArrayList<>();
        for (int i = 0; i < guess.size(); i++) {
            if (this.soluction.contains(guess.get(i)) &&
                i == this.soluction.indexOf(guess.get(i))) {
                results.add(SecretCodeResults.CORRECT_COLOR_AND_POSITION);
            } else if (this.soluction.contains(guess.get(i))) {
                results.add(SecretCodeResults.CORRECT_COLOR);
            } else {
                results.add(SecretCodeResults.WRONG_COLOR);
            }
        }
        if (hasGuessed(results)) {
            this.winner = Optional.of(this.getCurrentPlayer());
        }
        return Collections.unmodifiableList(results);
    }

    private boolean hasGuessed(List<SecretCodeResults> results) {
        return !results.contains(SecretCodeResults.CORRECT_COLOR)
               && !results.contains(SecretCodeResults.WRONG_COLOR); 
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public boolean addColor(SecretCodeColors color) {
        return this.players.get(actualPlayerIndex).addColor(color);
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public String getCurrentPlayer() {
        return this.players.get(actualPlayerIndex).getNamePlayer();
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public boolean isOver() {
        return this.turn == TURNS || this.winner.isPresent();
    }

    private void nextPlayer() {
        this.actualPlayerIndex = (this.actualPlayerIndex + 1) % players.size();
        if(this.actualPlayerIndex == 0){
            this.turn++;
        }
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public String getWinner() {
        if (this.winner.isEmpty()) {
            double maxPoints = 0;
            for (int i = 0; i < this.players.size(); i++) {
                double points = this.players.get(actualPlayerIndex).getPoints() + i == 0 ? 0.5 : 0;
                if (points > maxPoints) {
                    maxPoints = points;
                    this.winner = Optional.of(this.players.get(actualPlayerIndex).getNamePlayer());
                }
            }
        }
        return this.winner.get();
    }

    /**
     *
     * {@inheritDoc}
     */
	@Override
	public List<SecretCodeColors> getCurrentGuess() {
		return this.players.get(actualPlayerIndex).getGuess();
	}

    /**
     *
     * {@inheritDoc}
     */
	@Override
	public List<SecretCodeColors> getSoluction() {
		return Collections.unmodifiableList(this.soluction);
	}
    
}
