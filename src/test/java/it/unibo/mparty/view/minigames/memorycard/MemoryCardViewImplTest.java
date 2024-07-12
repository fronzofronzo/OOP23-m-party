package it.unibo.mparty.view.minigames.memorycard;

import it.unibo.mparty.view.minigames.MinigameView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationTest;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.util.WaitForAsyncUtils;

import java.io.IOException;
import java.util.List;

/**
 * Test class fot {@link MemoryCardViewImpl} class. Tests that all the visual
 * elements are correct at start and after some operations.
 */
@ExtendWith(ApplicationExtension.class)
class MemoryCardViewImplTest extends ApplicationTest {

    private Parent root;

    @BeforeAll
    static public void setup() {
        WaitForAsyncUtils.checkAllExceptions = false;
        WaitForAsyncUtils.autoCheckException = false;
    }

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
        final MinigameView controller = loader.getController();
        controller.startMinigame(List.of("user1"));
        final Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Check that the initial setup of the GUI is corrected.
     */
    @Test
    void testInitialSetup() {
        final Button button = from(this.root).lookup("#controlButton").query();
        verifyThat(button, hasText("Start !"));
    }

    /**
     * Check that the start button works properly.
     * @param robot used to test.
     */
    @Test
    void testButton(final FxRobot robot) {
        final Button button = from(this.root).lookup("#controlButton").query();
        final Label label = from(this.root).lookup("#textLabel").query();
        final FlowPane pane = from(this.root).lookup("#cardsPane").query();
        robot.clickOn(button);
        verifyThat(button, hasText("Pronto !"));
        verifyThat(label, hasText("Quando si e' pronti, spingere il pulsante 'Pronto'"));
        Assertions.assertFalse(pane.getChildren().isEmpty());
        for (final Node n : pane.getChildren()) {
            Assertions.assertTrue(n.isDisable());
        }
    }

}
