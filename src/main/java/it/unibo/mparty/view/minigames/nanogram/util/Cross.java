package it.unibo.mparty.view.minigames.nanogram.util;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Cross extends Pane {

    public Cross(Color color) {
        Line topLeftToBottomRight = new Line();
        topLeftToBottomRight.setStroke(color);

        topLeftToBottomRight.setStartX(0);
        topLeftToBottomRight.setStartY(0);

        topLeftToBottomRight.endXProperty().bind(this.widthProperty());
        topLeftToBottomRight.endYProperty().bind(this.heightProperty());


        Line topRightToBottomLeft = new Line();
        topRightToBottomLeft.setStroke(color);

        topRightToBottomLeft.startXProperty().bind(this.widthProperty());
        topRightToBottomLeft.setStartY(0);

        topRightToBottomLeft.setEndX(0);
        topRightToBottomLeft.endYProperty().bind(this.heightProperty());

        this.getChildren().add(topLeftToBottomRight);
        this.getChildren().add(topRightToBottomLeft);
    }
}
