package com.example.poesudoku.view;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.util.Duration;

public class ButtonHoverEffect implements ButtonHoverable {

    private static final Duration HOVER_DURATION = Duration.millis(130);

    @Override
    public void applyButtonHoverEffect(Button button) {
        button.setOnMouseEntered(e -> animate(button, 1.02, -1));
        button.setOnMouseExited(e -> animate(button, 1.0, 0));
        button.setOnMousePressed(e -> animate(button, 0.98, 0));
        button.setOnMouseReleased(e -> animate(button, 1.02, -1));
    }

    private void animate(Button button, double scale, double translateY) {
        Timeline timeline = new Timeline(
                new KeyFrame(
                        HOVER_DURATION,
                        new KeyValue(button.scaleXProperty(), scale, Interpolator.EASE_OUT),
                        new KeyValue(button.scaleYProperty(), scale, Interpolator.EASE_OUT),
                        new KeyValue(button.translateYProperty(), translateY, Interpolator.EASE_OUT)
                )
        );
        timeline.play();
    }
}
