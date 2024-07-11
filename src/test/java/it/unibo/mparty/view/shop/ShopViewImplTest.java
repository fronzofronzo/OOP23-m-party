package it.unibo.mparty.view.shop;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;

import it.unibo.mparty.view.shop.impl.ShopViewImpl;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This class tests the {@link ShopViewImpl} class.
 */
@ExtendWith(ApplicationExtension.class)
class ShopViewImplTest extends ApplicationTest {
    /**
     * {@inheritDoc}
     */
    @Override
    public void start(final Stage stage) throws Exception {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/Shop.fxml"));
        final Parent testRoot = loader.load();
        final Scene scene = new Scene(testRoot);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Test the presence of the exitButton.
     */
    @Test
    void shouldContainExitButton() {
        verifyThat(".button", hasText("Esci"));
    }

    /**
     * Test the presence of the label in the view.
     */
    @Test
    void shouldContainLabel() {
        verifyThat(".label", hasText("Seleziona l'Item che desideri acquistare"));
    }
}
