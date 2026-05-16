package com.example.poesudoku.model;

import java.util.ArrayList;
import java.util.List;

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
        return children;
    }

    public int[][] getBoardState() {
        return copyBoard(boardState);
    }

    private int[][] copyBoard(int[][] original) {
        int[][] copy = new int[6][6];
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                copy[row][col] = original[row][col];
            }
        }
        return copy;
    }
}