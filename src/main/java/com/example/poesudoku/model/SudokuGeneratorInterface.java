package com.example.poesudoku.model;

public interface SudokuGeneratorInterface {
    void generate();
    void placeFixedNumbers();
    int[][] getGeneratedBoard();
    boolean[][] getFixedCells();
}