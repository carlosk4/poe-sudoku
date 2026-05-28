package com.example.poesudoku.model;

/**
 * Defines validation and application of cell moves.
 *
 * @author Joan Lorenzo H. (carlosk4)
 * @author Abraham Y.
 * @version 1.0
 */
public interface CellMoveValidatorInterface {
    /**
     * Validates and applies a cell text value.
     *
     * @param gameManager game state manager
     * @param row target row
     * @param col target column
     * @param textValue entered cell text
     * @return move result
     */
    CellMoveResult validateAndApply(
            GameManagerInterface gameManager,
            int row,
            int col,
            String textValue
    );
}
