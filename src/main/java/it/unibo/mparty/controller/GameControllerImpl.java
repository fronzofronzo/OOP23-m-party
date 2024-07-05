package it.unibo.mparty.controller;

import java.io.IOException;

import java.util.Collections;
import java.util.Optional;

import it.unibo.mparty.model.GameModel;
import it.unibo.mparty.utilities.Direction;
import it.unibo.mparty.utilities.Pair;
import it.unibo.mparty.view.GameView;

import java.util.HashMap;
import java.util.Map;

import it.unibo.mparty.model.shop.impl.ShopImpl;
import it.unibo.mparty.model.item.api.Item;
import it.unibo.mparty.model.player.api.Player;
import it.unibo.mparty.model.player.impl.PlayerImplementation;
import it.unibo.mparty.model.shop.api.Shop;
import it.unibo.mparty.view.shop.ShopView;



public class GameControllerImpl implements GameController{

    private final GameView view;
    private GameModel model;
    private final Shop shop;

    public GameControllerImpl(final GameView view){
        this.view = view;
        this.shop= new ShopImpl();
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
        this.view.updatePlayerPos(this.model.getActualPlayerInfo());
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
    public void startGame(GameModel model) {
        this.model = model;
        try {
            this.view.setScene("GameBoard");
            this.view.setUpBoard(this.model.getBoardDimensions(), this.model.getBoardConfiguration(), this.model.getPlayersNicknames(), this.model.getActualPlayerInfo().getY());
            this.view.updateCommands(Collections.emptyList(), this.model.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        Map<String,Integer> itemMap = new HashMap<>();
        shop.getItemList().stream().forEach(it -> itemMap.put(it.getName().getNametoString(), it.getCost()));
        itemMap.forEach((str, i) -> shopView.addButton(str, i));
        shop.getItemList().stream().forEach(it -> shopView.addDescription(it.getDescription()));
        shopView.updateMoney(this.model.getActualPlayerInfo().getX()); //player
    }


    @Override
    public boolean buyItem(String itemString) {
        Optional<Item> item = shop.getItemList().stream().filter(it -> itemString.equals(it.getName().getNametoString()))
        .findFirst();
        if (item.isPresent()) {
            if (shop.buyItem//(player, item.get())) {
                this.shopview.updateMoney//(player.getNumCoins());
                return true;
            }
        }
        return false;
    }

}
