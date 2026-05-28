package com.example.poesudoku.model;

import static com.example.poesudoku.model.SudokuConstants.EMPTY_CELL;

/**
 * Validates cell input and applies accepted moves.
 *
 * @author Joan Lorenzo H. (carlosk4)
 * @author Abraham Y.
 * @version 1.0
 */
public class CellMoveValidator implements CellMoveValidatorInterface {

    /**
     * Validates and applies a cell text value.
     *
     * @param gameManager game state manager
     * @param row target row
     * @param col target column
     * @param textValue entered cell text
     * @return move result
     */
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

    /**
     * Parses cell text to a board value.
     *
     * @param textValue entered cell text
     * @return parsed board value
     */
    private int parseCellValue(String textValue) {
        if (textValue == null || textValue.isEmpty()) {
            return EMPTY_CELL;
        }

        return Integer.parseInt(textValue);
    }

    /**
     * Checks whether text is valid for a cell.
     *
     * @param textValue entered cell text
     * @return true when text is empty or from 1 to 6
     */
    private boolean isValidCellText(String textValue) {
        return textValue == null || textValue.isEmpty() || textValue.matches("[1-6]");
    }
}
