package com.example.poesudoku.controller;

import com.example.poesudoku.model.GameManagerInterface;
import com.example.poesudoku.model.GameSessionResult;
import com.example.poesudoku.model.VictoryGrader;
import com.example.poesudoku.model.VictoryResult;

import static com.example.poesudoku.model.SudokuConstants.EMPTY_CELL;
import static com.example.poesudoku.model.SudokuConstants.SIZE;

public class SudokuGamePresenter {

    private final GameManagerInterface gameManager;
    private final SudokuViewRefresher viewRefresher;
    private final VictoryGrader victoryGrader = new VictoryGrader();
    private final Runnable victoryAction;

    private boolean[][] invalidCells = new boolean[SIZE][SIZE];
    private int[] hintCell = new int[0];
    private int[] selectedCell = new int[0];
    private String message = "";

    public SudokuGamePresenter(
            GameManagerInterface gameManager,
            SudokuViewRefresher viewRefresher,
            Runnable victoryAction
    ) {
        this.gameManager = gameManager;
        this.viewRefresher = viewRefresher;
        this.victoryAction = victoryAction;
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

        gameManager.registerHintUsed();

        int row = hintCell[0];
        int col = hintCell[1];
        int value = hintCell[2];

        selectedCell = new int[]{row, col};

        message = "Sugerencia en la fila " + (row + 1)
                + ", columna " + (col + 1) + ".";

        refreshView();
    }

    public void handleCellSelected(int row, int col) {
        selectedCell = new int[]{row, col};
        hintCell = new int[0];
        message = "Celda seleccionada: fila " + (row + 1) + ", columna " + (col + 1) + ".";
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
            refreshView();
            return;
        }

        if (!gameManager.isCellValid(row, col)) {
            message = "Movimiento inválido: el número se repite en fila, columna o bloque.";
            refreshView();
            return;
        }

        if (gameManager.isSolved()) {
            showVictoryScreen();
            return;
        }

        message = "Movimiento válido.";
        refreshView();
    }

    private void showVictoryScreen() {
        String grade = victoryGrader.calculateGrade(gameManager.getHintsUsed());
        GameSessionResult.setCurrentResult(new VictoryResult(gameManager.getHintsUsed(), grade));
        victoryAction.run();
    }

    private void refreshView() {
        viewRefresher.refresh(
                gameManager,
                invalidCells,
                hintCell,
                selectedCell,
                message,
                this::handleCellChanged,
                this::handleCellSelected
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
        selectedCell = new int[0];
        message = "";
    }

    private int parseCellValue(String textValue) {
        if (textValue == null || textValue.isBlank()) {
            return EMPTY_CELL;
        }

        return Integer.parseInt(textValue);
    }
}