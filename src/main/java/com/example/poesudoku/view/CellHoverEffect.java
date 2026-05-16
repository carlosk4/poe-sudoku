package com.example.poesudoku.view;

import javafx.scene.layout.StackPane;

public class CellHoverEffect implements Hoverable {

    private static final String HOVER_CLASS = "cell-wrapper-hover";

    @Override
    public void applyHoverEffect(StackPane wrapper) {
        wrapper.setOnMouseEntered(e -> {
            if (!wrapper.getStyleClass().contains(HOVER_CLASS)) {
                wrapper.getStyleClass().add(HOVER_CLASS);
            }
        });

        wrapper.setOnMouseExited(e -> wrapper.getStyleClass().remove(HOVER_CLASS));
    }
}