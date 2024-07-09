package it.unibo.mparty.model.minigames.secretCode.impl;

import java.util.List;

import it.unibo.mparty.model.minigames.secretCode.api.SecretCodeGame;
import it.unibo.mparty.model.minigames.secretCode.api.SecretCodeModel;
import it.unibo.mparty.utilities.Pair;

public class SecretCodeModelImpl implements SecretCodeModel{

	private final static int COINS_WINNER = 10;

	private SecretCodeGame game;;

	@Override
	public Pair<String, Integer> getResult() {
		return new Pair<String,Integer>(this.game.getWinner(), COINS_WINNER);
	}

	@Override
	public void setUpPlayers(List<String> players) {
		this.game = new SecretCodeGameImpl(players);
	}

	@Override
	public boolean isOver() {
		return this.game.isOver();
	}

	@Override
	public SecretCodeGame getGame() {
		return this.game;
	}

	@Override
	public int getTurn() {
		return this.game.getTurn();
	}
    
}
