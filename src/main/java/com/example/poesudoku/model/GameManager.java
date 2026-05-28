package com.example.poesudoku.model;

import static com.example.poesudoku.model.SudokuConstants.EMPTY_CELL;
import static com.example.poesudoku.model.SudokuConstants.SIZE;

public class GameManager implements GameManagerInterface {

    private final SudokuBoardInterface board = new SudokuBoard();
    private final SudokuGeneratorInterface generator = new SudokuGenerator();
    private final GameTree gameTree = new GameTree();
    private final SudokuHintProvider hintProvider = new SudokuHintProvider();

    private boolean[][] fixedCells = new boolean[SIZE][SIZE];
    private int[][] solutionBoard = new int[SIZE][SIZE];
    private int hintsUsed;

    @Override
    public void startNewGame() {
        generator.generate();
        generator.placeFixedNumbers();

        fixedCells = generator.getFixedCells();
        solutionBoard = generator.getGeneratedBoard();
        hintsUsed = 0;

        board.reset();

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                int value = fixedCells[row][col] ? solutionBoard[row][col] : EMPTY_CELL;
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
        int[][] previousBoard = gameTree.undo();
        loadBoard(previousBoard);
    }

    @Override
    public boolean canUndo() {
        return gameTree.canUndo();
    }

    @Override
    public boolean setCellValue(int row, int col, int value) {
        if (!isInsideBoard(row, col) || isFixedCell(row, col) || !isValidCellValue(value)) {
            return false;
        }

        int previousValue = board.getCell(row, col);

        if (previousValue == value) {
            return true;
        }

        board.setCell(row, col, value);
        saveState();

        return true;
    }

    @Override
    public boolean isFixedCell(int row, int col) {
        return isInsideBoard(row, col) && fixedCells[row][col];
    }

    @Override
    public boolean isCellValid(int row, int col) {
        if (!isInsideBoard(row, col)) {
            return true;
        }

        int value = board.getCell(row, col);

        if (value == EMPTY_CELL) {
            return false;
        }

        return !board.isValid(row, col, value);
    }

    @Override
    public boolean isSolved() {
        return board.isSolved();
    }

    @Override
    public int[][] getBoard() {
        return board.getBoard();
    }

    @Override
    public boolean[][] getFixedCells() {
        return fixedCells;
    }

    @Override
    public int[] getHint() {
        return hintProvider.findHint(board.getBoard(), solutionBoard, fixedCells);
    }

    @Override
    public void registerHintUsed() {
        hintsUsed++;
    }

    @Override
    public int getHintsUsed() {
        return hintsUsed;
    }

    private void loadBoard(int[][] boardState) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                board.setCell(row, col, boardState[row][col]);
            }
        }
    }

    private boolean isInsideBoard(int row, int col) {
        return row >= 0 && row < SIZE && col >= 0 && col < SIZE;
    }

    private boolean isValidCellValue(int value) {
        return value >= EMPTY_CELL && value <= SIZE;
    }
}