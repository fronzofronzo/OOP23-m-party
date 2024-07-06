package it.unibo.mparty.controller;

import java.io.IOException;

import it.unibo.mparty.model.GameModel;
import it.unibo.mparty.model.item.impl.ItemName;
import it.unibo.mparty.model.player.api.Player;
import it.unibo.mparty.utilities.Direction;
import it.unibo.mparty.utilities.Pair;
import it.unibo.mparty.view.GameView;
import it.unibo.mparty.view.shop.api.ShopView;

import java.util.*;

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
        List<String> usernames = this.model.getPlayers()
                                           .stream()
                                           .map(p -> p.getUsername())
                                           .toList();
        this.view.setUpBoard(this.model.getBoardDim(), this.model.getBoardConfig(), usernames);
        this.view.setBoardScene();
        this.updatePlayersView();;
        this.updateCommandView();
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void useItem(String item) {
        this.model.useItem(Arrays.stream(ItemName.values())
                .filter(i -> i.toString().equals(item))
                .findAny()
                .get());
        this.updateCommandView();
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void rollDice() {
        this.view.showResultDice(this.model.rollDice());
        this.updateCommandView();
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void movePlayer(Optional<Direction> dir) {
        this.model.movePlayer(dir);
        this.updateCommandView();
        this.updatePlayersView();
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void action() throws IOException {
        this.model.action();
        if (this.model.getActiveMinigame().isPresent()) {
           this.view.setMinigameScene(this.model.getActiveMinigame().get(), this.model.getPlayersInGame());
        } else if (this.model.isShop()) {
            this.view.setShopScene();
        }
        this.updateCommandView();
        this.updatePlayersView();
        this.checkEndGame();
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void setUpShop(ShopView shopView) {
        Map<ItemName,Integer> itemMap = new HashMap<>();
        this.model.getItemsFromShop().stream().forEach(it -> itemMap.put(it.getName(), it.getCost()));
        itemMap.forEach((str, i) -> shopView.addButton(str, i));
        this.updateCommandView();
        //shopView.updateMoney(this.model.getPlayer());
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void buyItem(ItemName itemName, ShopView shopView) {
        if (this.model.buyItem(itemName)) {
            //shopView.updateMoney(this.model.getPlayers().stream()
            //.filter(pl -> pl.getUsername().equals(this.model.get))
            //.findAny().get().getNumCoins());
        }
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void saveMinigameResult(Pair<String, Integer> result) {
        this.model.endMinigame(result);
    }

    private void checkEndGame() {
        if (this.model.isOver()) {
            //set view end game
        }
    }

    private void updatePlayersView() {
        List<Player> players = this.model.getPlayers();
        players.forEach(p -> this.view.updatePlayer(p.getUsername(), 
                                                    p.getNumCoins(), 
                                                    p.getNumStars(), 
                                                    p.getPlayerBag()
                                                     .getItems()
                                                     .stream()
                                                     .map(i -> i.name()).toList(),
                                                    p.getPosition()));
    }

    private void updateCommandView() {
        this.view.updateCommands(this.model.getItemsOfCurrentPlayer()
                                           .stream()
                                           .map(i -> i.toString())
                                           .toList(),
                                 this.model.getMessage());
    }
}
