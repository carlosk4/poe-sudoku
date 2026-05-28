package com.example.poesudoku.model;

public interface CellMoveResult {
    boolean shouldClearHint();

    boolean shouldUpdateInvalidCells();

    boolean shouldShowVictory();

    String message();
}

final class InvalidCellInputResult implements CellMoveResult {

    @Override
    public boolean shouldClearHint() {
        return true;
    }

    @Override
    public boolean shouldUpdateInvalidCells() {
        return false;
    }

    @Override
    public boolean shouldShowVictory() {
        return false;
    }

    @Override
    public String message() {
        return "Símbolo o número inválido.";
    }
}

final class UnmodifiableCellResult implements CellMoveResult {

    @Override
    public boolean shouldClearHint() {
        return false;
    }

    @Override
    public boolean shouldUpdateInvalidCells() {
        return false;
    }

    @Override
    public boolean shouldShowVictory() {
        return false;
    }

    @Override
    public String message() {
        return "No puedes modificar esta celda.";
    }
}

final class ClearedCellResult implements CellMoveResult {

    @Override
    public boolean shouldClearHint() {
        return true;
    }

    @Override
    public boolean shouldUpdateInvalidCells() {
        return true;
    }

    @Override
    public boolean shouldShowVictory() {
        return false;
    }

    @Override
    public String message() {
        return "Celda limpiada.";
    }
}

final class InvalidCellMoveResult implements CellMoveResult {

    @Override
    public boolean shouldClearHint() {
        return true;
    }

    @Override
    public boolean shouldUpdateInvalidCells() {
        return true;
    }

    @Override
    public boolean shouldShowVictory() {
        return false;
    }

    @Override
    public String message() {
        return "Movimiento inválido: el número se repite en fila, columna o bloque.";
    }
}

final class SolvedCellMoveResult implements CellMoveResult {

    @Override
    public boolean shouldClearHint() {
        return true;
    }

    @Override
    public boolean shouldUpdateInvalidCells() {
        return true;
    }

    @Override
    public boolean shouldShowVictory() {
        return true;
    }

    @Override
    public String message() {
        return "";
    }
}

final class ValidCellMoveResult implements CellMoveResult {

    @Override
    public boolean shouldClearHint() {
        return true;
    }

    @Override
    public boolean shouldUpdateInvalidCells() {
        return true;
    }

    @Override
    public boolean shouldShowVictory() {
        return false;
    }

    @Override
    public String message() {
        return "Movimiento válido.";
    }
}
