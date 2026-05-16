package com.example.poesudoku.model;

import static com.example.poesudoku.model.SudokuConstants.EMPTY_CELL;
import static com.example.poesudoku.model.SudokuConstants.SIZE;

public class GameManager implements GameManagerInterface {

    private final SudokuBoardInterface board = new SudokuBoard();
    private final SudokuGeneratorInterface generator = new SudokuGenerator();
    private final GameTree gameTree = new GameTree();

    private boolean[][] fixedCells = new boolean[SIZE][SIZE];

    @Override
    public void startNewGame() {
        generator.generate();
        generator.placeFixedNumbers();

        fixedCells = generator.getFixedCells();
        int[][] generatedBoard = generator.getGeneratedBoard();

        board.reset();

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                int value = fixedCells[row][col] ? generatedBoard[row][col] : EMPTY_CELL;
                board.setCell(row, col, value);
            }
        }

        gameTree.init(board.getBoard());
    }

    @Override
    public void saveState() {
        gameTree.push(board.getBoard());
    }

    @Override
    public void undo() {
        if (!gameTree.canUndo()) {
            return;
        }

        int[][] previousBoard = gameTree.undo();

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                board.setCell(row, col, previousBoard[row][col]);
            }
        }
    }

    @Override
    public boolean canUndo() {
        return gameTree.canUndo();
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