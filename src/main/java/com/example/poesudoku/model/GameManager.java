package com.example.poesudoku.model;

public class GameManager implements GameManagerInterface {

    private final SudokuBoardInterface board = new SudokuBoard();
    private final SudokuGeneratorInterface generator = new SudokuGenerator();
    private boolean[][] fixedCells;

    @Override
    public void startNewGame() {
        generator.generate();
        generator.placeFixedNumbers();

        fixedCells = generator.getFixedCells();
        int[][] generatedBoard = generator.getGeneratedBoard();

        board.reset();
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                int value = fixedCells[row][col] ? generatedBoard[row][col] : 0;
                board.setCell(row, col, value);
            }
        }
    }

    @Override
    public int[][] getBoard() {
        return board.getBoard();
    }

    @Override
    public boolean[][] getFixedCells() {
        return fixedCells;
    }
}