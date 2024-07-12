package it.unibo.mparty.view.minigames.connect4;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;

import it.unibo.mparty.view.minigames.MinigameView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.List;

/**
 * This class tests the {@link Connect4ViewImpl} class.
 */
@ExtendWith(ApplicationExtension.class)
class Connect4ViewImplTest extends ApplicationTest {
    private Parent testRoot;

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(final Stage stage) throws Exception {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/minigames/connect4.fxml"));
        this.testRoot = loader.load();
        final MinigameView controller = loader.getController();
        controller.startMinigame(List.of("testPlayer1","testPlayer2"));
        final Scene scene = new Scene(testRoot);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Test the tutorial button.
     * @param robot click the button, given by TestFX
     */
    @Test
    void testTutorialButton(FxRobot robot) {
        final Button button = from(this.testRoot).lookup("#tutorialButton").query();
        verifyThat(button, hasText("Tutorial"));
        clickOn(button);
        verifyThat(button, hasText("Chiudi"));
    }

    /**
     * Test the presence of the exit button.
     */
    @Test
    void testExitButton() {
        final Button button = from(this.testRoot).lookup("#exitButton").query();
        verifyThat(button, hasText("Esci"));
    }

    /**
     * Test if the game add a circle in the correct way.
     * @param robot click the button, given by TestFX
     */
    @Test
    void testAddCircle(FxRobot robot) {
        final Button button = from(this.testRoot).lookup("3").query();
        final GridPane grid = from(this.testRoot.lookup("#gameGrid")).query();
        clickOn(button);
        final StackPane pane = (StackPane) (grid.getChildren().filtered(it -> it instanceof StackPane).get(0));
        final Circle circle = (Circle) (pane.getChildren().get(0));
        assertTrue(circle.getFill() == Color.RED);
    }
}
