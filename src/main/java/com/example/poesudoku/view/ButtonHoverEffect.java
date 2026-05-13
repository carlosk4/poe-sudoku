package com.example.poesudoku.view;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.util.Duration;

public class ButtonHoverEffect implements ButtonHoverable {

    @Override
    public void applyButtonHoverEffect(Button button) {
        button.setOnMouseEntered(e -> {
            Timeline tl = new Timeline(
                    new KeyFrame(Duration.millis(150),
                            new KeyValue(button.opacityProperty(), 0.7)
                    )
            );
            tl.play();
        });

        button.setOnMouseExited(e -> {
            Timeline tl = new Timeline(
                    new KeyFrame(Duration.millis(150),
                            new KeyValue(button.opacityProperty(), 1.0)
                    )
            );
            tl.play();
        });
    }
}
