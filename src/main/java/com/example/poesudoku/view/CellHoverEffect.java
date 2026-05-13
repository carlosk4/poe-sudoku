package com.example.poesudoku.view;

import javafx.animation.FadeTransition;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class CellHoverEffect implements Hoverable {

    @Override
    public void applyHoverEffect(StackPane wrapper) {
        wrapper.setOnMouseEntered(e -> {
            FadeTransition ft = new FadeTransition(Duration.millis(150), wrapper);
            ft.setFromValue(1.0);
            ft.setToValue(0.85);
            ft.play();
        });

        wrapper.setOnMouseExited(e -> {
            FadeTransition ft = new FadeTransition(Duration.millis(150), wrapper);
            ft.setFromValue(0.85);
            ft.setToValue(1.0);
            ft.play();
        });
    }
}