package com.example.poesudoku.view;

import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class GridBuilder {

    private final Hoverable hoverEffect = new CellHoverEffect();

    public void build(GridPane grid) {
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                TextField cell = new TextField();
                cell.getStyleClass().add("cell");

                StackPane wrapper = new StackPane(cell);
                wrapper.getStyleClass().add("cell-wrapper");

                boolean blockRight  = (col == 2);
                boolean blockBottom = (row == 1 || row == 3);

                if (blockRight && blockBottom) {
                    wrapper.getStyleClass().add("border-right-bottom-block");
                } else if (blockRight) {
                    wrapper.getStyleClass().add("border-right-block");
                } else if (blockBottom) {
                    wrapper.getStyleClass().add("border-bottom-block");
                }

                hoverEffect.applyHoverEffect(wrapper);
                grid.add(wrapper, col, row);
            }
        }
    }
}