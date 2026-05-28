package com.example.poesudoku.model;

/**
 * Represents the outcome of a cell move.
 *
 * @author Joan Lorenzo H. (carlosk4)
 * @author Abraham Y.
 * @version 1.0
 */
public interface CellMoveResult {
    /**
     * Indicates whether the current hint should be cleared.
     *
     * @return true when the hint should be cleared
     */
    boolean shouldClearHint();

    /**
     * Indicates whether invalid cell markers should be recomputed.
     *
     * @return true when invalid cells should be updated
     */
    boolean shouldUpdateInvalidCells();

    /**
     * Indicates whether the victory screen should be shown.
     *
     * @return true when the game is solved
     */
    boolean shouldShowVictory();

    /**
     * Provides the message for the move result.
     *
     * @return result message
     */
    String message();
}

/**
 * Result for a symbol or number outside the allowed range.
 *
 * @author Joan Lorenzo H. (carlosk4)
 * @author Abraham Y.
 * @version 1.0
 */
final class InvalidCellInputResult implements CellMoveResult {

    /**
     * Indicates whether the current hint should be cleared.
     *
     * @return true when the hint should be cleared
     */
    @Override
    public boolean shouldClearHint() {
        return true;
    }

    /**
     * Indicates whether invalid cell markers should be recomputed.
     *
     * @return false because the board does not change
     */
    @Override
    public boolean shouldUpdateInvalidCells() {
        return false;
    }

    /**
     * Indicates whether the victory screen should be shown.
     *
     * @return false because invalid input cannot solve the game
     */
    @Override
    public boolean shouldShowVictory() {
        return false;
    }

    /**
     * Provides the invalid input message.
     *
     * @return invalid input message
     */
    @Override
    public String message() {
        return "Símbolo o número inválido.";
    }
}

/**
 * Result for an attempt to edit a fixed cell.
 *
 * @author Joan Lorenzo H. (carlosk4)
 * @author Abraham Y.
 * @version 1.0
 */
final class UnmodifiableCellResult implements CellMoveResult {

    /**
     * Indicates whether the current hint should be cleared.
     *
     * @return false because the board does not change
     */
    @Override
    public boolean shouldClearHint() {
        return false;
    }

    /**
     * Indicates whether invalid cell markers should be recomputed.
     *
     * @return false because the board does not change
     */
    @Override
    public boolean shouldUpdateInvalidCells() {
        return false;
    }

    /**
     * Indicates whether the victory screen should be shown.
     *
     * @return false because the board does not change
     */
    @Override
    public boolean shouldShowVictory() {
        return false;
    }

    /**
     * Provides the unmodifiable cell message.
     *
     * @return unmodifiable cell message
     */
    @Override
    public String message() {
        return "No puedes modificar esta celda.";
    }
}

/**
 * Result for clearing an editable cell.
 *
 * @author Joan Lorenzo H. (carlosk4)
 * @author Abraham Y.
 * @version 1.0
 */
final class ClearedCellResult implements CellMoveResult {

    /**
     * Indicates whether the current hint should be cleared.
     *
     * @return true when the hint should be cleared
     */
    @Override
    public boolean shouldClearHint() {
        return true;
    }

    /**
     * Indicates whether invalid cell markers should be recomputed.
     *
     * @return true because the board changed
     */
    @Override
    public boolean shouldUpdateInvalidCells() {
        return true;
    }

    /**
     * Indicates whether the victory screen should be shown.
     *
     * @return false because a cleared cell cannot solve the game
     */
    @Override
    public boolean shouldShowVictory() {
        return false;
    }

    /**
     * Provides the cleared cell message.
     *
     * @return cleared cell message
     */
    @Override
    public String message() {
        return "Celda limpiada.";
    }
}

/**
 * Result for a move that breaks Sudoku constraints.
 *
 * @author Joan Lorenzo H. (carlosk4)
 * @author Abraham Y.
 * @version 1.0
 */
final class InvalidCellMoveResult implements CellMoveResult {

    /**
     * Indicates whether the current hint should be cleared.
     *
     * @return true when the hint should be cleared
     */
    @Override
    public boolean shouldClearHint() {
        return true;
    }

    /**
     * Indicates whether invalid cell markers should be recomputed.
     *
     * @return true because the board changed
     */
    @Override
    public boolean shouldUpdateInvalidCells() {
        return true;
    }

    /**
     * Indicates whether the victory screen should be shown.
     *
     * @return false because the move is invalid
     */
    @Override
    public boolean shouldShowVictory() {
        return false;
    }

    /**
     * Provides the invalid move message.
     *
     * @return invalid move message
     */
    @Override
    public String message() {
        return "Movimiento inválido: el número se repite en fila, columna o bloque.";
    }
}

/**
 * Result for a move that solves the game.
 *
 * @author Joan Lorenzo H. (carlosk4)
 * @author Abraham Y.
 * @version 1.0
 */
final class SolvedCellMoveResult implements CellMoveResult {

    /**
     * Indicates whether the current hint should be cleared.
     *
     * @return true when the hint should be cleared
     */
    @Override
    public boolean shouldClearHint() {
        return true;
    }

    /**
     * Indicates whether invalid cell markers should be recomputed.
     *
     * @return true because the board changed
     */
    @Override
    public boolean shouldUpdateInvalidCells() {
        return true;
    }

    /**
     * Indicates whether the victory screen should be shown.
     *
     * @return true because the game is solved
     */
    @Override
    public boolean shouldShowVictory() {
        return true;
    }

    /**
     * Provides an empty message because victory navigation follows.
     *
     * @return empty message
     */
    @Override
    public String message() {
        return "";
    }
}

/**
 * Result for a valid move that does not solve the game.
 *
 * @author Joan Lorenzo H. (carlosk4)
 * @author Abraham Y.
 * @version 1.0
 */
final class ValidCellMoveResult implements CellMoveResult {

    /**
     * Indicates whether the current hint should be cleared.
     *
     * @return true when the hint should be cleared
     */
    @Override
    public boolean shouldClearHint() {
        return true;
    }

    /**
     * Indicates whether invalid cell markers should be recomputed.
     *
     * @return true because the board changed
     */
    @Override
    public boolean shouldUpdateInvalidCells() {
        return true;
    }

    /**
     * Indicates whether the victory screen should be shown.
     *
     * @return false because the game is not solved
     */
    @Override
    public boolean shouldShowVictory() {
        return false;
    }

    /**
     * Provides the valid move message.
     *
     * @return valid move message
     */
    @Override
    public String message() {
        return "Movimiento válido.";
    }
}
