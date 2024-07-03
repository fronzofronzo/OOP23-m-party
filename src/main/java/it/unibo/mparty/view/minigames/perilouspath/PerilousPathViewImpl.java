package it.unibo.mparty.view.minigames.perilouspath;

import it.unibo.mparty.controller.GameController;
import it.unibo.mparty.controller.minigames.perilousPath.PerilousPathController;
import it.unibo.mparty.controller.minigames.perilousPath.PerilousPathControllerImpl;
import it.unibo.mparty.model.minigames.perilouspath.api.AbstractPosition;
import it.unibo.mparty.model.minigames.perilouspath.api.PerilousPath;
import it.unibo.mparty.model.minigames.perilouspath.impl.PathPosition;
import it.unibo.mparty.view.AbstractSceneView;
import it.unibo.mparty.view.GameView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PerilousPathViewImpl extends AbstractSceneView implements PerilousPathView{

    @FXML
    private GridPane myGridPane;

    @FXML
    private Button startButton;

    private Button button;
    private final static int SIZE = 8;

    private final PerilousPathController observer = new PerilousPathControllerImpl(this);

    @Override
    public void setUpView(List<AbstractPosition> balls, List<AbstractPosition> bombs) {
        for(var child: this.myGridPane.getChildren()){
            var pos = this.buttonPosition(child);
            if(balls.contains(pos)){
                if (child instanceof Button) {
                    ((Button) child).setText("O");
                }
            }
            if(bombs.contains(pos)){
                if (child instanceof Button) {
                    ((Button) child).setText("X");
                }
            }
        }
    }

    @Override
    public void hideBombs(List<AbstractPosition> bombs) {
        for(var child: this.myGridPane.getChildren()){
            var pos = this.buttonPosition(child);
            if(bombs.contains(pos)){
                if (child instanceof Button) {
                    ((Button) child).setText(" ");
                }
            }
        }
    }

    @Override
    public void hitTile(PerilousPath.Type type) {
        switch(type){
            case PATH -> this.button.setText("*");
            case BOMB -> System.out.println("perso");
            case BALL -> System.out.println("vinto");
            case WRONG -> System.out.println("sbagliato");
        }
    }

    @Override
    public void buttonClicked(ActionEvent e) {
        this.button = (Button) e.getSource();
        var pos = this.buttonPosition(this.button);
        this.observer.hit(pos);
    }

    @Override
    public void handleStartButton(ActionEvent e) throws InterruptedException {
        this.observer.setUp();
        this.startButton.setDisable(true);
    }


    @Override
    public void init(GameView view, GameController controller) {
        super.init(view, controller);
    }

    @Override
    public GameController getMainController() {
        return super.getMainController();
    }

    @Override
    public GameView getMainView() {
        return super.getMainView();
    }

    private AbstractPosition buttonPosition(Node child){
        return new PathPosition(GridPane.getRowIndex(child),GridPane.getColumnIndex(child),SIZE);
    }

}