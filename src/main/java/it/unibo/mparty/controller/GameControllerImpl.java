package it.unibo.mparty.controller;

import java.io.IOException;

import java.util.Collections;
import java.util.Optional;

import it.unibo.mparty.model.GameModel;
import it.unibo.mparty.model.item.impl.ItemName;
import it.unibo.mparty.model.player.api.Player;
import it.unibo.mparty.utilities.Direction;
import it.unibo.mparty.utilities.Pair;
import it.unibo.mparty.utilities.Position;
import it.unibo.mparty.view.GameView;
import it.unibo.mparty.view.shop.api.ShopView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameControllerImpl implements GameController{

    private final GameView view;
    private GameModel model;

    public GameControllerImpl(final GameView view){
        this.view = view;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void startGame(GameModel model) throws IOException {
        this.model = model;
        List<String> usernames = this.model.getPlayers().stream().map(p -> p.getUsername()).toList();
        this.view.setUpBoard(this.model.getBoardDimension(), this.model.getBoardConfiguration(), usernames);
        
        this.view.setBoardScene();
        this.view.updateCommands(Collections.emptyList(), this.model.getMessage());
        List<Player> players = this.model.getPlayers();
        players.forEach(p -> this.view.updatePlayerStats(p.getUsername(), p.getNumCoins(), p.getNumStars(), p.getPlayerBag().getItems().stream().map(i -> i.name()).toList()));
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void useItem(ItemName item) {
        this.model.useItem(item);
        this.view.updateCommands(Collections.emptyList(), this.model.getMessage());
    }

    @Override
    public void rollDice() {
        this.view.showResultDice(this.model.rollDice());
        this.view.updateCommands(Collections.emptyList(), this.model.getMessage());
    }

    @Override
    public void movePlayer(Optional<Direction> dir) {
        this.model.movePlayer(dir);
        this.view.updateCommands(Collections.emptyList(), this.model.getMessage());
        this.model.getPlayers().forEach(p -> this.view.updatePlayerPos(new Pair<String,Position>(p.getUsername(), p.getPosition())));
    }

    @Override
    public void action() throws IOException {
        this.model.action();
        if (this.model.getActiveMinigame().isPresent()) {
           this.view.setMinigameScene(this.model.getActiveMinigame().get());
        }
        this.view.updateCommands(Collections.emptyList(), this.model.getMessage());
    }

    @Override
    public void saveMinigameResult(Pair<String, Integer> result) {
        this.model.endMinigame(result);
    }

    @Override
    public void endGame() {
        // this.view.showWinner(this.model.getWinner)
    }

    @Override
    public void setUpShop(ShopView shopView) {
        Map<ItemName,Integer> itemMap = new HashMap<>();
        this.model.getItemsFromShop().stream().forEach(it -> itemMap.put(it.getName(), it.getCost()));
        itemMap.forEach((str, i) -> shopView.addButton(str, i));
        this.model.getItemsFromShop().stream().forEach(it -> shopView.addDescription(it.getDescription()));
        //shopView.updateMoney(this.model.getPlayer());
    }

    @Override
    public void buyItem(ItemName itemName, ShopView shopView) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buyItem'");
    }
}
