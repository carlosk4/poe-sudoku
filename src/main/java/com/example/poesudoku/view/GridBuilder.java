package com.example.poesudoku.view;

import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class GridBuilder {

    private final Hoverable hoverEffect = new CellHoverEffect();

    public void build(GridPane grid, int[][] generatedBoard, boolean[][] fixedCells) {
        grid.getChildren().clear();
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                TextField cell = createCell(generatedBoard, fixedCells, row, col);
                StackPane wrapper = createWrapper(cell, row, col);
                hoverEffect.applyHoverEffect(wrapper);
                grid.add(wrapper, col, row);
            }
        }
    }

    private TextField createCell(int[][] generatedBoard, boolean[][] fixedCells, int row, int col) {
        TextField cell = new TextField();
        cell.getStyleClass().add("cell");

        if (fixedCells[row][col]) {
            cell.setText(String.valueOf(generatedBoard[row][col]));
            cell.setEditable(false);
            cell.getStyleClass().add("cell-fixed");
        }

        return cell;
    }

    private StackPane createWrapper(TextField cell, int row, int col) {
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

        return wrapper;
    }
}