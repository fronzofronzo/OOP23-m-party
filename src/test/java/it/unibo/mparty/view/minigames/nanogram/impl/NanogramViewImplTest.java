package it.unibo.mparty.view.minigames.nanogram.impl;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;

import static org.testfx.api.FxAssert.verifyThat;

/**
 * Test class for {@link NanogramViewImpl} class.
 */
@ExtendWith(ApplicationExtension.class)
class NanogramViewImplTest extends ApplicationTest {

    private Parent root;

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(final Stage stage) throws Exception {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/minigames/nanogram.fxml"));
        this.root = loader.load();
        final Scene scene = new Scene(this.root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Verifies that the filled button is selected as default.
     */
    @Test
    void filledButtonSelected() {
        final RadioButton button = from(this.root).lookup("#filledButton").query();
        verifyThat(button, ToggleButton::isSelected);
    }
}
