package com.example.poesudoku.view;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.Locale;

public class ButtonHoverEffect implements ButtonHoverable {

    private static final Duration HOVER_DURATION = Duration.millis(130);
    private static final Color NORMAL_BACKGROUND = Color.web("#2B2118");
    private static final Color NORMAL_TEXT = Color.web("#FFF8EF");
    private static final Color NORMAL_BORDER = Color.TRANSPARENT;
    private static final Color HOVER_BACKGROUND = NORMAL_TEXT;
    private static final Color HOVER_TEXT = NORMAL_BACKGROUND;
    private static final Color HOVER_BORDER = NORMAL_BACKGROUND;

    @Override
    public void applyButtonHoverEffect(Button button) {
        ObjectProperty<Color> background = new SimpleObjectProperty<>(NORMAL_BACKGROUND);
        ObjectProperty<Color> text = new SimpleObjectProperty<>(NORMAL_TEXT);
        ObjectProperty<Color> border = new SimpleObjectProperty<>(NORMAL_BORDER);

        background.addListener((observable, oldValue, newValue) -> applyColors(button, background.get(), text.get(), border.get()));
        text.addListener((observable, oldValue, newValue) -> applyColors(button, background.get(), text.get(), border.get()));
        border.addListener((observable, oldValue, newValue) -> applyColors(button, background.get(), text.get(), border.get()));
        applyColors(button, background.get(), text.get(), border.get());

        button.setOnMouseEntered(e -> animate(button, background, text, border, HOVER_BACKGROUND, HOVER_TEXT, HOVER_BORDER, 1.02, -1));
        button.setOnMouseExited(e -> animate(button, background, text, border, NORMAL_BACKGROUND, NORMAL_TEXT, NORMAL_BORDER, 1.0, 0));
        button.setOnMousePressed(e -> animate(button, background, text, border, HOVER_BACKGROUND, HOVER_TEXT, HOVER_BORDER, 0.98, 0));
        button.setOnMouseReleased(e -> animate(button, background, text, border, HOVER_BACKGROUND, HOVER_TEXT, HOVER_BORDER, 1.02, -1));
    }

    private void animate(
            Button button,
            ObjectProperty<Color> background,
            ObjectProperty<Color> text,
            ObjectProperty<Color> border,
            Color targetBackground,
            Color targetText,
            Color targetBorder,
            double scale,
            double translateY
    ) {
        Timeline timeline = new Timeline(
                new KeyFrame(
                        HOVER_DURATION,
                        new KeyValue(background, targetBackground, Interpolator.EASE_OUT),
                        new KeyValue(text, targetText, Interpolator.EASE_OUT),
                        new KeyValue(border, targetBorder, Interpolator.EASE_OUT),
                        new KeyValue(button.scaleXProperty(), scale, Interpolator.EASE_OUT),
                        new KeyValue(button.scaleYProperty(), scale, Interpolator.EASE_OUT),
                        new KeyValue(button.translateYProperty(), translateY, Interpolator.EASE_OUT)
                )
        );
        timeline.play();
    }

    private void applyColors(Button button, Color background, Color text, Color border) {
        button.setStyle(String.format(
                Locale.US,
                "-fx-background-color: %s; -fx-text-fill: %s; -fx-border-color: %s;",
                toCssColor(background),
                toCssColor(text),
                toCssColor(border)
        ));
    }

    private String toCssColor(Color color) {
        return String.format(
                Locale.US,
                "rgba(%d,%d,%d,%.3f)",
                Math.round(color.getRed() * 255),
                Math.round(color.getGreen() * 255),
                Math.round(color.getBlue() * 255),
                color.getOpacity()
        );
    }
}
