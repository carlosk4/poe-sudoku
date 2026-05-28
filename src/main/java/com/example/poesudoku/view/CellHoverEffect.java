package com.example.poesudoku.view;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 * Applies animated hover effects to Sudoku cells.
 *
 * @author Joan Lorenzo H. (carlosk4)
 * @author Abraham Y.
 * @version 1.0
 */
public class CellHoverEffect implements Hoverable {

    private static final String HOVER_CLASS = "cell-wrapper-hover";
    private static final Duration HOVER_DURATION = Duration.millis(110);

    /**
     * Applies hover effects to a cell wrapper.
     *
     * @param wrapper target wrapper
     */
    @Override
    public void applyHoverEffect(StackPane wrapper) {
        wrapper.setOnMouseEntered(e -> {
            if (!wrapper.getStyleClass().contains(HOVER_CLASS)) {
                wrapper.getStyleClass().add(HOVER_CLASS);
            }
            animate(wrapper, 1.015);
        });

        wrapper.setOnMouseExited(e -> {
            wrapper.getStyleClass().remove(HOVER_CLASS);
            animate(wrapper, 1.0);
        });
    }

    /**
     * Animates wrapper scale.
     *
     * @param wrapper target wrapper
     * @param scale target scale
     */
    private void animate(StackPane wrapper, double scale) {
        Timeline timeline = new Timeline(
                new KeyFrame(
                        HOVER_DURATION,
                        new KeyValue(wrapper.scaleXProperty(), scale, Interpolator.EASE_OUT),
                        new KeyValue(wrapper.scaleYProperty(), scale, Interpolator.EASE_OUT)
                )
        );
        timeline.play();
    }
}
