package com.example.poesudoku.model;

import static com.example.poesudoku.model.SudokuConstants.EMPTY_CELL;

public class CellMoveValidator implements CellMoveValidatorInterface {

    @Override
    public CellMoveResult validateAndApply(
            GameManagerInterface gameManager,
            int row,
            int col,
            String textValue
    ) {
        if (!isValidCellText(textValue)) {
            return new InvalidCellInputResult();
        }

        int value = parseCellValue(textValue);

        if (!gameManager.setCellValue(row, col, value)) {
            return new UnmodifiableCellResult();
        }

        if (value == EMPTY_CELL) {
            return new ClearedCellResult();
        }

        if (gameManager.isCellValid(row, col)) {
            return new InvalidCellMoveResult();
        }

        if (gameManager.isSolved()) {
            return new SolvedCellMoveResult();
        }

        return new ValidCellMoveResult();
    }

    private int parseCellValue(String textValue) {
        if (textValue == null || textValue.isEmpty()) {
            return EMPTY_CELL;
        }

        return Integer.parseInt(textValue);
    }

    private boolean isValidCellText(String textValue) {
        return textValue == null || textValue.isEmpty() || textValue.matches("[1-6]");
    }
}
