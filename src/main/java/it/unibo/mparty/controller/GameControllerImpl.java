package it.unibo.mparty.controller;

import java.io.IOException;

import it.unibo.mparty.model.GameModel;
import it.unibo.mparty.utilities.Pair;
import it.unibo.mparty.view.GameView;

import java.util.HashMap;
import java.util.Optional;

import it.unibo.mparty.model.shop.impl.ShopImpl;
import it.unibo.mparty.model.item.api.Item;
import it.unibo.mparty.model.player.api.Player;
import it.unibo.mparty.model.player.impl.PlayerImplementation;
import it.unibo.mparty.model.shop.api.Shop;
import it.unibo.mparty.view.shop.ShopView;



public class GameControllerImpl implements GameController{

    private final GameView view;
    private GameModel model;

    public GameControllerImpl(final GameView view){
        this.view = view;
    }

    @Override
    public int rollDice() {
        return this.model.rollDice();        
    }

    @Override
    public void movePlayer() {
        this.model.movePlayer();
        // this.view.clean()
        // this.view.drawBoard()
    }

    @Override
    public void startGame(GameModel model) {
        this.model = model;
        try {
            this.view.setScene("GameBoard.fxml");
            this.drawBoard();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveMinigameResult(Pair<String, Integer> result) {
        this.model.endMinigame(result);
    }

    private void drawBoard() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
            }
        }
    }

    @Override
    public void endGame() {
        // this.view.showWinner(this.model.getWinner)
    }
    
    private Player player = new PlayerImplementation("ferro", "Mario");
    final private Shop shop = new ShopImpl();
    private ShopView shopview;
    private HashMap<String,Integer> itemMap = new HashMap<>();


    @Override
    public void setUpShop(ShopView sceneView) {
        this.shopview=sceneView;
        shop.getItemList().stream().forEach(it -> itemMap.put(it.getName().getNametoString(), it.getCost()));
        itemMap.forEach((str, i) -> shopview.addButton(str, i));
        shop.getItemList().stream().forEach(it -> this.shopview.addDescription(it.getDescription()));
        this.shopview.updateMoney(player.getNumCoins());
        player.addCoins(50);
    }


    @Override
    public boolean buyItem(String itemString) {
        Optional<Item> item = shop.getItemList().stream().filter(it -> itemString.equals(it.getName().getNametoString()))
        .findFirst();
        if (item.isPresent()) {
            if (shop.buyItem(player, item.get())) {
                this.shopview.updateMoney(player.getNumCoins());
                return true;
            }
        }
        return false;
    }

}
