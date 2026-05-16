package com.example.poesudoku.controller;

import com.example.poesudoku.model.GameManagerInterface;

import static com.example.poesudoku.model.SudokuConstants.EMPTY_CELL;

public class SudokuGamePresenter {

    private final GameManagerInterface gameManager;
    private final SudokuViewRefresher viewRefresher;

    public SudokuGamePresenter(GameManagerInterface gameManager, SudokuViewRefresher viewRefresher) {
        this.gameManager = gameManager;
        this.viewRefresher = viewRefresher;
    }

    public void startNewGame() {
        gameManager.startNewGame();
        refreshView();
    }

    public void undo() {
        gameManager.undo();
        refreshView();
    }

    public void restart() {
        startNewGame();
    }

    public void handleCellChanged(int row, int col, String textValue) {
        int value = parseCellValue(textValue);

        boolean changed = gameManager.setCellValue(row, col, value);

        if (!changed) {
            refreshView();
            return;
        }

        viewRefresher.refreshActions(gameManager);
    }

    private void refreshView() {
        viewRefresher.refresh(gameManager, this::handleCellChanged);
    }

    private int parseCellValue(String textValue) {
        if (textValue == null || textValue.isBlank()) {
            return EMPTY_CELL;
        }

        return Integer.parseInt(textValue);
    }
}