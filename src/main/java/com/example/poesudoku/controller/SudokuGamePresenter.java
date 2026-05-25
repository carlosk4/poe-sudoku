package com.example.poesudoku.controller;

import com.example.poesudoku.model.GameManagerInterface;

import static com.example.poesudoku.model.SudokuConstants.EMPTY_CELL;
import static com.example.poesudoku.model.SudokuConstants.SIZE;

public class SudokuGamePresenter {

    private final GameManagerInterface gameManager;
    private final SudokuViewRefresher viewRefresher;

    private boolean[][] invalidCells = new boolean[SIZE][SIZE];
    private int[] hintCell = new int[0];
    private String message = "";

    public SudokuGamePresenter(GameManagerInterface gameManager, SudokuViewRefresher viewRefresher) {
        this.gameManager = gameManager;
        this.viewRefresher = viewRefresher;
    }

    public void startNewGame() {
        gameManager.startNewGame();
        clearFeedback();
        message = "Nueva partida iniciada.";
        refreshView();
    }

    public void undo() {
        gameManager.undo();
        updateInvalidCells();
        hintCell = new int[0];
        message = "Movimiento deshecho.";
        refreshView();
    }

    public void restart() {
        startNewGame();
    }

    public void showHint() {
        hintCell = gameManager.getHint();

        if (hintCell.length == 0) {
            message = "No hay sugerencias disponibles.";
            refreshView();
            return;
        }

        int row = hintCell[0];
        int col = hintCell[1];
        int value = hintCell[2];

        message = "Sugerencia: coloca el número " + value
                + " en la fila " + (row + 1)
                + ", columna " + (col + 1) + ".";

        refreshView();
    }

    public void handleCellChanged(int row, int col, String textValue) {
        int value = parseCellValue(textValue);

        boolean changed = gameManager.setCellValue(row, col, value);

        if (!changed) {
            message = "No puedes modificar esta celda.";
            refreshView();
            return;
        }

        hintCell = new int[0];
        updateInvalidCells();

        if (value == EMPTY_CELL) {
            message = "Celda limpiada.";
        } else if (!gameManager.isCellValid(row, col)) {
            message = "Movimiento inválido: el número se repite en fila, columna o bloque.";
        } else if (gameManager.isSolved()) {
            message = "¡Felicitaciones! Has completado el Sudoku.";
        } else {
            message = "Movimiento válido.";
        }

        refreshView();
    }

    private void refreshView() {
        viewRefresher.refresh(
                gameManager,
                invalidCells,
                hintCell,
                message,
                this::handleCellChanged
        );
    }

    private void updateInvalidCells() {
        invalidCells = new boolean[SIZE][SIZE];

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                invalidCells[row][col] = !gameManager.isCellValid(row, col);
            }
        }
    }

    private void clearFeedback() {
        invalidCells = new boolean[SIZE][SIZE];
        hintCell = new int[0];
        message = "";
    }

    private int parseCellValue(String textValue) {
        if (textValue == null || textValue.isBlank()) {
            return EMPTY_CELL;
        }

        return Integer.parseInt(textValue);
    }
}