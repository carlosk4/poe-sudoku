package com.example.poesudoku.view;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import static com.example.poesudoku.model.SudokuConstants.BLOCK_COLUMNS;
import static com.example.poesudoku.model.SudokuConstants.BLOCK_ROWS;
import static com.example.poesudoku.model.SudokuConstants.EMPTY_CELL;
import static com.example.poesudoku.model.SudokuConstants.SIZE;

public class GridBuilder {

    private static final Duration HINT_DURATION = Duration.millis(180);

    private final Hoverable hoverEffect = new CellHoverEffect();

    public void build(
            GridPane grid,
            int[][] generatedBoard,
            boolean[][] fixedCells,
            boolean[][] invalidCells,
            int[] hintCell,
            int[] selectedCell,
            CellChangeHandler cellChangeHandler,
            CellSelectionHandler cellSelectionHandler
    ) {
        grid.getChildren().clear();
        TextField cellToFocus = null;

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                TextField cell = createCell(
                        generatedBoard,
                        fixedCells,
                        invalidCells,
                        hintCell,
                        selectedCell,
                        row,
                        col,
                        cellChangeHandler,
                        cellSelectionHandler
                );
                StackPane wrapper = createWrapper(cell, row, col);

                if (isHintCell(hintCell, row, col)) {
                    cell.setText("");
                    Label hintLabel = createHintLabel(hintCell[2]);
                    wrapper.getChildren().add(hintLabel);
                    animateHint(hintLabel);
                }

                if (isSelectedCell(selectedCell, row, col)) {
                    cellToFocus = cell;
                }

                hoverEffect.applyHoverEffect(wrapper);
                grid.add(wrapper, col, row);
            }
        }

        if (cellToFocus != null) {
            Platform.runLater(cellToFocus::requestFocus);
        }
    }

    private TextField createCell(
            int[][] generatedBoard,
            boolean[][] fixedCells,
            boolean[][] invalidCells,
            int[] hintCell,
            int[] selectedCell,
            int row,
            int col,
            CellChangeHandler cellChangeHandler,
            CellSelectionHandler cellSelectionHandler
    ) {
        TextField cell = new TextField();
        cell.getStyleClass().add("cell");

        int value = generatedBoard[row][col];

        if (value != EMPTY_CELL) {
            cell.setText(String.valueOf(value));
        }

        if (fixedCells[row][col]) {
            cell.setEditable(false);
            cell.getStyleClass().add("cell-fixed");
        } else {
            configureEditableCell(cell, row, col, cellChangeHandler);
        }

        configureSelection(cell, row, col, cellSelectionHandler);

        if (isRelatedToSelectedCell(selectedCell, row, col)) {
            cell.getStyleClass().add("cell-related");
        }

        if (isSelectedCell(selectedCell, row, col)) {
            cell.getStyleClass().add("cell-selected");
        }

        if (invalidCells[row][col]) {
            cell.getStyleClass().add("cell-error");
        }

        if (isHintCell(hintCell, row, col)) {
            cell.getStyleClass().add("cell-hint");
        }

        return cell;
    }

    private Label createHintLabel(int hintValue) {
        Label hintLabel = new Label(String.valueOf(hintValue));
        hintLabel.getStyleClass().add("hint-marker");
        hintLabel.setMouseTransparent(true);
        StackPane.setAlignment(hintLabel, Pos.CENTER);
        return hintLabel;
    }

    private void animateHint(Label hintLabel) {
        hintLabel.setOpacity(0);
        hintLabel.setScaleX(0.82);
        hintLabel.setScaleY(0.82);

        Timeline timeline = new Timeline(
                new KeyFrame(
                        HINT_DURATION,
                        new KeyValue(hintLabel.opacityProperty(), 1, Interpolator.EASE_OUT),
                        new KeyValue(hintLabel.scaleXProperty(), 1, Interpolator.EASE_OUT),
                        new KeyValue(hintLabel.scaleYProperty(), 1, Interpolator.EASE_OUT)
                )
        );
        timeline.play();
    }

    private void configureEditableCell(TextField cell, int row, int col, CellChangeHandler cellChangeHandler) {
        cell.setStyle("-fx-display-caret: false;");
        final boolean[] restoringPreviousValue = {false};

        cell.setOnMouseDragged(Event::consume);

        cell.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.BACK_SPACE || event.getCode() == KeyCode.DELETE) {
                event.consume();
            }
        });

        cell.textProperty().addListener((observable, oldValue, newValue) -> {
            if (restoringPreviousValue[0]) {
                return;
            }

            if (!newValue.matches("[1-6]?")) {
                restoringPreviousValue[0] = true;
                cell.setText(oldValue);
                restoringPreviousValue[0] = false;
                cellChangeHandler.onCellChanged(row, col, newValue);
                return;
            }

            cellChangeHandler.onCellChanged(row, col, newValue);
        });
    }

    private void configureSelection(TextField cell, int row, int col, CellSelectionHandler cellSelectionHandler) {
        cell.setOnMouseClicked(event -> cellSelectionHandler.onCellSelected(row, col));
    }

    private boolean isSelectedCell(int[] selectedCell, int row, int col) {
        return selectedCell != null
                && selectedCell.length == 2
                && selectedCell[0] == row
                && selectedCell[1] == col;
    }

    private boolean isRelatedToSelectedCell(int[] selectedCell, int row, int col) {
        if (selectedCell == null || selectedCell.length != 2) {
            return false;
        }

        int selectedRow = selectedCell[0];
        int selectedCol = selectedCell[1];

        return selectedRow == row
                || selectedCol == col
                || isSameBlock(selectedRow, selectedCol, row, col);
    }

    private boolean isSameBlock(int selectedRow, int selectedCol, int row, int col) {
        int selectedBlockRow = selectedRow / BLOCK_ROWS;
        int selectedBlockCol = selectedCol / BLOCK_COLUMNS;
        int currentBlockRow = row / BLOCK_ROWS;
        int currentBlockCol = col / BLOCK_COLUMNS;

        return selectedBlockRow == currentBlockRow && selectedBlockCol == currentBlockCol;
    }

    private boolean isHintCell(int[] hintCell, int row, int col) {
        return hintCell != null
                && hintCell.length == 3
                && hintCell[0] == row
                && hintCell[1] == col;
    }

    private StackPane createWrapper(TextField cell, int row, int col) {
        StackPane wrapper = new StackPane(cell);
        wrapper.getStyleClass().add("cell-wrapper");

        if (row == 0) {
            wrapper.getStyleClass().add("grid-top");
        }

        if (col == 0) {
            wrapper.getStyleClass().add("grid-left");
        }

        if (row == SIZE - 1) {
            wrapper.getStyleClass().add("grid-bottom");
        }

        if (col == SIZE - 1) {
            wrapper.getStyleClass().add("grid-right");
        }

        if (col == BLOCK_COLUMNS - 1) {
            wrapper.getStyleClass().add("block-right");
        }

        if (row == BLOCK_ROWS - 1 || row == (BLOCK_ROWS * 2) - 1) {
            wrapper.getStyleClass().add("block-bottom");
        }

        return wrapper;
    }
}
