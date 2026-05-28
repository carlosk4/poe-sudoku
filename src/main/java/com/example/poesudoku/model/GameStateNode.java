package com.example.poesudoku.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.example.poesudoku.model.SudokuConstants.SIZE;

/**
 * Represents one board state in the game history tree.
 *
 * @author Joan Lorenzo H. (carlosk4)
 * @author Abraham Y.
 * @version 1.0
 */
public class GameStateNode {

    private final int[][] boardState;
    private GameStateNode parent;
    private final List<GameStateNode> children = new ArrayList<>();

    /**
     * Creates a node from a board state.
     *
     * @param boardState board state
     */
    public GameStateNode(int[][] boardState) {
        this.boardState = copyBoard(boardState);
    }

    /**
     * Adds a child state to this node.
     *
     * @param child child state node
     */
    public void addChild(GameStateNode child) {
        child.parent = this;
        children.add(child);
    }

    /**
     * Returns the parent state.
     *
     * @return parent state node
     */
    public GameStateNode getParent() {
        return parent;
    }

    /**
     * Returns child states.
     *
     * @return unmodifiable child list
     */
    public List<GameStateNode> getChildren() {
        return Collections.unmodifiableList(children);
    }

    /**
     * Checks whether this node has children.
     *
     * @return true when child states exist
     */
    public boolean hasChildren() {
        return !children.isEmpty();
    }

    /**
     * Returns a child by index.
     *
     * @param index child index
     * @return child state node
     */
    public GameStateNode getChild(int index) {
        return children.get(index);
    }

    /**
     * Returns the number of child states.
     *
     * @return child state count
     */
    public int getChildrenCount() {
        return children.size();
    }

    /**
     * Returns a copy of the board state.
     *
     * @return copied board state
     */
    public int[][] getBoardState() {
        return copyBoard(boardState);
    }

    /**
     * Copies a board matrix.
     *
     * @param original source board
     * @return copied board
     */
    private int[][] copyBoard(int[][] original) {
        int[][] copy = new int[SIZE][SIZE];

        for (int row = 0; row < SIZE; row++) {
            System.arraycopy(original[row], 0, copy[row], 0, SIZE);
        }

        return copy;
    }
}
