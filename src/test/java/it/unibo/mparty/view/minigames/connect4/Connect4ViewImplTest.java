package it.unibo.mparty.view.minigames.connect4;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;

/**
 * This class tests the {@link Connect4ViewImpl} class.
 */
@ExtendWith(ApplicationExtension.class)
public class Connect4ViewImplTest extends ApplicationTest {
    private Parent testRoot;

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(final Stage stage) throws Exception {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/minigames/connect4.fxml"));
        this.testRoot = loader.load();
        final Scene scene = new Scene(testRoot);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Test the tutorial button.
     */
    @Test
    public void testTutorialButton() {
        final Button button = from(this.testRoot).lookup("#tutorialButton").query();
        verifyThat(button, hasText("Tutorial"));
    }

    /**
     * Test the presence of the exit button.
     */
    @Test
    public void testExitButton() {
        final Button button = from(this.testRoot).lookup("#exitButton").query();
        verifyThat(button, hasText("Esci"));
    }
}
