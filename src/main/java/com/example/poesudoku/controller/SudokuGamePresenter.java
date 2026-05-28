package com.example.poesudoku.controller;

import com.example.poesudoku.model.CellMoveResult;
import com.example.poesudoku.model.CellMoveValidator;
import com.example.poesudoku.model.CellMoveValidatorInterface;
import com.example.poesudoku.model.GameManagerInterface;
import com.example.poesudoku.model.GameSessionResult;
import com.example.poesudoku.model.VictoryGrader;
import com.example.poesudoku.model.VictoryResult;

import static com.example.poesudoku.model.SudokuConstants.SIZE;

public class SudokuGamePresenter {

    private final GameManagerInterface gameManager;
    private final SudokuViewRefresher viewRefresher;
    private final CellMoveValidatorInterface cellMoveValidator = new CellMoveValidator();
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
        CellMoveResult result = cellMoveValidator.validateAndApply(gameManager, row, col, textValue);

        if (result.shouldClearHint()) {
            hintCell = new int[0];
        }

        if (result.shouldUpdateInvalidCells()) {
            updateInvalidCells();
        }

        if (result.shouldShowVictory()) {
            showVictoryScreen();
            return;
        }

        message = result.message();
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
                invalidCells[row][col] = gameManager.isCellValid(row, col);
            }
        }
    }

    private void clearFeedback() {
        invalidCells = new boolean[SIZE][SIZE];
        hintCell = new int[0];
        selectedCell = new int[0];
        message = "";
    }
}
