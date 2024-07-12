package it.unibo.mparty.view.minigames.perilouspath;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

/**
 * test class for {@link PerilousPathView}.
 */
@ExtendWith(ApplicationExtension.class)
class PerilousPathViewImplTest extends ApplicationTest {

    /**
     * Method to start testing of a JavaFX implementation.
     *
     * @param stage used during testing.
     * @throws IOException if the relative fxml file is not found.
     */
    @Override
    public void start(final Stage stage) throws Exception {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/minigames/perilousPath.fxml"));
        final Parent root = loader.load();
        final Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * method that test if the button startButton has the right text in it.
     */
    @Test
    void testStartButton() {
        final Button startButton = (Button) lookup("#startButton").query();
        verifyThat(startButton, hasText("START"));
    }

    /**
     * method that tests the grid status before and after the click on startButton.
     */
    @Test
    void testGrid() {
        final GridPane grid = (GridPane) lookup("#myGridPane").query();
        assertFalse(grid.isDisabled());
        clickOn("#startButton");
        assertTrue(grid.isDisabled());


    }
}