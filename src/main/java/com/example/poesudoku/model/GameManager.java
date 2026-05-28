package com.example.poesudoku.model;

import static com.example.poesudoku.model.SudokuConstants.EMPTY_CELL;
import static com.example.poesudoku.model.SudokuConstants.SIZE;

/**
 * Manages Sudoku board state, history, hints, and fixed cells.
 *
 * @author Joan Lorenzo H. (carlosk4)
 * @author Abraham Y.
 * @version 1.0
 */
public class GameManager implements GameManagerInterface {

    private final SudokuBoardInterface board = new SudokuBoard();
    private final SudokuGeneratorInterface generator = new SudokuGenerator();
    private final GameTree gameTree = new GameTree();
    private final SudokuHintProvider hintProvider = new SudokuHintProvider();

    private boolean[][] fixedCells = new boolean[SIZE][SIZE];
    private int[][] solutionBoard = new int[SIZE][SIZE];
    private int hintsUsed;

    /**
     * Starts a new game.
     */
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

    /**
     * Saves the current board state.
     */
    @Override
    public void saveState() {
        gameTree.push(board.getBoard());
    }

    /**
     * Restores the previous board state.
     */
    @Override
    public void undo() {
        int[][] previousBoard = gameTree.undo();
        loadBoard(previousBoard);
    }

    /**
     * Checks whether undo is available.
     *
     * @return true when a previous state exists
     */
    @Override
    public boolean canUndo() {
        return gameTree.canUndo();
    }

    /**
     * Sets a cell value when the target cell can be edited.
     *
     * @param row target row
     * @param col target column
     * @param value target value
     * @return true when the value can be set
     */
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

    /**
     * Checks whether a cell is fixed.
     *
     * @param row target row
     * @param col target column
     * @return true when the cell is fixed
     */
    @Override
    public boolean isFixedCell(int row, int col) {
        return isInsideBoard(row, col) && fixedCells[row][col];
    }

    /**
     * Checks whether a cell is marked invalid.
     *
     * @param row target row
     * @param col target column
     * @return true when the cell is invalid
     */
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

    /**
     * Checks whether the puzzle is solved.
     *
     * @return true when the board is solved
     */
    @Override
    public boolean isSolved() {
        return board.isSolved();
    }

    /**
     * Returns a copy of the board.
     *
     * @return copied board
     */
    @Override
    public int[][] getBoard() {
        return board.getBoard();
    }

    /**
     * Returns fixed cell markers.
     *
     * @return fixed cell markers
     */
    @Override
    public boolean[][] getFixedCells() {
        return fixedCells;
    }

    /**
     * Finds a hint for the current board.
     *
     * @return hint data
     */
    @Override
    public int[] getHint() {
        return hintProvider.findHint(board.getBoard(), solutionBoard, fixedCells);
    }

    /**
     * Counts one used hint.
     */
    @Override
    public void registerHintUsed() {
        hintsUsed++;
    }

    /**
     * Returns the number of used hints.
     *
     * @return used hint count
     */
    @Override
    public int getHintsUsed() {
        return hintsUsed;
    }

    /**
     * Loads a board state into the board.
     *
     * @param boardState board state
     */
    private void loadBoard(int[][] boardState) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                board.setCell(row, col, boardState[row][col]);
            }
        }
    }

    /**
     * Checks whether coordinates are inside the board.
     *
     * @param row target row
     * @param col target column
     * @return true when coordinates are inside the board
     */
    private boolean isInsideBoard(int row, int col) {
        return row >= 0 && row < SIZE && col >= 0 && col < SIZE;
    }

    /**
     * Checks whether a value is allowed in a cell.
     *
     * @param value target value
     * @return true when the value is between empty and board size
     */
    private boolean isValidCellValue(int value) {
        return value >= EMPTY_CELL && value <= SIZE;
    }
}
