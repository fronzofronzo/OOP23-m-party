package it.unibo.mparty.view.minigames.perilouspath;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;

@ExtendWith(ApplicationExtension.class)
class PerilousPathViewImplTest extends ApplicationTest {

    private Parent root;

    @Override
    public void start(final Stage stage) throws Exception {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/minigames/perilousPath.fxml"));
        this.root = loader.load();
        final Scene scene = new Scene(this.root);
        stage.setScene(scene);
        stage.show();
    }

    @Test
    void test(){

    }
}