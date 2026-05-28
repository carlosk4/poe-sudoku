package com.example.poesudoku.controller;

import com.example.poesudoku.model.CellMoveResult;
import com.example.poesudoku.model.CellMoveValidator;
import com.example.poesudoku.model.CellMoveValidatorInterface;
import com.example.poesudoku.model.GameManagerInterface;
import com.example.poesudoku.model.GameSessionResult;
import com.example.poesudoku.model.VictoryGrader;
import com.example.poesudoku.model.VictoryResult;

import static com.example.poesudoku.model.SudokuConstants.SIZE;

/**
 * Coordinates Sudoku game state with the view layer.
 *
 * @author Joan Lorenzo H. (carlosk4)
 * @author Abraham Y.
 * @version 1.0
 */
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

    /**
     * Creates a presenter for a Sudoku game.
     *
     * @param gameManager game state manager
     * @param viewRefresher view refresh service
     * @param victoryAction victory navigation action
     */
    public SudokuGamePresenter(
            GameManagerInterface gameManager,
            SudokuViewRefresher viewRefresher,
            Runnable victoryAction
    ) {
        this.gameManager = gameManager;
        this.viewRefresher = viewRefresher;
        this.victoryAction = victoryAction;
    }

    /**
     * Starts a new Sudoku game.
     */
    public void startNewGame() {
        gameManager.startNewGame();
        clearFeedback();
        message = "Nueva partida iniciada.";
        refreshView();
    }

    /**
     * Reverts the latest game move.
     */
    public void undo() {
        gameManager.undo();
        updateInvalidCells();
        hintCell = new int[0];
        message = "Movimiento deshecho.";
        refreshView();
    }

    /**
     * Restarts the game.
     */
    public void restart() {
        startNewGame();
    }

    /**
     * Requests and displays a hint.
     */
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

    /**
     * Stores the selected board cell.
     *
     * @param row selected row
     * @param col selected column
     */
    public void handleCellSelected(int row, int col) {
        selectedCell = new int[]{row, col};
        hintCell = new int[0];
        message = "Celda seleccionada: fila " + (row + 1) + ", columna " + (col + 1) + ".";
        refreshView();
    }

    /**
     * Processes text entered into a board cell.
     *
     * @param row changed row
     * @param col changed column
     * @param textValue entered cell text
     */
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

    /**
     * Saves victory data and runs the victory action.
     */
    private void showVictoryScreen() {
        String grade = victoryGrader.calculateGrade(gameManager.getHintsUsed());
        GameSessionResult.setCurrentResult(new VictoryResult(gameManager.getHintsUsed(), grade));
        victoryAction.run();
    }

    /**
     * Refreshes the Sudoku view.
     */
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

    /**
     * Recomputes invalid cell markers.
     */
    private void updateInvalidCells() {
        invalidCells = new boolean[SIZE][SIZE];

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                invalidCells[row][col] = gameManager.isCellValid(row, col);
            }
        }
    }

    /**
     * Clears temporary feedback state.
     */
    private void clearFeedback() {
        invalidCells = new boolean[SIZE][SIZE];
        hintCell = new int[0];
        selectedCell = new int[0];
        message = "";
    }
}
