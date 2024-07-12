package it.unibo.mparty.view.minigames.domino.impl;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.util.NodeQueryUtils.hasText;

/**
 * Test class for {@link DominoViewImpl} class.
 */
@ExtendWith(ApplicationExtension.class)
class DominoViewImplTest extends ApplicationTest {

    private Parent root;

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(final Stage stage) throws Exception {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/minigames/domino.fxml"));
        this.root = loader.load();
        final Scene scene = new Scene(this.root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Verifies that the draw button has the correct text.
     */
    @Test
    void drawButtonClicked() {
        final Button button = from(this.root).lookup("#drawButton").query();
        verifyThat(button, hasText("Pesca Tessera"));
    }

    /**
     * Verifies that the play button has the correct text.
     */
    @Test
    void playButtonClicked() {
        final Button button = from(this.root).lookup("#playButton").query();
        verifyThat(button, hasText("Gioca Tessera"));
    }

    /**
     * Verifies that the tutorial button has the correct text.
     */
    @Test
    void tutorialClicked() {
        final Button button = from(this.root).lookup("#tutorialButton").query();
        verifyThat(button, hasText("Tutorial"));
    }

    /**
     * Verifies that the draw button is disabled.
     */
    @Test
    void playerCanDraw() {
        final Button button = from(this.root).lookup("#drawButton").query();
        verifyThat(button, Node::isDisabled);
    }
}