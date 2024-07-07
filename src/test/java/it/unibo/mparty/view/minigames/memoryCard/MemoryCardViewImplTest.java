package it.unibo.mparty.view.minigames.memoryCard;

import static org.junit.jupiter.api.Assertions.*;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

import java.io.IOException;


class MemoryCardViewImplTest extends  ApplicationTest{

    private Parent root;

    @Override
    public void start(Stage stage) throws IOException {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/minigames/memoryCard.fxml"));
        this.root = loader.load();
        final Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void testInitialSetup(){
        final Button button = from(this.root).lookup("#controlButton").query();
        verifyThat(button, hasText("Start !"));
    }

}