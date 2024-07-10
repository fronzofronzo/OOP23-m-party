package it.unibo.mparty.view.minigames.memoryCard;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationTest;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

import org.testfx.framework.junit5.ApplicationExtension;

import java.io.IOException;

/**
 * Test class fot {@link MemoryCardViewImpl} class. Tests that all the visual
 * elements are correct at start and after some operations.
 */
@ExtendWith(ApplicationExtension.class)
class MemoryCardViewImplTest extends ApplicationTest {

    private Parent root;

    /**
     * Method to start testing of a JavaFX implementation.
     *
     * @param stage used during testing.
     * @throws IOException if the relative fxml file is not found.
     */
    @Override
    public void start(final Stage stage) throws IOException {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/minigames/memoryCard.fxml"));
        this.root = loader.load();
        final Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Check that the initial setup of the GUI is corrected.
     */
    @Test
    public void testInitialSetup() {
        final Button button = from(this.root).lookup("#controlButton").query();
        verifyThat(button, hasText("Start !"));
    }

    /**
     * Check that after the first click on the control button, the GUI elements
     * change properly.
     *
     * @param robot - will be injected by the test runner.
     */
    @Test
    public void testStartButton(final FxRobot robot) {
        final Button button = from(this.root).lookup("#controlButton").query();
        final Label textLabel = from(this.root).lookup("#textLabel").query();
        robot.clickOn(button);
        verifyThat(button, hasText("Pronto !"));
        verifyThat(textLabel, hasText("Quando si e' pronti, spingere il pulsante 'Pronto' "));
    }

}
