package com.example.poesudoku.view;

@FunctionalInterface
public interface CellChangeHandler {
    void onCellChanged(int row, int col, String value);
}