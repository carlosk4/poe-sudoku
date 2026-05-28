package com.example.poesudoku.model;

public interface CellMoveValidatorInterface {
    CellMoveResult validateAndApply(
            GameManagerInterface gameManager,
            int row,
            int col,
            String textValue
    );
}
