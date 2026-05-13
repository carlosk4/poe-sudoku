package com.example.poesudoku.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import com.example.poesudoku.view.Hoverable;
import com.example.poesudoku.view.CellHoverEffect;
import com.example.poesudoku.view.ButtonHoverable;
import com.example.poesudoku.view.ButtonHoverEffect;
import javafx.scene.control.Button;

public class SudokuController {

    @FXML
    private GridPane sudokuGrid;

    private final Hoverable hoverEffect = new CellHoverEffect();
    private final ButtonHoverable buttonHoverEffect = new ButtonHoverEffect();

    @FXML
    public void initialize() {
        buildGrid();
        buttonHoverEffect.applyButtonHoverEffect(btnNewGame);
        buttonHoverEffect.applyButtonHoverEffect(btnHint);
        buttonHoverEffect.applyButtonHoverEffect(btnRestart);
    }

    private void buildGrid() {
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
                sudokuGrid.add(wrapper, col, row);
            }
        }
    }

    @FXML
    protected void onNewGame() {
        sudokuGrid.getChildren().clear();
        buildGrid();
    }

    @FXML
    protected void onHint() {

    }

    @FXML
    protected void onRestart() {
        sudokuGrid.getChildren().clear();
        buildGrid();
    }
    @FXML private Button btnNewGame;
    @FXML private Button btnHint;
    @FXML private Button btnRestart;
}