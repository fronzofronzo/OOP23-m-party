package it.unibo.mparty.model.minigames.secretCode.impl;

import java.util.List;

import it.unibo.mparty.model.minigames.secretCode.api.SecretCodeModel;
import it.unibo.mparty.model.minigames.secretCode.api.SecreteCodePlayer;
import it.unibo.mparty.utilities.Pair;

public class SecretCodeModelImpl implements SecretCodeModel{
    
    private static final int TURN = 8;

    private List<SecreteCodePlayer> players;
    

    @Override
    public Pair<String, Integer> getResult() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getResult'");
    }

    @Override
    public void setUpPlayers(List<String> players) {
        for (int i = 0; i < players.size(); i++){
            //this.players.add(new Secre)
        }
    }

    @Override
    public boolean isOver() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isOver'");
    }

	@Override
	public void guess() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'guess'");
	}

	@Override
	public void addColor(SecretCodeColors color) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'addColor'");
	}

	@Override
	public void removeColor() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'removeColor'");
	}

	@Override
	public List<SecretCodeResults> resultGuess() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'resultGuess'");
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getMessage'");
	}
    
}
