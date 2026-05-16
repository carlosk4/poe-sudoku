package com.example.poesudoku.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.example.poesudoku.model.SudokuConstants.SIZE;

public class GameStateNode {

    private final int[][] boardState;
    private GameStateNode parent;
    private final List<GameStateNode> children = new ArrayList<>();

    public GameStateNode(int[][] boardState) {
        this.boardState = copyBoard(boardState);
    }

    public void addChild(GameStateNode child) {
        child.parent = this;
        children.add(child);
    }

    public GameStateNode getParent() {
        return parent;
    }

    public List<GameStateNode> getChildren() {
        return Collections.unmodifiableList(children);
    }

    public boolean hasChildren() {
        return !children.isEmpty();
    }

    public GameStateNode getChild(int index) {
        return children.get(index);
    }

    public int getChildrenCount() {
        return children.size();
    }

    public int[][] getBoardState() {
        return copyBoard(boardState);
    }

    private int[][] copyBoard(int[][] original) {
        int[][] copy = new int[SIZE][SIZE];

        for (int row = 0; row < SIZE; row++) {
            System.arraycopy(original[row], 0, copy[row], 0, SIZE);
        }

        return copy;
    }
}